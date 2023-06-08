-- Table User
CREATE TABLE IF NOT EXISTS t_user (
    id bigserial NOT NULL,
    name varchar(150) NOT NULL,
    email varchar(150) NOT NULL,
    password varchar(255) NOT NULL,
    roles varchar(255) NOT NULL,
    CONSTRAINT t_user_id_pk PRIMARY KEY (id),
    CONSTRAINT t_user_email_unique UNIQUE (email)
);

-- Table Event
CREATE TABLE IF NOT EXISTS t_event (
    id bigserial NOT NULL,
    title varchar(100) NOT NULL,
    description varchar(255),
    date timestamp NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT t_commitment_id_pk PRIMARY KEY (id),
    CONSTRAINT t_commitment_user_id_fk FOREIGN KEY (user_id) REFERENCES t_user(id)
);