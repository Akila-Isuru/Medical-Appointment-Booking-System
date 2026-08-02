package org.example.medical_appointment_booking_system.advisor;

import org.example.medical_appointment_booking_system.exception.DuplicateException;
import org.example.medical_appointment_booking_system.exception.NotFoundException;
import org.example.medical_appointment_booking_system.utill.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(Exception e){
        return new ResponseEntity<>(
                new StandardResponse(404,"error",e.getMessage()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<StandardResponse> handleDuplicateException(Exception e){
        return new ResponseEntity<>(
                new StandardResponse(500,"error",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleException(Exception e){
        return new ResponseEntity<>(
                new StandardResponse(500,"error",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
