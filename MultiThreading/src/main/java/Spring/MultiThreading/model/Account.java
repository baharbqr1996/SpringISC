package Spring.MultiThreading.model;

import java.time.LocalDate;

import javax.validation.constraints.*;
import jakarta.persistence.*;

@Entity
@Table(name = "ACCOUNT2")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Pattern(regexp = "^[0-9]{22}$", message = "Account number must be 22 digits with leading zeros")
    private String accountNumber;
    @NotNull
	@Enumerated(EnumType.STRING)
    private AccountType accountType;
    @NotNull
    private double accountLimit;
    @NotNull
    private LocalDate accountOpenDate;
    @NotNull
    private double accountBalance; // Encrypted
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId") 
    private Customer customer;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Account(Long id, String accountNumber,@NotNull AccountType accountType, @NotNull double accountLimit,LocalDate accountOpenDate,
			@NotNull double accountBalance, Customer customer) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountLimit = accountLimit;
		this.accountOpenDate = accountOpenDate;
		this.accountBalance = accountBalance;
		this.customer = customer;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getAccountLimit() {
		return accountLimit;
	}

	public void setAccountLimit(double accountLimit) {
		this.accountLimit = accountLimit;
	}

	public LocalDate getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(LocalDate accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}


    
}

