package Spring.MultiThreading.model;

import java.time.LocalDate;
import javax.validation.constraints.*;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotNull
    @Size(min = 1)
    private String customerName; // Encrypted
    @NotNull
    @Size(min = 1)
    private String customerLastname; // Encrypted
    @NotNull
    private String customerAddress;
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Customer Zip Code must be 10 digits")
    private String customerZipCode;
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Customer National ID must be 10 digits")
    @Column(unique = true)
    private String customerNationalId; // Encrypted
    @NotNull
    @Past
    private LocalDate customerBirthDate;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;
    
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Customer(Long customerId, @NotNull @Size(min = 1) String customerName,
			@NotNull @Size(min = 1) String customerLastname, @NotNull String customerAddress,
			@NotNull @Pattern(regexp = "^[0-9]{10}$", message = "Customer Zip Code must be 10 digits") String customerZipCode,
			@NotNull @Pattern(regexp = "^[0-9]{10}$", message = "Customer National ID must be 10 digits") String customerNationalId,
			@NotNull @Past LocalDate customerBirthDate, Account account) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerLastname = customerLastname;
		this.customerAddress = customerAddress;
		this.customerZipCode = customerZipCode;
		this.customerNationalId = customerNationalId;
		this.customerBirthDate = customerBirthDate;
		this.account = account;
	}
	
	
	public @NotNull long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(@NotNull long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerLastname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerZipCode() {
		return customerZipCode;
	}

	public void setCustomerZipCode(String customerZipCode) {
		this.customerZipCode = customerZipCode;
	}

	public String getCustomerNationalId() {
		return customerNationalId;
	}

	public void setCustomerNationalId(String customerNationalId) {
		this.customerNationalId = customerNationalId;
	}

	public LocalDate getCustomerBirthDate() {
		return customerBirthDate;
	}

	public void setCustomerBirthDate(LocalDate customerBirthDate) {
		this.customerBirthDate = customerBirthDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

    
}

