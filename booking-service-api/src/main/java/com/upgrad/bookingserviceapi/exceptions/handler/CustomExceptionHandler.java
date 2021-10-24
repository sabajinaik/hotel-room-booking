package com.upgrad.bookingserviceapi.exceptions.handler;

import com.upgrad.bookingserviceapi.dto.ErrorResponse;
import com.upgrad.bookingserviceapi.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    //tbd
    private final String BAD_REQUEST = "400";

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getLocalizedMessage())
                .statusCode(BAD_REQUEST)
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getLocalizedMessage())
                .statusCode(BAD_REQUEST)
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
