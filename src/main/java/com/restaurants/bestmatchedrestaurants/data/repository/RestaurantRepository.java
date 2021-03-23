package com.restaurants.bestmatchedrestaurants.data.repository;

import com.restaurants.bestmatchedrestaurants.data.entity.RestaurantEntity;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Long> {

}
