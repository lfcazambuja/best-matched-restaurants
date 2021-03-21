package com.restaurants.bestmatchedrestaurants.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 2976912422840649338L;

    private String name;
    @JsonProperty("customer_rating")
    private int customerRating;
    private int distance;
    private BigDecimal price;
    @JsonProperty("cuisine_id")
    private long cuisineId;
}
