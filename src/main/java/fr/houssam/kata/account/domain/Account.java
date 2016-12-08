package fr.houssam.kata.account.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ghazala on 30/11/16.
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String numero;

    private long solde;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
