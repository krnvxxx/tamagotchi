ALTER TABLE animals ADD COLUMN user_id BIGINT;

ALTER TABLE animals ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)