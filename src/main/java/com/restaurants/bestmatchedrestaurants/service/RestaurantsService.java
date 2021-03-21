package com.restaurants.bestmatchedrestaurants.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.restaurants.bestmatchedrestaurants.business.RestaurantsFilter;
import com.restaurants.bestmatchedrestaurants.business.validator.RequestParametersValidator;
import com.restaurants.bestmatchedrestaurants.dataloader.DataLoader;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RestaurantsService {

    private static final int RESTAURANT_COLLECTION_MAX_NUMBER = 5;

    @Autowired
    private DataLoader dataLoader;
    @Autowired
    private RequestParametersValidator validator;
    @Autowired
    private RestaurantsFilter restaurantFilter;

    @Cacheable("restaurants")
    public Collection<RestaurantCuisine> findRestaurants(final String name, final Integer customerRating,
                    final Integer distance, final BigDecimal price, final String cuisine) {
        validator.validate(name, customerRating, distance, price, cuisine);

        Predicate<RestaurantCuisine> restaurantsFilter = restaurantFilter.buildFilter(
                        name, customerRating, distance, price, cuisine);

        return dataLoader.getRestaurantCuisine().stream()
                        .filter(restaurantsFilter)
                        .sorted(Comparator.comparingInt(RestaurantCuisine::getDistance)
                                        .thenComparing(RestaurantCuisine::getCustomerRating, Comparator.reverseOrder())
                                        .thenComparing(RestaurantCuisine::getPrice))
                        .limit(RESTAURANT_COLLECTION_MAX_NUMBER)
                        .collect(Collectors.toList());
    }
}
