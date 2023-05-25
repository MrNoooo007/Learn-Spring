package com.example.learnspring.exceptions;

import com.example.learnspring.response.ResponseObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ApiRequestException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        Map<String, String> detailError = new LinkedHashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        responseBody.put("timestamp", new Date());
        responseBody.put("status", status);

        for(FieldError fieldError : fieldErrors) {
            String errorMessage = fieldError.getDefaultMessage();
            detailError.put(fieldError.getField(), errorMessage);
        }
        responseBody.put("errors: ", detailError);

        return new ResponseEntity<>(responseBody, headers, status);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            HttpHeaders headers,
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        return ResponseEntity.ok().body(
                new ResponseObject(ex.getLocalizedMessage(), "errorssss" , errors)
        );
    }

//    Handle unique custom error message
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String errorMessage = "An error occurred while processing your request.";
        if(ex.getRootCause().getMessage().contains("Duplicate")) {
            errorMessage = "The record already exists.";
        }
        return ResponseEntity.badRequest().body(
            new ResponseObject("Errors", HttpStatus.INTERNAL_SERVER_ERROR.toString(), errorMessage)
        );
    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//
//        System.out.println(ex.toString());
//        return ResponseEntity.ok().body(
//                new ResponseObject("error", "errors" , ex.getRootCause().getMessage())
//        );
//    }
}
