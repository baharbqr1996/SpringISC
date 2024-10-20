package Spring.wallet;

import Spring.wallet.controller.TransactionController;
import Spring.wallet.model.Transaction;
import Spring.wallet.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void getTransactionsByAccountId_ValidId_ReturnsTransactions() throws Exception {
        Long accountId = 1L;
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(BigDecimal.valueOf(100.00));
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setType("Deposit");

        when(transactionService.getTransactionsByAccountId(accountId)).thenReturn(Arrays.asList(transaction));

        mockMvc.perform(get("/api/transactions/account/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                // .andExpect(jsonPath("$[0].amount").value(100.00)); // اگر نیاز به چک کردن محتوا دارید
    }

    @Test
    public void createTransaction_ValidTransaction_ReturnsCreated() throws Exception {
        Long accountId = 1L;
        BigDecimal amount = BigDecimal.valueOf(100.00);
        Transaction createdTransaction = new Transaction();
        createdTransaction.setId(1L);
        createdTransaction.setAmount(amount);
        createdTransaction.setTransactionDate(LocalDateTime.now());
        createdTransaction.setType("Deposit");

        when(transactionService.createTransaction(anyLong(), any(BigDecimal.class))).thenReturn(createdTransaction);

        mockMvc.perform(post("/api/transactions/account/{accountId}", accountId)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                // .andExpect(jsonPath("$.amount").value(amount)); // اگر نیاز به چک کردن محتوا دارید
    }
}
