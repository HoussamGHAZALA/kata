package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Query;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ghazala on 01/12/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    private Account expectedAccount;
    private Customer customer;

    @Before
    public void init() {
        customer = Customer.builder().nom("TOTO").prenom("TATA").build();
        expectedAccount = Account.builder()
                .numero("11005BC589")
                .solde(500L)
                .customer(customer)
                .build();
        testEntityManager.persist(customer);
        testEntityManager.persist(expectedAccount);

    }

    @Test
    public void should_fetch_account_by_numero() {
        Account resulting = accountRepository.findByNumero("11005BC589");

        assertThat(resulting)
                .isNotNull()
                .isEqualToComparingFieldByField(expectedAccount);
    }

    @Test
    public void should_add_deposit_in_account_numero() throws Exception {
        Account accountWithNewSolde = Account.builder()
                .customer(customer)
                .numero("11005BC599")
                .solde(950L)
                .build();

        accountRepository.saveAndFlush(accountWithNewSolde);

        Account actual = (Account) testEntityManager.getEntityManager()
                .createQuery("SELECT OBJECT(account) FROM Account account WHERE numero = '11005BC599' ")
                .getSingleResult();

        assertThat(actual
                .getSolde())
                .isEqualTo(950L);
    }
}
