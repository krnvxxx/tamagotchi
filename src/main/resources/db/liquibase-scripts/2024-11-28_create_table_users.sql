CREATE TABLE users(
    id            BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username      varchar(255) NOT NULL,
    email         varchar(255) NOT NULL
)