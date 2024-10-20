package Spring.wallet.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String type; // Deposit or Withdrawal
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long id, BigDecimal amount, LocalDateTime transactionDate, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime localDateTime) {
		this.transactionDate = localDateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", transactionDate="
				+ transactionDate + ", type=" + type + "]";
	}

	

    
}

