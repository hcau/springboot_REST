package fr.caujolle.herve.restservices.exceptions.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorsDetails usernameNotFound(UserNameNotFoundException ex){
        return new CustomErrorsDetails(new Date(),
                "From @RestController not found.",
                ex.getMessage());
    }
}