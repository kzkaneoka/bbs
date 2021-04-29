package com.github.kzkaneoka.bbs.error;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<JsonResponse> handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error("ConstraintViolationException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Resource already exists"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<JsonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("MethodArgumentNotValidException: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Invalid inputs"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        LOGGER.error("Exception: {}", e.getMessage());
        return new ResponseEntity<JsonResponse>(new JsonResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
