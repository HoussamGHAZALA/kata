package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Customer;
import fr.houssam.kata.account.domain.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static fr.houssam.kata.account.domain.OperationType.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ghazala on 09/12/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OperationRepositoryTest {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private TestEntityManager testEntityManager;

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
        testEntityManager.persist(account);
        testEntityManager.persist(customer);
        testEntityManager.persist(depot);
        testEntityManager.persist(retrait);

    }

    @Test
    public void should_load_all_operations_by_account() {
        List<Operation> result = operationRepository.findByAccount(account);

        assertThat(result).isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(depot, retrait);
    }

}