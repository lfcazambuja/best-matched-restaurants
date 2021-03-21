package com.restaurants.bestmatchedrestaurants.business;

import java.util.function.BiPredicate;

import org.springframework.stereotype.Component;

@Component
public class RestaurantCuisineMatcher implements BiPredicate<String, String> {

    @Override
    public boolean test(String restaurantCuisine, String requestCuisine) {
        return restaurantCuisine.equalsIgnoreCase(requestCuisine)
                        || restaurantCuisine.toLowerCase().startsWith(requestCuisine.toLowerCase());
    }
}
