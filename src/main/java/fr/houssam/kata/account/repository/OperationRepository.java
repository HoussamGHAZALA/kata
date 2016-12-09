package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ghazala on 09/12/16.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByAccount(Account account);
}
