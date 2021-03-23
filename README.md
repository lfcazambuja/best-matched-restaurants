# best-matched-restaurants

## Coding Challenge
Best Matched Restaurants

### Solution

My solution is stored in a github repo: https://github.com/lfcazambuja/best-matched-restaurants.


### About My Solution

It is a Spring Boot application written in Java, exposing an HTTP endpoint that returns the results of the proposed search:

GET localhost:8080/restaurants

As the data provided (and the relationship between them) is very simple, I didn't use a database to store the data (I thought about using mongodb or an in-memory database like H2, but discarded the idea, as you are focusing in my coding skills).

For sorting purposes, I chose the **name of the restaurant** to be the last criteria, after the distance, customer rating, and price.

I added a cache to the search function, again, very simple, provided by Spring Boot, where the key is based on the search parameters.


### How it works

The application takes the provided data (**restaurants.csv** and **cuisines.csv**) and loads it into memory, and all the searching is based on this "static" data.

The endpoint provided returns the search results in JSON format, like:

    {
        "name": "Deliciousgenix",
        "customerRating": 4,
        "distance": 1,
        "price": 10,
        "cuisine": "Spanish"
    }

All the parameters (**name**, **customerRating**, **distance**, **price**, and **cuisine**) are query strings, and all of them are optional, but **if no one is provided, there will be an error**. Also, **if the parameters' values are invalid, there will be an error**.

### How to run and test

The application requires **JAVA 11** and **Maven** to run.

Go to the root folder and execute the following

`mvn spring-boot:run`

In order to test the application, please make the following request

`curl --location --request GET 'localhost:8080/restaurants?customerRating=3&price=15'`

Please note that it is an example, where I used **customer rating** and **price** as search criteria. You are free to play with the given parameters (again, **name**, **customerRating**, **distance**, **price**, and **cuisine**).

Alternatively, you can also find a live version deployed at AWS:

`curl --location --request GET 'http://bestmatchedrestaurants-env.eba-uwdpi2qz.sa-east-1.elasticbeanstalk.com/restaurants?customerRating=3&price=10'`
