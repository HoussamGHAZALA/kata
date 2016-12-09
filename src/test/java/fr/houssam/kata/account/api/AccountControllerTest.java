package fr.houssam.kata.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.houssam.kata.account.business.AccountService;
import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.account.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ghazala on 30/11/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_make_deposit_by_account_numero() throws Exception {
        Account account = Account.builder()
                .id(1L)
                .customer(Customer.builder()
                        .id(1L).build()
                )
                .solde(1050L)
                .numero("1000236").build();
        Amount amount = new Amount(50L);
        doReturn(account).when(accountService).depose(amount, account);
        doReturn(Optional.of(account)).when(accountService).fetchByNumero("1000236");

        mockMvc.perform(put("/api/accounts/1000236/operations/deposit/")
                .content(mapper.writeValueAsString(amount))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                "{'id':1, 'customer': {'id':1}, 'solde': 1050, 'numero': '1000236'}"
                        )
                );

        verify(accountService, times(1)).depose(amount, account);
    }

    @Test
    public void should_make_withdraw_by_account_numero() throws Exception {

    }
}
