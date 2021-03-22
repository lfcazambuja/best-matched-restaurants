package com.restaurants.bestmatchedrestaurants.data;

import java.util.Collection;

import com.restaurants.bestmatchedrestaurants.model.Cuisine;
import com.restaurants.bestmatchedrestaurants.model.Restaurant;

public interface Repository {

    Collection<Restaurant> findAllRestaurants();

    Collection<Cuisine> findAllCuisines();
}
