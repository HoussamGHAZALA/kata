package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ghazala on 01/12/16.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNumero(String numero);

    Account findByCustomer(Customer customer);
}
