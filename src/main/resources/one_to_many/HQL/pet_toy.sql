CREATE TABLE pet_toy (
    pet_toy_id      SERIAL      NOT NULL,
    pet_id          INT         NOT NULL,
    toy_id          INT         NOT NULL,
    CONSTRAINT fk_pet_toy_pet
        FOREIGN KEY (pet_id)
            REFERENCES pet (pet_id),
    CONSTRAINT fk_pet_toy_toy
        FOREIGN KEY (toy_id)
            REFERENCES toy (toy_id)
);
