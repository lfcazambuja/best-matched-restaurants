package com.restaurants.bestmatchedrestaurants.business.validator;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import java.math.BigDecimal;
import java.util.Random;

import com.restaurants.bestmatchedrestaurants.exception.ClientErrorException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RequestParametersValidatorTest {

    @InjectMocks
    private RequestParametersValidator validator;

    @Test
    public void shouldThrowExceptionWhenNameAllParametersAreNull() {
        //given

        //when
        catchException(validator).validate(null, null, null, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        //given
        String name = "";

        //when
        catchException(validator).validate(name, null, null, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsBlank() {
        //given
        String name = "   ";

        //when
        catchException(validator).validate(name, null, null, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenCustomerRatingIsNegative() {
        //given
        Integer customerRating = Integer.valueOf(-new Random().nextInt(Integer.MAX_VALUE));

        //when
        catchException(validator).validate(null, customerRating, null, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenCustomerRatingIsGreaterThanFive() {
        //given
        Integer customerRating = Integer.valueOf(new Random().nextInt(Integer.MAX_VALUE - 5) + 6);

        //when
        catchException(validator).validate(null, customerRating, null, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenDistanceIsNegative() {
        //given
        Integer distance = Integer.valueOf(-new Random().nextInt(Integer.MAX_VALUE));

        //when
        catchException(validator).validate(null, null, distance, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenDistanceIsGreaterThanTen() {
        //given
        Integer distance = Integer.valueOf(new Random().nextInt(Integer.MAX_VALUE - 10) + 11);

        //when
        catchException(validator).validate(null, null, distance, null, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsNegative() {
        //given
        BigDecimal price = BigDecimal.valueOf(-new Random().nextInt(Integer.MAX_VALUE));

        //when
        catchException(validator).validate(null, null, null, price, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsGreaterThanFifty() {
        //given
        BigDecimal price = BigDecimal.valueOf(-new Random().nextInt(Integer.MAX_VALUE - 50) + 51);

        //when
        catchException(validator).validate(null, null, null, price, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsLessThanTen() {
        //given
        BigDecimal price = BigDecimal.valueOf(new Random().nextInt(10));

        //when
        catchException(validator).validate(null, null, null, price, null);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenCuisineIsEmpty() {
        //given
        String cuisine = "";

        //when
        catchException(validator).validate(null, null, null, null, cuisine);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldThrowExceptionWhenCuisineIsBlank() {
        //given
        String cuisine = "   ";

        //when
        catchException(validator).validate(null, null, null, null, cuisine);

        //then
        assertThat(caughtException(), instanceOf(ClientErrorException.class));
    }

    @Test
    public void shouldValidateParameters() {
        //given
        String name = "name";
        Integer customerRating = Integer.valueOf(new Random().nextInt(5) + 1);
        Integer distance = Integer.valueOf(new Random().nextInt(10) + 1);
        BigDecimal price = BigDecimal.valueOf(new Random().nextInt(40) + 10 + 1);
        String cuisine = "cuisine";

        //when
        validator.validate(name, customerRating, distance, price, cuisine);

        //then should validate
    }
}
