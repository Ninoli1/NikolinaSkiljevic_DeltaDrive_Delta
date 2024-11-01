package com.app.DeltaDrive.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> errors= new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName= ((FieldError)error).getField();
            String errorMessage= error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return "Data integrity violation" +ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return "Validation error: " + ex.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Invalid data provided" + ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchElementException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("Access denied: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        return new ResponseEntity<>("Data access error: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>("Invalid username or password. Please try again.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvocationTargetException.class)
    public ResponseEntity<String> handleInvocationTargetException(InvocationTargetException ex) {
        Throwable cause = ex.getCause();
        String errorMessage;

        if (cause != null) {
            errorMessage = "Error: " + cause.getMessage();
        } else {
            errorMessage = "Unknown Invocation Target Error.";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


}
