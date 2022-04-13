package fr.caujolle.herve.restservices.exceptions;

public class UserNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    // Superclass constructor
    public UserNotFoundException(String message) {
        super(message);
    }
}
