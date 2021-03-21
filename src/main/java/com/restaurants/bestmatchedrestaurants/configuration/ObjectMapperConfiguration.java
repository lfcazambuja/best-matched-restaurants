package com.restaurants.bestmatchedrestaurants.configuration;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//@Configuration
public class ObjectMapperConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    void objectMapperSetup() {
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
