CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    email    TEXT,
    password TEXT
);

CREATE TABLE candidates
(
    id          SERIAL PRIMARY KEY,
    name        TEXT,
    description TEXT,
    created     TIMESTAMP,
    photo       bytea
);

CREATE TABLE cities
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

INSERT INTO cities(name) VALUES ('Москва');
INSERT INTO cities(name) VALUES ('Санкт-Петербург');
INSERT INTO cities(name) VALUES ('Екатеринбург');
INSERT INTO cities(name) VALUES ('Самара');

CREATE TABLE posts
(
    id          SERIAL PRIMARY KEY,
    name        TEXT,
    description TEXT,
    city_id     INT REFERENCES cities (id)
);