package fr.caujolle.herve.restservices.exceptions.customized;

import java.util.Date;

// Simple custom error details bean
public class CustomErrorsDetails {

    private Date timeStamp;
    private String message;
    private String errorMessage;


    // Fields constructor
    public CustomErrorsDetails(Date timeStamp, String message, String errorMessage) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    //Getters
    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
