package com.github.walkgs.springtests.resources.exceptions;

import com.github.walkgs.springtests.services.exceptions.ResourceIntegrityViolationException;
import com.github.walkgs.springtests.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<SimpleError> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        final SimpleError error = new SimpleError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ResourceIntegrityViolationException.class)
    public ResponseEntity<SimpleError> entityNotFound(ResourceIntegrityViolationException exception, HttpServletRequest request) {
        final SimpleError error = new SimpleError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_GATEWAY.value());
        error.setError("Data exception");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }

}
