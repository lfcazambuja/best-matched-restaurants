package com.restaurants.bestmatchedrestaurants.business;

import java.util.function.BiPredicate;

import org.springframework.stereotype.Component;

@Component
public class RestaurantDistanceMatcher implements BiPredicate<Integer, Integer> {

    @Override
    public boolean test(Integer restaurantDistance, Integer requestDistance) {
        return restaurantDistance.intValue() <= requestDistance.intValue();
    }
}
