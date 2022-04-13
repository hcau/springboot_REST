package fr.caujolle.herve.restservices.exceptions.customized;

import fr.caujolle.herve.restservices.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // On surcharge une méthode de la classe ResponseEntityExceptionHandler
    // A chaque fois qu'une contrainte n'est pas respectée (@Size, @NotEmpty, etc...),
    // la méthode handleMethodArgumentNotValid() est lancée.
    // MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        CustomErrorsDetails customErrorsDetails =  new CustomErrorsDetails(
                new Date(),
                "From MethodArgumentNotValidException in GEH (Global Exception Handler.",
                ex.getMessage());
        return new ResponseEntity<Object>(customErrorsDetails, HttpStatus.BAD_REQUEST);
    }

    // Permet de gérer les cas ou on envoie un PATCH qui n'existe pas dans le contrôleur par exemple.
    @Override
    // HttpRequestMethodNotSupportedException
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        pageNotFoundLogger.warn(ex.getMessage());

        CustomErrorsDetails customErrorsDetails =  new CustomErrorsDetails(
                new Date(),
                "From HttpRequestMethodNotSupportedException in GEH (Global Exception Handler) - Method not allowed.",
                ex.getMessage());
        return new ResponseEntity<Object>(customErrorsDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){

        CustomErrorsDetails customErrorsDetails =  new CustomErrorsDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(customErrorsDetails, HttpStatus.NOT_FOUND);
    }

    //UserNameNotFoundException
    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){

        CustomErrorsDetails customErrorsDetails =  new CustomErrorsDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(customErrorsDetails, HttpStatus.NOT_FOUND);
    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){

        CustomErrorsDetails customErrorsDetails =  new CustomErrorsDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(customErrorsDetails, HttpStatus.BAD_REQUEST);
    }

}
