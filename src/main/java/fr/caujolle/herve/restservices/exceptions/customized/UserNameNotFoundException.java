package fr.caujolle.herve.restservices.exceptions.customized;

public class UserNameNotFoundException extends Exception{

    private static final long serialVersionUID = 143313131L;

    public UserNameNotFoundException(String message) {
        super(message);
    }
}
