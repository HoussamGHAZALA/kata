package fr.houssam.kata.account.domain;

import org.junit.Test;

/**
 * Created by ghazala on 08/12/16.
 */
public class AmmountTest {

    @Test
    public void should_validate_that_amount_is_positif() {
        Amount amount = new Amount(500L);
    }
}