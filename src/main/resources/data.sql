DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS cuisines;

CREATE TABLE cuisines (
  id INT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
) AS SELECT * FROM CSVREAD('classpath:cuisines.csv');

CREATE TABLE restaurants (
  name VARCHAR(100) NOT NULL,
  customer_rating INT NOT NULL,
  distance INT NOT NULL,
  price NUMERIC(8, 2) NOT NULL,
  cuisine_id INT NOT NULL
) AS SELECT * FROM CSVREAD('classpath:restaurants.csv');

ALTER TABLE restaurants ADD COLUMN id INT AUTO_INCREMENT NOT NULL FIRST;
ALTER TABLE restaurants ADD PRIMARY KEY (id);
