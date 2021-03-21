package com.restaurants.bestmatchedrestaurants.web;

import java.math.BigDecimal;
import java.util.Collection;

import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.service.RestaurantsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestaurantsController implements RestaurantsApi {

    @Autowired
    private RestaurantsService restaurantsService;

    @Override
    @GetMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<RestaurantCuisine>> find(
                    @RequestParam(value = "name", required = false) final String name,
                    @RequestParam(value = "customerRating", required = false) final Integer customerRating,
                    @RequestParam(value = "distance", required = false) final Integer distance,
                    @RequestParam(value = "price", required = false) final BigDecimal price,
                    @RequestParam(value = "cuisine", required = false) final String cuisine) {
        log.info("Finding restaurants... Search criteria: name = {}, customer rating = {}, distance = {},"
                        + " price = {}, cuisine = {}.", name, customerRating, distance, price, cuisine);
        return ResponseEntity.ok(restaurantsService.findRestaurants(name, customerRating, distance, price, cuisine));
    }
}
