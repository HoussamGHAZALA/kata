package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.account.domain.Customer;
import fr.houssam.kata.account.repository.AccountRepository;
import fr.houssam.kata.exception.SoldeInsuffisantException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static fr.houssam.kata.account.domain.OperationType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

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
    public void should_update_account_solde_deposit_case() {
        Customer custom = Customer.builder().id(1L).build();
        Account account = Account.builder()
                .id(1L)
                .numero("1000555")
                .customer(custom)
                .solde(1000L).build();
        Account updatedAccount = Account.builder()
                .id(1L)
                .numero("1000555")
                .customer(custom)
                .solde(1100L).build();
        Amount amount = new Amount(100L);
        doReturn(account).when(accountRepository).findByNumero("1000555");
        doReturn(updatedAccount).when(accountRepository).save(updatedAccount);

        Account result = accountService.updateSolde(amount, "1000555", DEPOSIT);

        assertThat(result).isEqualToComparingFieldByField(updatedAccount);
    }

    @Test
    public void should_update_account_solde_withdrawl_case() {
        Customer custom = Customer.builder().id(1L).build();
        Account account = Account.builder()
                .id(1L)
                .numero("1000555")
                .customer(custom)
                .solde(1000L).build();
        Account updatedAccount = Account.builder()
                .id(1L)
                .numero("1000555")
                .customer(custom)
                .solde(900L).build();
        Amount amount = new Amount(100L);
        doReturn(account).when(accountRepository).findByNumero("1000555");
        doReturn(updatedAccount).when(accountRepository).save(updatedAccount);

        Account result = accountService.updateSolde(amount, "1000555", WITHDRAWL);

        assertThat(result).isEqualToComparingFieldByField(updatedAccount);
    }

    @Test
    public void should_not_authorize_customer_to_make_withdraw_if_his_solde_is_less_then_amount() {
        Customer custom = Customer.builder().id(1L).build();
        Account account = Account.builder()
                .id(1L)
                .numero("1000555")
                .customer(custom)
                .solde(1000L).build();
        doReturn(account).when(accountRepository).findByNumero("1000555");

        assertThatThrownBy(() -> accountService.updateSolde(new Amount(1100L), "1000555", WITHDRAWL))
                .isInstanceOf(SoldeInsuffisantException.class);
    }
}
