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
}
