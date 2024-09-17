package com.kdu.jpa.exception;

import com.kdu.jpa.dto.error.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling various exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles InvalidEntityException and returns an HTTP UNPROCESSABLE_ENTITY response.
     *
     * @param ex The InvalidEntityException to be handled.
     * @return ResponseEntity containing the error details and HTTP status UNPROCESSABLE_ENTITY.
     */
    @ExceptionHandler(value = {InvalidEntityException.class})
    public ResponseEntity<ErrorDTO> handleInvalidEntityException(InvalidEntityException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles EntityNotExist exception and returns an HTTP NOT_FOUND response.
     *
     * @param ex The EntityNotExist exception to be handled.
     * @return ResponseEntity containing the error details and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(value = {EntityNotExist.class})
    public ResponseEntity<ErrorDTO> handleEntityNotExist(EntityNotExist ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles ItemsNotFound exception and returns an HTTP NOT_FOUND response.
     *
     * @param ex The ItemsNotFound exception to be handled.
     * @return ResponseEntity containing the error details and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(value = {ItemsNotFound.class})
    public ResponseEntity<ErrorDTO> handleItemsNotFound(ItemsNotFound ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles generic Exception and returns an HTTP BAD_REQUEST response.
     *
     * @param ex The generic Exception to be handled.
     * @return ResponseEntity containing the error details and HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
