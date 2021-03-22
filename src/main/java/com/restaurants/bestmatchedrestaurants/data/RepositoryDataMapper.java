package com.restaurants.bestmatchedrestaurants.data;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.model.Cuisine;
import com.restaurants.bestmatchedrestaurants.model.Restaurant;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RepositoryDataMapper {

    public Collection<RestaurantCuisine> map(final Collection<Cuisine> cuisines,
                    Collection<Restaurant> restaurants) {
        Map<Long, String> cuisinesMap = cuisines.stream()
            .collect(Collectors.toMap(Cuisine::getId, Cuisine::getName));

        log.info("Converting model into internal domain...");
        return restaurants.stream()
            .map(r -> new RestaurantCuisine(
                r.getName(), r.getCustomerRating(), r.getDistance(), r.getPrice(), cuisinesMap.get(r.getCuisineId())))
            .collect(Collectors.toList());
    }
}
