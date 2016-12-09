package fr.houssam.kata.account.domain;

/**
 * Created by ghazala on 09/12/16.
 */
public enum OperationType {
    DEPOSIT("deposit"),
    WITHDRAWL("withdrawl");

    private String value;

    OperationType(String value) {
        this.value = value;
    }
}
