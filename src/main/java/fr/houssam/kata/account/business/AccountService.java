package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.account.domain.OperationType;
import fr.houssam.kata.account.repository.AccountRepository;
import fr.houssam.kata.exception.AccountNotFoundException;
import fr.houssam.kata.exception.SoldeInsuffisantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by ghazala on 30/11/16.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Optional<Account> fetchByNumero(String accountNumero) {
        return Optional.of(accountRepository.findByNumero(accountNumero));
    }

    @Transactional
    public Account updateSolde(Amount amount, String accountNum, OperationType deposit) {
        Account account = fetchByNumero(accountNum)
                .orElseThrow(() -> new AccountNotFoundException("Account not found, please check your account numero"));
        switch (deposit) {
            case DEPOSIT:
                return accountRepository.save(Account.builder().id(account.getId())
                        .solde(account.getSolde() + amount.getValue())
                        .numero(account.getNumero())
                        .customer(account.getCustomer()).build());
            case WITHDRAWL:
                if(amount.getValue() > account.getSolde()) {
                    throw new SoldeInsuffisantException("Votre solde est insuffisant, l'opération est annulée.");
                }
                return accountRepository.save(Account.builder().id(account.getId())
                        .solde(account.getSolde() - amount.getValue())
                        .numero(account.getNumero())
                        .customer(account.getCustomer()).build());
            default:
                throw new IllegalArgumentException("Le type d'opération donné ne correspondant à aucun type.");
        }
    }
}
