package fr.houssam.kata.account.business;

import fr.houssam.kata.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ghazala on 30/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Spy
    @InjectMocks
    private AccountService accountService;

    @Test
    public void should_make_a_deposit_in_a_given_account() {
        accountService.depose(100L, "10025135");
    }
}