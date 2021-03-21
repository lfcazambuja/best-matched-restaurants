package com.restaurants.bestmatchedrestaurants.business;

import java.math.BigDecimal;
import java.util.function.BiPredicate;

import org.springframework.stereotype.Component;

@Component
public class RestaurantPriceMatcher implements BiPredicate<BigDecimal, BigDecimal> {

    @Override
    public boolean test(BigDecimal restaurantPrice, BigDecimal requestPrice) {
        return restaurantPrice.compareTo(requestPrice) <= 0;
    }
}
