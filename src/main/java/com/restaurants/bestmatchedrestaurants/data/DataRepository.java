package com.restaurants.bestmatchedrestaurants.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.restaurants.bestmatchedrestaurants.exception.ServerErrorException;
import com.restaurants.bestmatchedrestaurants.model.Cuisine;
import com.restaurants.bestmatchedrestaurants.model.Restaurant;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataRepository implements Repository {

    private CsvMapper csvMapper;
    private Collection<Cuisine> cuisines;
    private Collection<Restaurant> restaurants;

    @Override
    public Collection<Restaurant> findAllRestaurants() {
        if (restaurants == null) {
            try {
                log.info("Loading restaurants data...");
                final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
                final InputStream restaurantsFile = new ClassPathResource("restaurants.csv").getInputStream();
                final MappingIterator<Restaurant> restaurantValues = getCsvMapper()
                                .readerFor(Restaurant.class)
                                .with(bootstrapSchema)
                                .readValues(restaurantsFile);

                restaurants = List.copyOf(restaurantValues.readAll());
                log.info("Loaded {} restaurants.", Integer.valueOf(restaurants.size()));
            } catch (IOException e) {
                log.error("Error loading restaurants data.", e);
                throw new ServerErrorException("Error loading restaurants data.", e);
            }
        }
        return restaurants;
    }

    @Override
    public Collection<Cuisine> findAllCuisines() {
        if (cuisines == null) {
            try {
                log.info("Loading restaurants data...");
                final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
                final InputStream cuisinesFile = new ClassPathResource("cuisines.csv").getInputStream();
                final MappingIterator<Cuisine> cuisineValues = getCsvMapper()
                                .readerFor(Cuisine.class)
                                .with(bootstrapSchema)
                                .readValues(cuisinesFile);

                cuisines = List.copyOf(cuisineValues.readAll());
                log.info("Loaded {} cuisines.", Integer.valueOf(cuisines.size()));
            } catch (IOException e) {
                log.error("Error loading cuisines data.", e);
                throw new ServerErrorException("Error loading cuisines data.", e);
            }
        }
        return cuisines;
    }

    private CsvMapper getCsvMapper() {
        if (csvMapper == null) {
            csvMapper = new CsvMapper();
        }
        return csvMapper;
    }
}
