package com.restaurants.bestmatchedrestaurants.business;

import java.util.function.BiPredicate;

import org.springframework.stereotype.Component;

@Component
public class RestaurantNameMatcher implements BiPredicate<String, String> {

    @Override
    public boolean test(String restaurantName, String requestName) {
        return restaurantName.equalsIgnoreCase(requestName)
                        || restaurantName.toLowerCase().startsWith(requestName.toLowerCase());
    }
}
