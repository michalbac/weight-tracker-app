CREATE TABLE IF NOT EXISTS user_account (
                                            id BIGINT NOT NULL PRIMARY KEY,
                                            username varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(255),
    enabled boolean NOT NULL
    );

CREATE TABLE IF NOT EXISTS weight_measure (

                                              id BIGINT NOT NULL PRIMARY KEY,
                                              weight NUMERIC (8, 2) NOT NULL,
    date_of_measure DATE,
    user_id BIGINT NOT NULL REFERENCES user_account (id)

    );

CREATE TABLE IF NOT EXISTS body_measure (

                                            id BIGINT NOT NULL PRIMARY KEY,
                                            right_bicep NUMERIC (8, 2) NOT NULL,
    left_bicep NUMERIC (8, 2) NOT NULL,
    waist NUMERIC (8, 2) NOT NULL,
    chest NUMERIC (8, 2) NOT NULL,
    left_thigh NUMERIC (8, 2) NOT NULL,
    right_thigh NUMERIC (8, 2) NOT NULL,
    date_of_measure DATE,
    user_id BIGINT NOT NULL REFERENCES user_account (id)
    );

CREATE TABLE IF NOT EXISTS user_account (
                                            id BIGINT NOT NULL PRIMARY KEY,
                                            username varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(255),
    enabled boolean NOT NULL
    );
