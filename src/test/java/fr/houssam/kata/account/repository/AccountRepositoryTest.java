package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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
    private Account expected;

    @Before
    public void init() {
        expected = Account.builder()
                .numero("11005BC589").build();
        testEntityManager.persist(expected);
    }

    @Test
    public void should_fetch_account_by_numero() {
        Account resulting = accountRepository.findByNumero("11005BC589");

        assertThat(resulting)
                .isNotNull()
                .isEqualToComparingFieldByField(expected);
    }
}
