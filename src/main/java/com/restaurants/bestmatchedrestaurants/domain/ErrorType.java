package com.restaurants.bestmatchedrestaurants.domain;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorType {

    BUSINESS(HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    VALIDATION(HttpStatus.BAD_REQUEST);

    private final HttpStatus httpStatus;

}
