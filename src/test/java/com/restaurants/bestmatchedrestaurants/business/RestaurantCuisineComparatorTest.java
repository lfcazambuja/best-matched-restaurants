package com.restaurants.bestmatchedrestaurants.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestaurantCuisineComparatorTest {

    private RestaurantCuisineComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new RestaurantCuisineComparator();
    }

    @Test
    public void compareByDistance() {
        //given
        RestaurantCuisine restaurant1 = new RestaurantCuisine("r1", 4, 1, BigDecimal.valueOf(10), "cuisine-1");
        RestaurantCuisine restaurant2 = new RestaurantCuisine("r2", 5, 2, BigDecimal.valueOf(10), "cuisine-2");

        //when
        int compare = comparator.compare(restaurant1, restaurant2);

        //then
        assertEquals(-1, compare);
    }

    @Test
    public void compareByCustomerRating() {
        //given
        RestaurantCuisine restaurant1 = new RestaurantCuisine("r1", 4, 1, BigDecimal.valueOf(10), "cuisine-1");
        RestaurantCuisine restaurant2 = new RestaurantCuisine("r2", 5, 1, BigDecimal.valueOf(10), "cuisine-2");

        //when
        int compare = comparator.compare(restaurant1, restaurant2);

        //then
        assertEquals(1, compare);
    }

    @Test
    public void compareByCustomerPrice() {
        //given
        RestaurantCuisine restaurant1 = new RestaurantCuisine("r1", 5, 1, BigDecimal.valueOf(10), "cuisine-1");
        RestaurantCuisine restaurant2 = new RestaurantCuisine("r2", 5, 1, BigDecimal.valueOf(20), "cuisine-2");

        //when
        int compare = comparator.compare(restaurant1, restaurant2);

        //then
        assertEquals(-1, compare);
    }

    @Test
    public void compareByName() {
        //given
        RestaurantCuisine restaurant1 = new RestaurantCuisine("r1", 5, 1, BigDecimal.valueOf(20), "cuisine-1");
        RestaurantCuisine restaurant2 = new RestaurantCuisine("r2", 5, 1, BigDecimal.valueOf(20), "cuisine-2");

        //when
        int compare = comparator.compare(restaurant1, restaurant2);

        //then
        assertEquals(-1, compare);
    }
}
