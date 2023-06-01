CREATE TABLE pet
(
    pet_id      SERIAL          NOT NULL,
    name        VARCHAR(32)     NOT NULL,
    breed       VARCHAR(32)     NOT NULL,
    owner_id    INT             NOT NULL,
    PRIMARY KEY (pet_id),
    CONSTRAINT fk_pet_owner
        FOREIGN KEY (owner_id)
            REFERENCES owner (owner_id)
);