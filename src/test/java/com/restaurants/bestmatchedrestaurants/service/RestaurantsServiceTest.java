package com.restaurants.bestmatchedrestaurants.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Predicate;

import com.restaurants.bestmatchedrestaurants.business.RestaurantsFilter;
import com.restaurants.bestmatchedrestaurants.business.validator.RequestParametersValidator;
import com.restaurants.bestmatchedrestaurants.data.DataRepository;
import com.restaurants.bestmatchedrestaurants.data.RepositoryDataMapper;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.model.Cuisine;
import com.restaurants.bestmatchedrestaurants.model.Restaurant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantsServiceTest {

    @Mock private DataRepository repository;
    @Mock private RequestParametersValidator validator;
    @Mock private RestaurantsFilter restaurantFilter;
    @Mock private RepositoryDataMapper dataMapper;
    @InjectMocks private RestaurantsService restaurantsService;

    @Mock private Predicate<RestaurantCuisine> predicate;
    @Mock private Collection<Cuisine> cuisines;
    @Mock private Collection<Restaurant> restaurants;

    @Test
    public void shouldLimitCollectionByFiveElements() {
        //given the setup and
        final String name = "KFC";
        doNothing().when(validator).validate(name, null, null, null, null);
        given(restaurantFilter.buildFilter(name, null, null, null, null)).willReturn(predicate);
        given(predicate.test(any(RestaurantCuisine.class))).willReturn(Boolean.TRUE);
        given(repository.findAllCuisines()).willReturn(cuisines);
        given(repository.findAllRestaurants()).willReturn(restaurants);
        Collection<RestaurantCuisine> mockedRestaurants = restaurants(10);
        given(dataMapper.map(cuisines, restaurants)).willReturn(mockedRestaurants);

        //when
        Collection<RestaurantCuisine> response = restaurantsService.findRestaurants(name, null, null, null, null);

        //then
        assertThat(response, hasSize(5));
        then(validator).should().validate(name, null, null, null, null);
        then(restaurantFilter).should().buildFilter(name, null, null, null, null);
        then(repository).should().findAllCuisines();
        then(repository).should().findAllRestaurants();
        then(dataMapper).should().map(cuisines, restaurants);
    }

    @Test
    public void shouldReturnAllElementsWhenResultHasFiveOrLessRestaurants() {
        //given the setup and
        final String name = "1";
        doNothing().when(validator).validate(name, null, null, null, null);
        given(restaurantFilter.buildFilter(name, null, null, null, null)).willReturn(predicate);
        given(predicate.test(any(RestaurantCuisine.class))).willReturn(Boolean.TRUE);
        given(repository.findAllCuisines()).willReturn(cuisines);
        given(repository.findAllRestaurants()).willReturn(restaurants);
        final int numberOfRestaurants = new Random().nextInt(5) + 1;
        Collection<RestaurantCuisine> mockedRestaurants = restaurants(numberOfRestaurants);
        given(dataMapper.map(cuisines, restaurants)).willReturn(mockedRestaurants);

        //when
        Collection<RestaurantCuisine> response = restaurantsService.findRestaurants(name, null, null, null, null);

        //then
        assertThat(response, hasSize(numberOfRestaurants));
        then(validator).should().validate(name, null, null, null, null);
        then(restaurantFilter).should().buildFilter(name, null, null, null, null);
        then(repository).should().findAllCuisines();
        then(repository).should().findAllRestaurants();
        then(dataMapper).should().map(cuisines, restaurants);
    }

    private Collection<RestaurantCuisine> restaurants(final int size) {
        Collection<RestaurantCuisine> localRestaurants = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            localRestaurants.add(
                new RestaurantCuisine(String.valueOf(i), i, i, BigDecimal.valueOf(i*10), String.valueOf(i*2)));
        }
        return localRestaurants;
    }
}
