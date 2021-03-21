package com.restaurants.bestmatchedrestaurants.service;

import static io.codearte.catchexception.shade.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import com.restaurants.bestmatchedrestaurants.business.RestaurantsFilter;
import com.restaurants.bestmatchedrestaurants.business.validator.RequestParametersValidator;
import com.restaurants.bestmatchedrestaurants.dataloader.DataLoader;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestaurantsServiceTest {

    @Mock private DataLoader dataLoader;
    @Mock private RequestParametersValidator validator;
    @Mock private RestaurantsFilter restaurantFilter;
    @InjectMocks private RestaurantsService restaurantsService;

    @Mock private Predicate<RestaurantCuisine> predicate;

    @Test
    public void shouldLimitCollectionByFiveElements() {
        //given the setup and
        final String name = "KFC";
        doNothing().when(validator).validate(name, null, null, null, null);
        given(restaurantFilter.buildFilter(name, null, null, null, null)).willReturn(predicate);
        given(predicate.test(any(RestaurantCuisine.class))).willReturn(Boolean.TRUE);
        Collection<RestaurantCuisine> mockedRestaurants = mockRestaurants(10);
        given(dataLoader.getRestaurantCuisine()).willReturn(mockedRestaurants);

        //when
        Collection<RestaurantCuisine> restaurants = restaurantsService.findRestaurants(name, null, null, null, null);

        //then
        assertThat(restaurants, hasSize(5));
        then(validator).should().validate(name, null, null, null, null);
        then(restaurantFilter).should().buildFilter(name, null, null, null, null);
        then(dataLoader).should().getRestaurantCuisine();
    }

    private Collection<RestaurantCuisine> mockRestaurants(final int size) {
        Collection<RestaurantCuisine> restaurants = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            //TODO
            restaurants.add(mock(RestaurantCuisine.class));
        }
        return restaurants;
    }
}
