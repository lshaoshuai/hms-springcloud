package com.hms.provider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * @author luoshao
 * @date 2019/5/31 17:54
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception Handler.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("be valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
