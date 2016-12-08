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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        Customer custom = Customer.builder().id(1L).build();
        Account initialAccount = Account.builder()
                .id(1L)
                .numero("10025135")
                .customer(custom)
                .solde(1000L)
                .build();
        Account updatedAccount = Account.builder()
                .id(1L)
                .numero("10025135")
                .customer(custom)
                .solde(1100L)
                .build();
        doReturn(initialAccount).when(accountRepository).findByNumero("10025135");
        doReturn(updatedAccount).when(accountRepository).save(updatedAccount);

        Account accountWithNewDeposit = accountService.depose(100L, "10025135");

        verify(accountRepository, times(1)).findByNumero("10025135");
        verify(accountRepository, times(1)).save(Mockito.any(Account.class));
        assertThat(accountWithNewDeposit).isEqualToComparingFieldByField(updatedAccount);
    }
}