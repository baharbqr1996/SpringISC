package Spring.MultiThreading.service;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.*;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Spring.MultiThreading.auth.EncryptionUtil;
import Spring.MultiThreading.model.Account;
import Spring.MultiThreading.model.AccountType;
import Spring.MultiThreading.model.Customer;
import Spring.MultiThreading.model.ErrorLog;
import Spring.MultiThreading.repository.AccountRepository;
import Spring.MultiThreading.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class DataProcessingService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    private final ValidationService validationService;

    private SecretKey secretKey;

    private final int NUM_THREADS = 4; 
   
    @PostConstruct
    public void init() throws Exception {
        
        secretKey = EncryptionUtil.generateKey();
    }
    
    public DataProcessingService(ValidationService validationService) {
		super();
		this.validationService = validationService;
	}


    @Transactional
    public void processFiles(MultipartFile accountsFile, MultipartFile customersFile) throws IOException {
        System.out.println("Starting to process files...");
        Map<Long, Customer> customerMap = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        try {
            Future<Map<Long, Customer>> customerFuture = executorService.submit(() -> readCustomersFromCsv(customersFile));
            Future<List<Account>> accountsFuture = executorService.submit(() -> readAccountsFromCsv(accountsFile, customerMap));

            Map<Long, Customer> customers = customerFuture.get();
            List<Account> accounts = accountsFuture.get();

            int customerLineNumber = 1; 
            for (Customer customer : customers.values()) {
                if (validationService.validateCustomer(customer)) {
                    // بررسی کد ملی قبل از ذخیره‌سازی
                    if (!customerRepository.existsByCustomerNationalId(customer.getCustomerNationalId())) {
                        customerRepository.save(customer);
                        customerMap.put(customer.getCustomerId(), customer);
                    } else {
                        logError(customer, customerLineNumber); // ثبت خطا در صورت تکراری بودن
                    }
                } else {
                    logError(customer, customerLineNumber); 
                }
                customerLineNumber++;
            }

            int accountLineNumber = 1;  
            for (Account account : accounts) {
                if (validationService.validateAccount(account)) {
                    Customer customer = account.getCustomer(); // دریافت شیء مشتری از حساب
                    
                    if (customer != null) {
                        Long customerId = customer.getCustomerId(); // دریافت شناسه مشتری
                        account.setCustomer(customer); // تنظیم مشتری برای حساب
                        customer.setAccount(account); // تنظیم حساب برای مشتری
                        accountRepository.save(account); // ذخیره حساب
                        customerRepository.save(customer); // ذخیره مشتری (در صورت نیاز)
                    } else {
                        logError(account, accountLineNumber); // اگر مشتری پیدا نشد
                    }
                } else {
                    logError(account, accountLineNumber);
                }
                accountLineNumber++;
            }




        } catch (InterruptedException e) {
            System.err.println("Processing was interrupted: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Error during execution: " + e.getCause().getMessage());
            e.getCause().printStackTrace();
        } finally {
            executorService.shutdown(); 
        }
    }

    private Map<Long, Customer> readCustomersFromCsv(MultipartFile file) throws IOException {
        System.out.println("Start reading customers from CSV...");

        Map<Long, Customer> customers = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true; 
            int lineNumber = 1; 
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; 
                }

                String[] fields = line.split(",");
                Customer customer = new Customer();
                String nationalId = fields[5].trim();

                try {                  
                    customer.setCustomerId(Long.parseLong(fields[0].trim()));
                    String encryptedName = EncryptionUtil.encrypt(fields[1].trim(), secretKey);
                    String encryptedLastname = EncryptionUtil.encrypt(fields[2].trim(), secretKey);
                    customer.setCustomerName(encryptedName);
                    customer.setCustomerLastname(encryptedLastname);
                    customer.setCustomerNationalId(EncryptionUtil.encrypt(nationalId, secretKey));
                    
                    customer.setCustomerAddress(fields[3].trim());
                    customer.setCustomerZipCode(fields[4].trim());

                    // تبدیل تاریخ از dd-MM-yyyy به LocalDate
                    String[] dateParts = fields[6].trim().split("-");
                    LocalDate birthDate = LocalDate.of(
                        Integer.parseInt(dateParts[2]), // year
                        Integer.parseInt(dateParts[1]), // month
                        Integer.parseInt(dateParts[0])  // day
                    );
                    customer.setCustomerBirthDate(birthDate);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number for customer ID: " + fields[0] + ", data: " + line);
                    logError(customer, lineNumber);
                    lineNumber++;
                    continue;
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date for customer with ID " + fields[0] + ": " + e.getMessage() + ", data: " + line);
                    logError(customer, lineNumber);
                    lineNumber++;
                    continue;
                } catch (Exception e) {
                    System.err.println("Error encrypting fields for customer: " + e.getMessage());
                    logError(customer, lineNumber);
                    lineNumber++;
                    continue;
                }

                System.out.println("Read Customer: " + customer);
                customers.put(customer.getCustomerId(), customer);
                lineNumber++;
            }
        }
        return customers;
    }


    private List<Account> readAccountsFromCsv(MultipartFile file, Map<Long, Customer> customerMap) throws IOException {
        System.out.println("Start reading accounts from CSV...");

        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true; 
            int lineNumber = 1; 
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; 
                }

                String[] fields = line.split(",");
                Account account = new Account();
                try {
                	account.setId(Long.parseLong(fields[0].trim()));
                    account.setAccountNumber(fields[1].trim());
                    
                    account.setAccountType(AccountType.values()[Integer.parseInt(fields[2].trim()) - 1]);
                    
                    Long customerId = Long.parseLong(fields[3].trim());
                    account.setAccountLimit(Double.parseDouble(fields[4].trim()));
                    String[] dateParts = fields[5].trim().split("-");
                    LocalDate openDate = LocalDate.of(
                        Integer.parseInt(dateParts[2]), // year
                        Integer.parseInt(dateParts[1]), // month
                        Integer.parseInt(dateParts[0])  // day
                    );
                    account.setAccountOpenDate(openDate);
                    account.setAccountBalance(Double.parseDouble(fields[6].trim()));

                    Customer customer = customerMap.get(customerId);
                    if (customer != null) {
                        account.setCustomer(customer);
                        customer.setAccount(account);
                    }

                    accounts.add(account);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number for account number: " + fields[0] + ", data: " + line);
                    logError(account, lineNumber);
                    lineNumber++;
                    continue;
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date for account: " + e.getMessage() + ", data: " + line);
                    logError(account, lineNumber); 
                    lineNumber++;
                    continue;
                } catch (Exception e) {
                    System.err.println("Error processing account: " + e.getMessage() + ", data: " + line);
                    logError(account, lineNumber); 
                    lineNumber++;
                    continue;
                }
            }
        }
        return accounts;
    }
    
    @Autowired
    private ObjectMapper objectMapper;

    // لیست برای نگهداری خطاها
    private List<ErrorLog> errorLogs = new ArrayList<>();

    public void logError(Object entity, int lineNumber) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setFileName(entity instanceof Account ? "accounts.csv" : "customers.csv");
        errorLog.setRecordNumber(lineNumber); 
        errorLog.setErrorCode("VALIDATION_ERROR");
        errorLog.setErrorClassificationName("Validation Error");
        errorLog.setErrorDescription("Invalid data for entity: " + entity.toString());
        errorLog.setErrorDate(LocalDateTime.now());

        // اضافه کردن خطا به لیست
        errorLogs.add(errorLog);

        // نوشتن به فایل JSON
        writeErrorsToFile();
    }
    private void writeErrorsToFile() {
        try {
            File file = new File("src/main/resources/errors.json");
            
            // اگر فایل وجود ندارد، ایجاد می‌شود
            if (!file.exists()) {
                file.createNewFile();
                // نوشتن آرایه خالی در فایل
                objectMapper.writeValue(file, new ArrayList<ErrorLog>());
            }

            List<ErrorLog> existingErrors = new ArrayList<>();
            
            // بررسی اینکه آیا فایل خالی است یا خیر
            if (file.length() > 0) {
                existingErrors = objectMapper.readValue(file, new TypeReference<List<ErrorLog>>() {});
            }
            
            existingErrors.addAll(errorLogs); // اضافه کردن خطاهای جدید به خطاهای موجود
            
            // نوشتن مجدد همه خطاها به فایل با فرمت زیبا
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, existingErrors);
        } catch (IOException e) {
            System.out.println("Error logging the error: " + e.getMessage());
        }
    }


/*    
    @Autowired
    private ObjectMapper objectMapper; 

    public void logError(Object entity, int lineNumber) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setFileName(entity instanceof Account ? "accounts.csv" : "customers.csv");
        errorLog.setRecordNumber(lineNumber); 
        errorLog.setErrorCode("VALIDATION_ERROR");
        errorLog.setErrorClassificationName("Validation Error");
        errorLog.setErrorDescription("Invalid data for entity: " + entity.toString());
        errorLog.setErrorDate(LocalDateTime.now());
        
        try {
            File file = new File("src/main/resources/errors.json");
            objectMapper.writeValue(file, new ArrayList<ErrorLog>());

            
            List<ErrorLog> errorLogs = objectMapper.readValue(file, new TypeReference<List<ErrorLog>>() {});
            errorLogs.add(errorLog);

            objectMapper.writeValue(file, errorLogs);
            
        } catch (IOException e) {
            System.out.println("Error logging the error: " + e.getMessage());
        }
    }
*/    
}
