CREATE TABLE client_pf(
    id UUID NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birth_date DATE,
    PRIMARY KEY(id)
);