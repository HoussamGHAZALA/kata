package fr.houssam.kata.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ghazala on 09/12/16.
 */
@Entity
@Data
@Builder
public class Operation {

    @Id
    @GeneratedValue
    private final Long id;

    private final Date date;

    private final Long amount;

    private final OperationType operationType;

    @ManyToOne(fetch = FetchType.LAZY)
    private final Account account;
}
