package fr.caujolle.herve.restservices.exceptions;

import java.io.Serial;
import java.io.Serializable;

/**
 * Un User est unique : username ==> unique
 */
public class UserExistsException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 4163232221638238745L;

    public UserExistsException(String message) {
        super(message);
    }
}
