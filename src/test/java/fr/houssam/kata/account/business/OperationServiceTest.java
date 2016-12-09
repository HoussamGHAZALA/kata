package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Customer;
import fr.houssam.kata.account.domain.Operation;
import fr.houssam.kata.account.repository.AccountRepository;
import fr.houssam.kata.account.repository.OperationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static fr.houssam.kata.account.domain.OperationType.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

/**
 * Created by ghazala on 09/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;
    @Mock
    private AccountRepository accountRepository;

    @Spy
    @InjectMocks
    private OperationService operationService;

    private Customer customer;
    private Operation depot;
    private Operation retrait;
    private Account account;

    @Before
    public void init() {
        customer = Customer.builder().nom("TOTO").prenom("TATA").build();
        account = Account.builder()
                .numero("11005BC589")
                .solde(500L)
                .customer(customer)
                .build();
        depot = Operation.builder()
                .amount(50L)
                .date(new Date(1111L))
                .account(account)
                .operationType(DEPOSIT).build();
        retrait = Operation.builder()
                .amount(300L)
                .date(new Date(22222L))
                .account(account)
                .operationType(DEPOSIT).build();
    }

    @Test
    public void should_fetch_all_operations_by_account() throws Exception {
        doReturn(of(depot, retrait)).when(operationRepository).findByAccount(account);
        doReturn(account).when(accountRepository).findByNumero("11005BC589");

        List<Operation> operations = operationService.fetchBy("11005BC589");

        assertThat(operations).isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(depot, retrait);
    }
}