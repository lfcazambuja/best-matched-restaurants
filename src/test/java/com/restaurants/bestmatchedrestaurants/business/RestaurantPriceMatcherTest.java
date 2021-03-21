package com.restaurants.bestmatchedrestaurants.business;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantPriceMatcherTest {

    @InjectMocks private RestaurantPriceMatcher matcher;

    @Test
    public void shouldMatchRestaurantPriceWhenValuesAreEqual() {
        //given
        BigDecimal restaurantPrice = BigDecimal.valueOf(15);
        BigDecimal parameterPrice = BigDecimal.valueOf(15);

        //when
        boolean isValid = matcher.test(restaurantPrice, parameterPrice);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldMatchPriceWhenParameterIsGreaterThanRestaurantPrice() {
        //given
        BigDecimal restaurantPrice = BigDecimal.valueOf(10);
        BigDecimal parameterPrice = BigDecimal.valueOf(15);

        //when
        boolean isValid = matcher.test(restaurantPrice, parameterPrice);

        //then
        assertTrue(isValid);
    }

    @Test
    public void shouldNotMatchPriceWhenParameterIsLessThanRestaurantPrice() {
        //given
        BigDecimal restaurantPrice = BigDecimal.valueOf(15);
        BigDecimal parameterPrice = BigDecimal.valueOf(10);

        //when
        boolean isValid = matcher.test(restaurantPrice, parameterPrice);

        //then
        assertFalse(isValid);
    }

}
