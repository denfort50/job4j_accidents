CREATE TABLE IF NOT EXISTS accidents (
    id serial PRIMARY KEY,
    name VARCHAR,
    text VARCHAR,
    address VARCHAR,
    accident_type INTEGER REFERENCES accident_types(id)
);