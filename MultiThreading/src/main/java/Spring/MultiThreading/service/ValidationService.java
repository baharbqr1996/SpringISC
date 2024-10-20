package Spring.MultiThreading.service;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Spring.MultiThreading.model.Account;
import Spring.MultiThreading.model.AccountType;
import Spring.MultiThreading.model.Customer;

import java.time.LocalDate;
import java.util.Set;
/*
@Service
public class ValidationService{

    private Set<String> existingAccountNumbers; // مجموعه اکانت‌نامبرهای موجود
    private Set<String> existingNationalIds; // مجموعه کد ملی‌های موجود

    private DataProcessingService dataProcessingService;

    @Autowired
    public ValidationService(Set<String> existingAccountNumbers, Set<String> existingNationalIds) {
        this.existingAccountNumbers = existingAccountNumbers;
        this.existingNationalIds = existingNationalIds;
    }
    
    public boolean validateAccount(Account account, int lineNumber) {
        return validateAccountBalance(account , lineNumber) 
            && validateAccountType(account , lineNumber) 
            && validateUniqueAccountNumber(account.getAccountNumber(), lineNumber);
    }

    private boolean validateAccountBalance(Account account , int lineNumber) {
        if (account.getAccountBalance() >= account.getAccountLimit()) {
        	System.err.println("Account balance is more than account limit: \n balance : " + account.getAccountBalance() + " limit : " + account.getAccountLimit());
            dataProcessingService.logError("Account number: " + account.getAccountNumber(), lineNumber);
            return false;
        }
        return true;
    }

    private boolean validateAccountType(Account account , int lineNumber) {
        if (account.getAccountType() == null || isValidAccountType(account.getAccountType()) == false) {
        	System.err.println("Account type is invalid : " + account.getAccountType());
            dataProcessingService.logError("Account number: " + account.getAccountNumber(), lineNumber);
            return false;
        }
		return true;
    }

    private boolean isValidAccountType(AccountType accountType) {
        return accountType.ordinal() >= 0 && accountType.ordinal() < AccountType.values().length;
    }

    private boolean validateUniqueAccountNumber(String accountNumber, int lineNumber) {
        if (existingAccountNumbers.contains(accountNumber)) {
            System.err.println("Duplicate account number: " + accountNumber);
            dataProcessingService.logError("Account number: " + accountNumber, lineNumber);
            return false;
        }
        System.err.println("Duplicate account number: " + accountNumber);
        dataProcessingService.logError("Account number: " + accountNumber, lineNumber);
        return false;
       
    }

    public boolean validateCustomer(Customer customer, int lineNumber) {
        return validateCustomerBirthDate(customer , lineNumber) 
            && validateCustomerZipCode(customer.getCustomerZipCode() , lineNumber) 
            && validateUniqueNationalId(customer.getCustomerNationalId(), lineNumber);
    }

    private boolean validateCustomerBirthDate(Customer customer , int lineNumber) {
        if (customer.getCustomerBirthDate().isAfter(LocalDate.of(1995, 1, 1))) {
        	System.err.println("Invalid birthdate : " + customer.getCustomerBirthDate());
            dataProcessingService.logError("Birthdate: " + customer.getCustomerBirthDate(), lineNumber);
            return false;
        }
		return true;
    }

    private boolean validateCustomerZipCode(String zipCode, int lineNumber) {
        if (zipCode != null && zipCode.matches("^[0-9]{10}$")) {
        	 System.err.println("Invalid zip code: " + zipCode);
             dataProcessingService.logError("Zip code : " + zipCode, lineNumber);
             return false;
        }
		return true;
    }

    private boolean validateUniqueNationalId(String nationalId, int lineNumber) {
        if (existingNationalIds.contains(nationalId)) {
            System.err.println("Duplicate national ID: " + nationalId);
            dataProcessingService.logError("National ID: " + nationalId, lineNumber);
            return false;
        }
        existingNationalIds.add(nationalId); // اضافه کردن به مجموعه اگر تکراری نباشد
        return true;
    }

}
*/

@Service
public class ValidationService {

    private Set<String> existingAccountNumbers; // مجموعه اکانت‌نامبرهای موجود
    private Set<String> existingNationalIds; // مجموعه کد ملی‌های موجود

    @Autowired
    public ValidationService(Set<String> existingAccountNumbers, Set<String> existingNationalIds) {
        this.existingAccountNumbers = existingAccountNumbers;
        this.existingNationalIds = existingNationalIds;
    }

    public boolean validateAccount(Account account) {
        return validateAccountBalance(account) && validateAccountType(account) && validateUniqueAccountNumber(account.getAccountNumber());
    }

    private boolean validateAccountBalance(Account account) {
        return account.getAccountBalance() <= account.getAccountLimit();
    }

    private boolean validateAccountType(Account account) {
        return account.getAccountType() != null && isValidAccountType(account.getAccountType());
    }

    private boolean isValidAccountType(AccountType accountType) {
        return accountType.ordinal() >= 0 && accountType.ordinal() < AccountType.values().length;
    }

    private boolean validateUniqueAccountNumber(String accountNumber) {
        if (existingAccountNumbers.contains(accountNumber)) {
            System.err.println("Duplicate account number: " + accountNumber);
            return false;
        }
        existingAccountNumbers.add(accountNumber); // اضافه کردن به مجموعه اگر تکراری نباشد
        return true;
    }

    public boolean validateCustomer(Customer customer) {
        return validateCustomerBirthDate(customer) && validateCustomerZipCode(customer.getCustomerZipCode()) && validateUniqueNationalId(customer.getCustomerNationalId());
    }

    private boolean validateCustomerBirthDate(Customer customer) {
        return customer.getCustomerBirthDate().isAfter(LocalDate.of(1995, 1, 1));
    }

    private boolean validateCustomerZipCode(String zipCode) {
        return zipCode != null && zipCode.matches("^[0-9]{10}$");
    }

    private boolean validateUniqueNationalId(String nationalId) {
        if (existingNationalIds.contains(nationalId)) {
            System.err.println("Duplicate national ID: " + nationalId);
            return false;
        }
        existingNationalIds.add(nationalId); // اضافه کردن به مجموعه اگر تکراری نباشد
        return true;
    }
}