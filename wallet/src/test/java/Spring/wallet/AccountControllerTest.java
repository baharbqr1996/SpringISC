package Spring.wallet;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import Spring.wallet.controller.AccountController;
import Spring.wallet.model.Account;
import Spring.wallet.service.AccountService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void createAccount_success() throws Exception {
        Account account = new Account("12345678901234567890");
        account.setShabaNumber("IR123456789012345678901234");
        account.setBalance(new BigDecimal("10000"));
       // account.setCreationDate(LocalDate.now());

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(account)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value("12345678901234567890"));
    }
    
    @Test
    public void testDeposit_Success() throws Exception {
        mockMvc.perform(post("/api/accounts/6/deposit")
                .param("amount", "1000"))
                .andExpect(status().isOk());

        verify(accountService).deposit(6L, BigDecimal.valueOf(1000));
    }

    @Test
    public void testWithdraw_Success() throws Exception {
        mockMvc.perform(post("/api/accounts/6/withdraw")
                .param("amount", "500"))
                .andExpect(status().isOk());

        verify(accountService).withdraw(6L, BigDecimal.valueOf(500));
    }
}
