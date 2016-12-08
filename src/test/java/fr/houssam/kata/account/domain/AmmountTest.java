package fr.houssam.kata.account.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by ghazala on 08/12/16.
 */
public class AmmountTest {

    @Test
    public void should_validate_that_amount_is_positif() {
        Amount amount = new Amount(500L);

        assertThat(amount.getValue()).isEqualTo(500L);
    }

    @Test
    public void should_throw_exception_when_value_negatif() throws Exception {
        assertThatThrownBy(() -> new Amount(-550L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount should be positif");

    }
}