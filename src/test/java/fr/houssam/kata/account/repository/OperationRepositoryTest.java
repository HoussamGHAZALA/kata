package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ghazala on 09/12/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OperationRepositoryTest {

    @Autowired
    private OperationRepository operationRepository;

    @Test
    public void should_load_all_operations_by_account() {

    }

}