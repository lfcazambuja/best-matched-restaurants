package com.restaurants.bestmatchedrestaurants.business;

import java.util.function.BiPredicate;

import org.springframework.stereotype.Component;

@Component
public class RestaurantCustomerRatingMatcher implements BiPredicate<Integer, Integer> {

    @Override
    public boolean test(Integer restaurantCustomerRate, Integer requestCustomerRate) {
        return restaurantCustomerRate.intValue() >= requestCustomerRate.intValue();
    }
}
