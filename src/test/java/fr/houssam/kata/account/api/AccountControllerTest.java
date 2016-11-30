package fr.houssam.kata.account.api;

import fr.houssam.kata.account.business.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by ghazala on 30/11/16.
 */
public class AccountControllerTest {

    private AccountService accountService = Mockito.mock(AccountService.class);

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountController(accountService)).build();
    }

    @Test
    public void should_test_noting() {

    }
}
