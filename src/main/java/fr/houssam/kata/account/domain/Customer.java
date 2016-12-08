package fr.houssam.kata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by ghazala on 30/11/16.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    private String prenom;

    @OneToOne(fetch = LAZY)
    private Account account;
}
