CREATE TABLE address(
    id UUID NOT NULL,
    client_id UUID NOT NULL,
    street VARCHAR(30),
    city VARCHAR(30),
    state VARCHAR(2),
    cep VARCHAR(8),
    PRIMARY KEY(id),
    FOREIGN KEY(client_id) REFERENCES client_pf(id)
)