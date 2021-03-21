package com.restaurants.bestmatchedrestaurants.business.validator;

import java.math.BigDecimal;

import com.restaurants.bestmatchedrestaurants.domain.ErrorType;
import com.restaurants.bestmatchedrestaurants.exception.ClientErrorException;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestParametersValidator {

    public void validate(final String name, final Integer customerRating, final Integer distance,
                    final BigDecimal price, final String cuisine) {
        log.info("Validating request parameters...");
        if (name == null && customerRating == null && distance == null && price == null && cuisine == null) {
            throw new ClientErrorException(ErrorType.VALIDATION, "At least one parameter should be informed.");
        }
        if (name != null && name.isBlank()) {
            throw new ClientErrorException(ErrorType.VALIDATION, "Restaurant name should not be empty.");
        }
        if (customerRating != null && customerRating.intValue() < 0) {
            throw new ClientErrorException(ErrorType.VALIDATION, "Customer rating should not be negative.");
        }
        if (distance != null && distance.intValue() < 0) {
            throw new ClientErrorException(ErrorType.VALIDATION, "Distance rating should not be negative.");
        }
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new ClientErrorException(ErrorType.VALIDATION, "Price must be a positive number.");
        }
        if (cuisine != null && cuisine.isBlank()) {
            throw new ClientErrorException(ErrorType.VALIDATION, "Cuisine should not be empty.");
        }
        log.info("Request parameters successfully validated.");
    }
}
