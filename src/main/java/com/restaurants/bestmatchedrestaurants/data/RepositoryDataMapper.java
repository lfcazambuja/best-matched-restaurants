package com.restaurants.bestmatchedrestaurants.data;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.restaurants.bestmatchedrestaurants.data.entity.RestaurantEntity;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RepositoryDataMapper {

    public Collection<RestaurantCuisine> map(Iterable<RestaurantEntity> restaurants) {
        log.info("Converting database model into internal domain...");
        return StreamSupport.stream(restaurants.spliterator(), false)
            .map(r -> new RestaurantCuisine(
                r.getName(), r.getCustomerRating(), r.getDistance(), r.getPrice(), r.getCuisine().getName()))
            .collect(Collectors.toList());
    }
}
