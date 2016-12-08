package fr.houssam.kata.account.domain;

import lombok.Builder;
import lombok.Value;

/**
 * Created by ghazala on 08/12/16.
 */
@Value
@Builder
public class Amount {
    private long value;

    public Amount(long value) {
        this.value = validate(value);
    }

    private long validate(long value) {
        if(value > 0) {
            return value;
        }
        throw new IllegalArgumentException("Amount should be positif");
    }
}
