CREATE TABLE toy (
    toy_id      SERIAL          NOT NULL,
    what        VARCHAR(32)     NOT NULL,
    color       VARCHAR(32)     NOT NULL,
    PRIMARY KEY (toy_id)
);