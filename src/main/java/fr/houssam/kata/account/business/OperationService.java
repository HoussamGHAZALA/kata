package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Operation;
import fr.houssam.kata.account.repository.AccountRepository;
import fr.houssam.kata.account.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ghazala on 09/12/16.
 */
@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Operation> fetchBy(String accountNumero) {
        Account account = accountRepository.findByNumero(accountNumero);
        return operationRepository.findByAccount(account);
    }
}
