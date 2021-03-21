package com.restaurants.bestmatchedrestaurants.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import com.restaurants.bestmatchedrestaurants.domain.ErrorType;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.exception.ClientErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class RestaurantsFilter {

    @Autowired
    private RestaurantNameMatcher restaurantNameMatcher;
    @Autowired
    private RestaurantCustomerRatingMatcher restaurantCustomerRatingMatcher;
    @Autowired
    private RestaurantDistanceMatcher restaurantDistanceMatcher;
    @Autowired
    private RestaurantPriceMatcher restaurantPriceMatcher;
    @Autowired
    private RestaurantCuisineMatcher restaurantCuisineMatcher;

    public Predicate<RestaurantCuisine> buildFilter(final String name, final Integer customerRating,
                    final Integer distance, final BigDecimal price, final String cuisine) {
        Predicate<RestaurantCuisine> findByName = r -> restaurantNameMatcher.test(r.getName(), name);
        Predicate<RestaurantCuisine> findByCustomerRating
            = r -> restaurantCustomerRatingMatcher.test(Integer.valueOf(r.getCustomerRating()), customerRating);
        Predicate<RestaurantCuisine> findByDistance
            = r -> restaurantDistanceMatcher.test(Integer.valueOf(r.getDistance()), distance);
        Predicate<RestaurantCuisine> findByPrice = r -> restaurantPriceMatcher.test(r.getPrice(), price);
        Predicate<RestaurantCuisine> findByCuisine = r -> restaurantCuisineMatcher.test(r.getCuisine(), cuisine);
        Collection<Predicate<RestaurantCuisine>> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(name)) {
            predicates.add(findByName);
        }
        if (!ObjectUtils.isEmpty(customerRating)) {
            predicates.add(findByCustomerRating);
        }
        if (!ObjectUtils.isEmpty(distance)) {
            predicates.add(findByDistance);
        }
        if (!ObjectUtils.isEmpty(price)) {
            predicates.add(findByPrice);
        }
        if (!ObjectUtils.isEmpty(cuisine)) {
            predicates.add(findByCuisine);
        }
        return predicates.stream().reduce(Predicate::and).orElseThrow(
            () -> new ClientErrorException(ErrorType.VALIDATION, "Error defining filter."));
    }
}
