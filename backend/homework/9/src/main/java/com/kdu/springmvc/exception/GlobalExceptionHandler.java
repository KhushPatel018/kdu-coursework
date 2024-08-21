package com.kdu.springmvc.exception;

import com.azure.core.exception.ResourceNotFoundException;
import com.kdu.springmvc.dto.response.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling specific exceptions and providing appropriate responses.
 */
@ControllerAdvice
@Profile("dev")
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles the exception when a vehicle with the same ID already exists.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing an error message and HTTP status BAD_REQUEST (400).
     */
    @ExceptionHandler(VehicleAlreadyExist.class)
    public ResponseEntity<String> handleVehicleAlreadyExist(VehicleAlreadyExist ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(), HttpStatus.BAD_REQUEST.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the exception when a requested vehicle is not found.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing an error message and HTTP status NOT_FOUND (404).
     */
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(), HttpStatus.NOT_FOUND.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the general resource not found exception.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing an error message and HTTP status INTERNAL_SERVER_ERROR (500).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the general bad request exception.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing an error message and HTTP status INTERNAL_SERVER_ERROR (500).
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles generic exceptions.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing an error message and HTTP status INTERNAL_SERVER_ERROR (500).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
