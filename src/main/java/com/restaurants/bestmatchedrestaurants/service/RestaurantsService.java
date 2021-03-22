package com.restaurants.bestmatchedrestaurants.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.restaurants.bestmatchedrestaurants.business.RestaurantCuisineComparator;
import com.restaurants.bestmatchedrestaurants.business.RestaurantsFilter;
import com.restaurants.bestmatchedrestaurants.business.validator.RequestParametersValidator;
import com.restaurants.bestmatchedrestaurants.data.DataRepository;
import com.restaurants.bestmatchedrestaurants.data.RepositoryDataMapper;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.model.Cuisine;
import com.restaurants.bestmatchedrestaurants.model.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RestaurantsService {

    private static final int RESTAURANT_COLLECTION_MAX_NUMBER = 5;

    @Autowired
    private RequestParametersValidator validator;
    @Autowired
    private RestaurantsFilter restaurantFilter;
    @Autowired
    private DataRepository repository;
    @Autowired
    private RepositoryDataMapper dataMapper;

    @Cacheable("restaurants")
    public Collection<RestaurantCuisine> findRestaurants(final String name, final Integer customerRating,
                    final Integer distance, final BigDecimal price, final String cuisine) {
        validator.validate(name, customerRating, distance, price, cuisine);

        Predicate<RestaurantCuisine> restaurantsFilter = restaurantFilter.buildFilter(
                        name, customerRating, distance, price, cuisine);

        Collection<Cuisine> cuisines = repository.findAllCuisines();
        Collection<Restaurant> restaurants = repository.findAllRestaurants();
        Collection<RestaurantCuisine> restaurantCollection = dataMapper.map(cuisines, restaurants);

        return restaurantCollection.stream()
                        .filter(restaurantsFilter)
                        .sorted(new RestaurantCuisineComparator())
                        .limit(RESTAURANT_COLLECTION_MAX_NUMBER)
                        .collect(Collectors.toList());
    }
}
