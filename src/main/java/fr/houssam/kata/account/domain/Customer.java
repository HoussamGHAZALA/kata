package fr.houssam.kata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by ghazala on 30/11/16.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
@Builder
public class Customer {

    @Id
    @GeneratedValue
    private final Long id;

    private final String nom;

    private final String prenom;

    @OneToOne(fetch = LAZY)
    private final Account account;
}
