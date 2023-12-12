package fr.dawan.demobank.exceptions;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String target) {
        super("Le compte de %s est introuvable".formatted(target));
    }
}
