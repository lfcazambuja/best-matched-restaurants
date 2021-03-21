package com.restaurants.bestmatchedrestaurants.domain;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restaurants.bestmatchedrestaurants.exception.AbstractErrorException;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 244955725533939654L;

    private final ErrorType errorType;
    private final String message;
    private final Map<String, String> details;

    public static ErrorResponse build(final Exception ex) {
        if (ex instanceof AbstractErrorException) {
            return build((AbstractErrorException) ex);
        }
        return buildDefault();
    }

    public static ErrorResponse build(final AbstractErrorException ex) {
        return ErrorResponse.builder()
                        .errorType(ex.getErrorType())
                        .message(ex.getMessage())
                        .details(!ex.getDetails().isEmpty() ? ex.getDetails() : null)
                        .build();
    }

    public static ErrorResponse buildDefault() {
        return ErrorResponse.builder()
                        .errorType(ErrorType.INTERNAL_ERROR)
                        .message("Unexpected error.")
                        .build();
    }

}
