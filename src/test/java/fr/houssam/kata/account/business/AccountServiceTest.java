package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Customer;
import fr.houssam.kata.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Mockito.doReturn(Account.builder()
                .id(1L)
                .numero("10025135")
                .customer(new Customer())
                .solde(1000L)
                .build()).when(accountRepository).findByNumero("10025135");

        accountService.depose(100L, "10025135");

        Mockito.verify(accountRepository, Mockito.times(1)).findByNumero("10025135");
        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any(Account.class));
    }
}