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
        if (customerRating != null && (customerRating.intValue() < 1 || customerRating.intValue() > 5)) {
            throw new ClientErrorException(
                ErrorType.VALIDATION, "Customer rating should be a number between 1 and 5 (inclusive).");
        }
        if (distance != null && (distance.intValue() < 1 || distance.intValue() > 10)) {
            throw new ClientErrorException(
                ErrorType.VALIDATION, "Distance should be a number between 1 and 10 (inclusive).");
        }
        if (price != null && (price.compareTo(BigDecimal.valueOf(10)) < 0 || price.compareTo(BigDecimal.valueOf(50)) > 0)) {
            throw new ClientErrorException(
                ErrorType.VALIDATION, "Price should be a number between 10 and 50 (inclusive).");
        }
        if (cuisine != null && cuisine.isBlank()) {
            throw new ClientErrorException(ErrorType.VALIDATION, "Cuisine should not be empty.");
        }
        log.info("Request parameters successfully validated.");
    }
}
