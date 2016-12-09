package fr.houssam.kata.account.business;

import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ghazala on 30/11/16.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account depose(Amount deposit, Account account) {
        Account accountToUpdate = Account.builder().id(account.getId())
                .solde(account.getSolde() + deposit.getValue())
                .numero(account.getNumero())
                .customer(account.getCustomer()).build();

        return accountRepository.save(accountToUpdate);
    }

    public Optional<Account> fetchByNumero(String accountNumero) {
        return Optional.of(accountRepository.findByNumero(accountNumero));
    }

    public Account withdraw(Amount amountWithdraw, Account account) {
        return null;
    }
}
