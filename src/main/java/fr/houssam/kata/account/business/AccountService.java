package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ghazala on 30/11/16.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void depose(long deposit, String accountNumero) {
        Account byNumero = accountRepository.findByNumero(accountNumero);
        Long newSolde = byNumero.getSolde() + deposit;

        Account accountToUpdate = Account.builder().id(byNumero.getId())
                .solde(newSolde)
                .numero(accountNumero)
                .customer(byNumero.getCustomer()).build();

        accountRepository.save(accountToUpdate);
    }
}
