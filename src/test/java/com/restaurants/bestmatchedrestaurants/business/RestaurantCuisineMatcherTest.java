package com.restaurants.bestmatchedrestaurants.business;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantCuisineMatcherTest {

    @InjectMocks private RestaurantCuisineMatcher matcher;

    @Test
    public void shouldMatchCuisinesWhenValuesAreEqual() {
        //given
        String restaurantCuisine = "Chinese";
        String parameterCuisine = "Chinese";

        //when
        boolean isValid = matcher.test(restaurantCuisine, parameterCuisine);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldMatchCuisinesWhenValuesAreEqualIgnoringCase() {
        //given
        String restaurantCuisine = "Chinese";
        String parameterCuisine = "CHINESE";

        //when
        boolean isValid = matcher.test(restaurantCuisine, parameterCuisine);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldMatchCuisinesWhenRestaurantCuisineStartsWithParameterIgnoringCase() {
        //given
        String restaurantCuisine = "Chinese";
        String parameterCuisine = "chi";

        //when
        boolean isValid = matcher.test(restaurantCuisine, parameterCuisine);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldNotMatchCuisinesWhenValuesAreDifferentAndRestaurantCuisineDoesNotStartWithParameter() {
        //given
        String restaurantCuisine = "Chinese";
        String parameterCuisine = "Portuguese";

        //when
        boolean isValid = matcher.test(restaurantCuisine, parameterCuisine);

        //then
        assertFalse(isValid);
    }
}
