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


@ControllerAdvice
@Profile("prod")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleAlreadyExist.class)
    public ResponseEntity<String> handleVehicleAlreadyExist(VehicleAlreadyExist ex) {
        ErrorDTO error2 = new ErrorDTO(ex.toString() + "got it",HttpStatus.BAD_REQUEST.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error2.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.toString(),HttpStatus.NOT_FOUND.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(),   HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(),   HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.toString());
        return new ResponseEntity<>(error.toString(),   HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
