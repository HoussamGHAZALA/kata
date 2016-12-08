package fr.houssam.kata.account.api;

import fr.houssam.kata.account.business.AccountService;
import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.PUT, path = "/api/accounts/{accountNumero}")
    public Account update(@PathVariable String accountNumero, @RequestBody Amount amount) {
        Account account = accountService.fetchByNumero(accountNumero)
                .orElseThrow(() -> new AccountNotFoundException("Account not found, please check your account numero"));

        return accountService.depose(amount, account);
    }
}
