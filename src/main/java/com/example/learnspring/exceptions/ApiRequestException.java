package com.example.learnspring.exceptions;

import com.example.learnspring.response.ResponseObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
}
