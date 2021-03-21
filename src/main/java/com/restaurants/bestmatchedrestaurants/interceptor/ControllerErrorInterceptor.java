package com.restaurants.bestmatchedrestaurants.interceptor;

import com.restaurants.bestmatchedrestaurants.domain.ErrorResponse;
import com.restaurants.bestmatchedrestaurants.exception.ClientErrorException;
import com.restaurants.bestmatchedrestaurants.exception.ServerErrorException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerErrorInterceptor {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ClientErrorException clientErrorException) {
        ErrorResponse error = ErrorResponse.builder().errorType(clientErrorException.getErrorType())
                        .message(clientErrorException.getMessage()).details(clientErrorException.getDetails()).build();

        log.error("Error {}.", clientErrorException.getMessage());

        return new ResponseEntity<>(error, clientErrorException.getErrorType().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ServerErrorException serverErrorException) {
        ErrorResponse error = ErrorResponse.builder().errorType(serverErrorException.getErrorType())
                        .message(serverErrorException.getMessage()).details(serverErrorException.getDetails()).build();

        log.error("Internal Error {}.", serverErrorException.getMessage());

        return new ResponseEntity<>(error, serverErrorException.getErrorType().getHttpStatus());
    }

}
