package com.restaurants.bestmatchedrestaurants.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.service.RestaurantsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RestaurantsControllerTest {

    @Mock private RestaurantsService restaurantsService;
    @InjectMocks private RestaurantsController controller;

    @Test
    public void shouldDelegateSearchToRestaurantsService() {
        //given
        String name = "name";
        Integer customerRating = Integer.valueOf(5);
        Integer distance = Integer.valueOf(1);
        BigDecimal price = BigDecimal.valueOf(10);
        String cuisine = "cuisine";
        Collection<RestaurantCuisine> restaurants = mockCollection();
        given(restaurantsService.findRestaurants(name, customerRating, distance, price, cuisine))
            .willReturn(restaurants);

        //when
        ResponseEntity<Collection<RestaurantCuisine>> response = controller.find(
                        name, customerRating, distance, price, cuisine);

        //then
        assertThat(response.getBody(), equalTo(restaurants));
        then(restaurantsService).should().findRestaurants(name, customerRating, distance, price, cuisine);
    }

    private Collection<RestaurantCuisine> mockCollection() {
        int collectionSize = new Random().nextInt(5) + 1;
        Collection<RestaurantCuisine> collection = new ArrayList<>();

        for (int i = 0; i < collectionSize; i++) {
            collection.add(mock(RestaurantCuisine.class));
        }
        return collection;
    }
}
