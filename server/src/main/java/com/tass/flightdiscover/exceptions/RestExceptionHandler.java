package com.tass.flightdiscover.exceptions;

import com.tass.flightdiscover.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {CityNotFoundException.class, FlightNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        var message = "Internal error";
        log.error(message, ex);
        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BindException.class)
    protected ResponseEntity<ErrorResponse> handleException(BindException ex, WebRequest request) {
        var errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.info("Request errors: {}", errors);
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.NOT_FOUND);
    }
}
