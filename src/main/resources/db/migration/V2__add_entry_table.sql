CREATE TABLE entry
(
    id                BIGSERIAL PRIMARY KEY,
    debit_account_id  BIGINT  NOT NULL REFERENCES account (id),
    credit_account_id BIGINT  NOT NULL REFERENCES account (id),
    amount            DECIMAL NOT NULL,
    date              DATE    NOT NULL,
    description       TEXT    NOT NULL
);