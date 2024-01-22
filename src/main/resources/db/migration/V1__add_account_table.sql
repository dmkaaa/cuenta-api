CREATE TABLE account
(
    id   BIGSERIAL PRIMARY KEY,
    code VARCHAR(10) UNIQUE NOT NULL,
    type VARCHAR(20)        NOT NULL,
    name TEXT               NOT NULL
);