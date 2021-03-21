package com.restaurants.bestmatchedrestaurants.business;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantNameMatcherTest {

    @InjectMocks private RestaurantNameMatcher matcher;

    @Test
    public void shouldMatchNamesWhenValuesAreEqual() {
        //given
        String restaurantName = "KFC";
        String parameterName = "KFC";

        //when
        boolean isValid = matcher.test(restaurantName, parameterName);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldMatchNamesWhenValuesAreEqualIgnoringCase() {
        //given
        String restaurantName = "KFC";
        String parameterName = "kfc";

        //when
        boolean isValid = matcher.test(restaurantName, parameterName);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldMatchNamesWhenRestaurantCuisineStartsWithParameterIgnoringCase() {
        //given
        String restaurantName = "KFC";
        String parameterName = "KF";

        //when
        boolean isValid = matcher.test(restaurantName, parameterName);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldNotMatchNamesWhenValuesAreDifferentAndRestaurantNameDoesNotStartWithParameter() {
        //given
        String restaurantName = "KFC";
        String parameterName = "Carmine's";

        //when
        boolean isValid = matcher.test(restaurantName, parameterName);

        //then
        assertFalse(isValid);
    }
}
