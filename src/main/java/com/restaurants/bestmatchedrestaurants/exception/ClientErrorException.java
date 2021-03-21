package com.restaurants.bestmatchedrestaurants.exception;

import static java.util.Optional.ofNullable;

import com.restaurants.bestmatchedrestaurants.domain.ErrorResponse;
import com.restaurants.bestmatchedrestaurants.domain.ErrorType;

public class ClientErrorException extends AbstractErrorException {

    private static final long serialVersionUID = 1486297407038116871L;

    private final ErrorType errorType;

    public ClientErrorException(ErrorType errorType, String msg) {
        super(msg);
        this.errorType = errorType;
    }

    public ClientErrorException(final ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorType = errorResponse.getErrorType();
        ofNullable(errorResponse.getDetails()).ifPresent((details) -> this.getDetails().putAll(details));
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }

}
