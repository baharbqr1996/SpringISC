package Spring.wallet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Account number is required")
    @Pattern(regexp = "^[0-9]{1,20}$", message = "Account number must be numeric and up to 20 digits")
    @Column(unique = true)
    private String accountNumber;
    @DecimalMin(value = "10000", message = "Minimum balance must be 10,000")
    private BigDecimal balance;
    private LocalDate creationDate;
    @NotNull(message = "Shaba number is required")
    @Pattern(regexp = "^IR[0-9]{24}$", message = "Shaba number must start with 'IR' followed by 24 digits")
    private String shabaNumber;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getShabaNumber() {
		return shabaNumber;
	}

	public void setShabaNumber(String shabaNumber) {
		this.shabaNumber = shabaNumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + ", creationDate="
				+ creationDate + ", shabaNumber=" + shabaNumber + "]";
	}

   
}
