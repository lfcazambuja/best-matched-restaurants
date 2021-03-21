package com.restaurants.bestmatchedrestaurants.web;

import java.math.BigDecimal;
import java.util.Collection;

import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;

import org.springframework.http.ResponseEntity;

public interface RestaurantsApi {

    ResponseEntity<Collection<RestaurantCuisine>> find(
                    String name, Integer customerRating, Integer distance, BigDecimal price, String cuisine);
}
