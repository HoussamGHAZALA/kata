package fr.houssam.kata.account.api;

import fr.houssam.kata.account.business.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ghazala on 30/11/16.
 */
@RestController("api/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
}
