package Spring.wallet.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Spring.wallet.model.Account;
import Spring.wallet.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        // بررسی وجود شماره حساب
        if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }

        // بررسی حداقل موجودی
        if (account.getBalance().compareTo(BigDecimal.valueOf(10000)) < 0) {
            throw new IllegalArgumentException("Minimum balance must be 10,000");
        }

        // ذخیره حساب
        return accountRepository.save(account);
    }

    public void deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        
        // Check withdrawal conditions
        if (amount.compareTo(BigDecimal.valueOf(100000)) < 0) {
            throw new IllegalArgumentException("Minimum withdrawal amount is 100,000");
        }
        if (amount.compareTo(BigDecimal.valueOf(10000000)) > 0) {
            throw new IllegalArgumentException("Maximum withdrawal amount is 10,000,000");
        }
        if (amount.compareTo(account.getBalance()) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Deduct amount
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

}

