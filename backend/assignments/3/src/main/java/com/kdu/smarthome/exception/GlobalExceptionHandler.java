package com.kdu.smarthome.exception;

import com.kdu.smarthome.dto.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions thrown by controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles EntityNotFoundException and returns a ResponseEntity with an ErrorDTO.
     *
     * @param ex The EntityNotFoundException thrown.
     * @return ResponseEntity containing the ErrorDTO.
     */
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleDataNotFoundException(EntityNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles UnprocessableEntityException and returns a ResponseEntity with an ErrorDTO.
     *
     * @param ex The UnprocessableEntityException thrown.
     * @return ResponseEntity containing the ErrorDTO.
     */
    @ExceptionHandler(value = {UnprocessableEntityException.class})
    public ResponseEntity<ErrorDTO> handleUnProcessableEntityException(UnprocessableEntityException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles UnauthorizedUserAccessException and returns a ResponseEntity with an ErrorDTO.
     *
     * @param ex The UnauthorizedUserAccessException thrown.
     * @return ResponseEntity containing the ErrorDTO.
     */
    @ExceptionHandler(value = {UnauthorizedUserAccessException.class})
    public ResponseEntity<ErrorDTO> handleUnauthorizedAccessException(UnauthorizedUserAccessException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles DataRetrievalFailedException and returns a ResponseEntity with an ErrorDTO.
     *
     * @param ex The DataRetrievalFailedException thrown.
     * @return ResponseEntity containing the ErrorDTO.
     */
    @ExceptionHandler(value = {DataRetrievalFailedException.class})
    public ResponseEntity<ErrorDTO> handleFetchFailedException(DataRetrievalFailedException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    /**
     * Handles generic exceptions and returns a ResponseEntity with an ErrorDTO.
     *
     * @param ex The Exception thrown.
     * @return ResponseEntity containing the ErrorDTO.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
