package com.kdu.jdbc.exception;

import com.kdu.jdbc.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions across the application.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFound ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(), HttpStatus.NOT_FOUND.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExist.class)
    public ResponseEntity<String> handleEntityAlreadyExist(EntityAlreadyExist ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(), HttpStatus.NOT_FOUND.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles generic Exception.
     *
     * @param ex Generic Exception.
     * @return ResponseEntity with the error details and HTTP status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
    }
}
