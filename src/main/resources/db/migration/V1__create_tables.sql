CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    venue_name VARCHAR(128) NOT NULL,
    venue_city VARCHAR(128),
    venue_address VARCHAR(128),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    sales_start TIMESTAMP NOT NULL,
    sales_end TIMESTAMP NOT NULL,
    capacity INTEGER NOT NULL,
    ticket_price_amount NUMERIC(19, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(32) NOT NULL
);

CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    event_id UUID NOT NULL,
    customer_id UUID,
    quantity INTEGER,
    total_amount NUMERIC(19, 2)
);

CREATE TABLE tickets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    event_id UUID,
    seat_number VARCHAR(255),
    price_amount NUMERIC(19, 2),
    order_id UUID,
    status VARCHAR(32)
);
