CREATE TABLE animals (
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    type varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    date_of_birth timestamp NOT NULL,
    health_points int NOT NULL,
    hunger_points int NOT NULL,
    thirst_points int NOT NULL
)