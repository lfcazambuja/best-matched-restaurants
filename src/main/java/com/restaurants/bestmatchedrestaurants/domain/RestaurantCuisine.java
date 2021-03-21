package com.restaurants.bestmatchedrestaurants.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantCuisine implements Serializable {

    private static final long serialVersionUID = -2025268846468019800L;

    private String name;
    private int customerRating;
    private int distance;
    private BigDecimal price;
    private String cuisine;
}
