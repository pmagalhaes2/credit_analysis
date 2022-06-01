CREATE TABLE contact(
    id UUID NOT NULL,
    client_id UUID NOT NULL,
    type VARCHAR(20),
    phone_number VARCHAR(12),
    email_address VARCHAR(255)
)