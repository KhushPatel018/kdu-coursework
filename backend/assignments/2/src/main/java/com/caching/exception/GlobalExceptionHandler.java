package com.caching.exception;

import com.caching.dto.response.ErrorDTO;
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

    /**
     * Handles AddressNotFound exception.
     *
     * @param ex AddressNotFound exception.
     * @return ResponseEntity with the error details and HTTP status.
     */
    @ExceptionHandler(AddressNotFound.class)
    public ResponseEntity<String> handleAddressNotFound(AddressNotFound ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(), HttpStatus.NOT_FOUND.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidAddressException exception.
     *
     * @param ex InvalidAddressException exception.
     * @return ResponseEntity with the error details and HTTP status.
     */
    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<String> handleInvalidAddress(InvalidAddressException ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(), HttpStatus.NOT_FOUND.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles LatitudeLongitudeNotFound exception.
     *
     * @param ex LatitudeLongitudeNotFound exception.
     * @return ResponseEntity with the error details and HTTP status.
     */
    @ExceptionHandler(LatitudeLongitudeNotFound.class)
    public ResponseEntity<String> handleLatitudeLongitudeNotFound(LatitudeLongitudeNotFound ex) {
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
