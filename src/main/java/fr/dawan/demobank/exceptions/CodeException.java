package fr.dawan.demobank.exceptions;

import java.util.Map;

public class CodeException extends Exception {
    public enum Type {NULL,DIFFERENT}
    public CodeException(Type type) {
        super(message.get(type));
    }

    private static final Map<Type,String> message = Map.of(
            Type.NULL, "Le code n'a pas encore été généré pour ce compte, veuillez utiliser la commande dédiée.",
            Type.DIFFERENT, "Code erroné, veuillez réessayer."
    );
}
