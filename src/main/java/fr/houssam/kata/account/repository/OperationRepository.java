package fr.houssam.kata.account.repository;

import fr.houssam.kata.account.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ghazala on 09/12/16.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
