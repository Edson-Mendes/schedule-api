CREATE TABLE IF NOT EXISTS t_user (
    id bigserial NOT NULL,
    name varchar(150) NOT NULL,
    email varchar(150) NOT NULL,
    password varchar(255) NOT NULL,
    roles varchar(255) NOT NULL,
    CONSTRAINT t_user_id_pk PRIMARY KEY (id),
    CONSTRAINT t_user_email_unique UNIQUE (email)
);