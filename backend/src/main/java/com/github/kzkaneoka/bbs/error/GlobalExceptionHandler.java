package com.github.kzkaneoka.bbs.error;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<JsonResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        LOGGER.error("DataIntegrityViolationException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Resource already exists"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<JsonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("MethodArgumentNotValidException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Invalid inputs"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {JsonParseException.class})
    public ResponseEntity<JsonResponse> handleJsonParseException(JsonParseException e) {
        LOGGER.error("JsonParseException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Invalid json format"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<JsonResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("HttpMessageNotReadableException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Invalid json format"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
    public ResponseEntity<JsonResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        LOGGER.error("InternalAuthenticationServiceException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Resource not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<JsonResponse> handleNoSuchElementException(NoSuchElementException e) {
        LOGGER.error("NoSuchElementException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Resource not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<JsonResponse> handleAccessDeniedException(AccessDeniedException e) {
        LOGGER.error("AccessDeniedException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Unauthorized request"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<JsonResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Method not allowed"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        LOGGER.error("Exception: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
