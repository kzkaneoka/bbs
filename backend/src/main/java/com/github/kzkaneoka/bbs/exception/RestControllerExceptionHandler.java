package com.github.kzkaneoka.bbs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@ControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<Object> handleNotSuchElementException(NoSuchElementException e) {
        LOGGER.error("NoSuchElementException: {}", e.getMessage());
        return new ResponseEntity<Object>("Recource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        LOGGER.error("DataIntegrityViolationException: {}", e.getMessage());
        return new ResponseEntity<Object>("Invalid inputs", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        LOGGER.error("EmptyResultDataAccessException: {}", e.getMessage());
        return new ResponseEntity<Object>("Invalid inputs", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return new ResponseEntity<Object>("Invalid request method", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception e) {
        LOGGER.error("Exception: {}", e.getMessage());
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
