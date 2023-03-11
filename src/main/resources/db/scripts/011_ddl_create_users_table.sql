CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    authority_id INT NOT NULL REFERENCES authorities(id)
);