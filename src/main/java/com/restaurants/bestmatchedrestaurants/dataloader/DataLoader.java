package com.restaurants.bestmatchedrestaurants.dataloader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.restaurants.bestmatchedrestaurants.domain.RestaurantCuisine;
import com.restaurants.bestmatchedrestaurants.exception.ServerErrorException;
import com.restaurants.bestmatchedrestaurants.model.Cuisine;
import com.restaurants.bestmatchedrestaurants.model.Restaurant;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader {

    private CsvMapper csvMapper;
    private Collection<Cuisine> cuisines;
    private Collection<Restaurant> restaurants;

    @PostConstruct
    public void loadData() {
        final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

        log.info("Loading restaurants data...");
        try {
            final File cuisinesFile = new ClassPathResource("cuisines.csv").getFile();
            final MappingIterator<Cuisine> cuisineValues = getCsvMapper()
                            .readerFor(Cuisine.class)
                            .with(bootstrapSchema)
                            .readValues(cuisinesFile);
            final File restaurantsFile = new ClassPathResource("restaurants.csv").getFile();
            final MappingIterator<Restaurant> restaurantValues = getCsvMapper()
                            .readerFor(Restaurant.class)
                            .with(bootstrapSchema)
                            .readValues(restaurantsFile);

            cuisines = List.copyOf(cuisineValues.readAll());
            log.info("Loaded {} cuisines.", Integer.valueOf(cuisines.size()));
            restaurants = List.copyOf(restaurantValues.readAll());
            log.info("Loaded {} restaurants.", Integer.valueOf(restaurants.size()));
        } catch (IOException e) {
            throw new ServerErrorException("Error loading restaurants data.");
        }
    }

    public Collection<RestaurantCuisine> getRestaurantCuisine() {
        Map<Long, String> cuisinesMap = cuisines.stream()
            .collect(Collectors.toMap(Cuisine::getId, Cuisine::getName));

        log.info("Converting model into internal domain...");
        return restaurants.stream()
            .map(r -> new RestaurantCuisine(
                r.getName(), r.getCustomerRating(), r.getDistance(), r.getPrice(), cuisinesMap.get(r.getCuisineId())))
            .collect(Collectors.toList());
    }

    private CsvMapper getCsvMapper() {
        if (csvMapper == null) {
            csvMapper = new CsvMapper();
        }
        return csvMapper;
    }
}
