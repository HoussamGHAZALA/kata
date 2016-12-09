package fr.houssam.kata.account.api;

import fr.houssam.kata.account.business.AccountService;
import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.account.domain.Operation;
import fr.houssam.kata.account.domain.OperationType;
import fr.houssam.kata.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ghazala on 30/11/16.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/accounts/{accountNumero}/operations/{operationType}")
    public Account update(@PathVariable String accountNumero, @PathVariable OperationType operationType, @RequestBody Amount amount) {
        Account account = accountService.fetchByNumero(accountNumero)
                .orElseThrow(() -> new AccountNotFoundException("Account not found, please check your account numero"));
        switch (operationType) {
            case  DEPOSIT: return accountService.depose(amount, account);
            case  WITHDRAWL: return accountService.withdraw(amount, account);
        }
        throw new IllegalArgumentException("Le type d'opération donné ne correspondant à aucun type.");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/accounts/{accountNumero}/history")
    public List<Operation> getAccountHistory(@PathVariable String accountNumero) {
        return null;
    }
}
