package com.restaurants.bestmatchedrestaurants.business;

import java.util.Comparator;

import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;

public class RestaurantCuisineComparator implements Comparator<RestaurantCuisine> {

    @Override
    public int compare(RestaurantCuisine o1, RestaurantCuisine o2) {
        return Comparator.comparingInt(RestaurantCuisine::getDistance)
            .thenComparing(RestaurantCuisine::getCustomerRating, Comparator.reverseOrder())
            .thenComparing(RestaurantCuisine::getPrice)
            .thenComparing(RestaurantCuisine::getName)
            .compare(o1, o2);
    }
}
