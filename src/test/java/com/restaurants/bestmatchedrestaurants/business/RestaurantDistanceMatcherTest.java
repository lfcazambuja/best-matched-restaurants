package com.restaurants.bestmatchedrestaurants.business;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantDistanceMatcherTest {

    @InjectMocks private RestaurantDistanceMatcher matcher;

    @Test
    public void shouldMatchRestaurantDistanceWhenValuesAreEqual() {
        //given
        Integer restaurantDistance = Integer.valueOf(4);
        Integer parameterDistance = Integer.valueOf(4);

        //when
        boolean isValid = matcher.test(restaurantDistance, parameterDistance);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldMatchDistanceWhenParameterIsGreaterThanRestaurantDistance() {
        //given
        Integer customerRating = Integer.valueOf(4);
        Integer parameterCustomerRating = Integer.valueOf(5);

        //when
        boolean isValid = matcher.test(customerRating, parameterCustomerRating);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldNotMatchDistanceWhenParameterIsLessThanRestaurantDistance() {
        //given
        Integer restaurantDistance = Integer.valueOf(4);
        Integer parameterDistance = Integer.valueOf(3);

        //when
        boolean isValid = matcher.test(restaurantDistance, parameterDistance);

        //then
        assertFalse(isValid);
    }

}
