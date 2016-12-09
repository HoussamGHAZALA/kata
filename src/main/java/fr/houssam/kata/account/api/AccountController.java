package fr.houssam.kata.account.api;

import fr.houssam.kata.account.business.AccountService;
import fr.houssam.kata.account.business.OperationService;
import fr.houssam.kata.account.domain.Account;
import fr.houssam.kata.account.domain.Amount;
import fr.houssam.kata.account.domain.Operation;
import fr.houssam.kata.account.domain.OperationType;
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

    @Autowired
    private OperationService operationService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.PUT,
            path = "/api/accounts/{accountNumero}/operations/{operationType}")
    public Account update(@PathVariable String accountNumero,
                          @PathVariable OperationType operationType,
                          @RequestBody Amount amount) {
        return accountService.updateSolde(amount, accountNumero, operationType);
    }

    @RequestMapping(method = RequestMethod.GET,
            path = "/api/accounts/{accountNumero}/history")
    public List<Operation> getAccountHistory(@PathVariable String accountNumero) {
        return operationService.fetchBy(accountNumero);
    }
}
