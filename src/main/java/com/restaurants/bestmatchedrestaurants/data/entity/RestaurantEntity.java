package com.restaurants.bestmatchedrestaurants.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Table(name = "restaurants")
@ToString(of = "name")
public class RestaurantEntity implements Serializable {

    private static final long serialVersionUID = -7444444997947024751L;

    private static final String SEQUENCE = "restaurant_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "customer_rating", nullable = false)
    private int customerRating;

    @Column(name = "distance", nullable = false)
    private int distance;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuisine_id", referencedColumnName = "id", nullable = false)
    private CuisineEntity cuisine;
}
