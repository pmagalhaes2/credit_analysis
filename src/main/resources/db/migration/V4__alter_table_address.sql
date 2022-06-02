ALTER TABLE address
    DROP client_id;

ALTER TABLE address
    ADD client_id UUID NOT NULL;

ALTER TABLE address
    ADD FOREIGN KEY(client_id) REFERENCES client_pf(id)
    ON DELETE CASCADE;
