SET NAMES utf8;

DROP DATABASE IF EXISTS touragency;
CREATE DATABASE touragency CHARACTER SET utf8 COLLATE utf8_bin;

USE touragency;

CREATE TABLE Description
(
    description_id INTEGER       NOT NULL auto_increment,
    type_id        INTEGER       NULL,
    country        VARCHAR(20)   NOT NULL,
    program_tour   VARCHAR(2000) NOT NULL,
    sport_activity VARCHAR(2000) NULL,
    beach_activity VARCHAR(2000) NULL,
    photo          LONGBLOB          NULL,
    PRIMARY KEY (description_id)
)
;
CREATE TABLE Tour_type
(
    type_id   INTEGER     NOT NULL AUTO_INCREMENT,
    type_name varchar(40) NOT NULL UNIQUE,
    PRIMARY KEY (type_id)
);



CREATE TABLE Discount
(
    discount_id        INTEGER      NOT NULL auto_increment,
    discount_percent   INTEGER      NOT NULL,
    discount_step      INTEGER      NOT NULL,
    discount_name      VARCHAR(200) NOT NULL,
    discount_date_from TIMESTAMP    NOT NULL,
    discount_date_to   TIMESTAMP    NOT NULL,
    discount_price     DECIMAL(19, 4),
    data_last_change   TIMESTAMP,
    PRIMARY KEY (discount_id)
)
;



CREATE TABLE Hotel
(
    hotel_id    INTEGER      NOT NULL auto_increment,
    hotel_name  VARCHAR(20)  NOT NULL,
    hotel_class INTEGER      NOT NULL,
    hotel_site  VARCHAR(200) NULL,
    PRIMARY KEY (hotel_id)
)
;



CREATE TABLE Roles
(
    role_id INTEGER     NOT NULL,
    role    VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (role_id)
)
;



CREATE TABLE Tour
(
    tour_id                  INTEGER        NOT NULL auto_increment,
    tour_name                VARCHAR(200)   NOT NULL UNIQUE,
    departure_city           VARCHAR(20)    NOT NULL,
    hotel_id                 INTEGER        NULL,
    price                    DECIMAL(19, 4) NOT NULL,
    amount_available_tickets INTEGER        NULL,
    amount_of_tickets        INTEGER        NOT NULL,
    description_id           INTEGER        NULL,
    discount_id              INTEGER        NULL,
    hot_tour                 BOOLEAN,
    tour_duration_id         INTEGER        NOT NULL,
    PRIMARY KEY (tour_id)
)
;



CREATE TABLE Tour_duration
(
    tour_duration_id INTEGER   NOT NULL auto_increment,
    tour_begin_date  TIMESTAMP NOT NULL,
    tour_end_date    TIMESTAMP NOT NULL,
    PRIMARY KEY (tour_duration_id)
)
;



CREATE TABLE Tour_reservation
(
    reservation_id INTEGER NOT NULL auto_increment,
    tour_id        INTEGER NULL,
    status_id      INTEGER NULL,
    PRIMARY KEY (reservation_id)
)
;

CREATE TABLE Reservation_list
(
    user_id        INTEGER NOT NULL,
    reservation_id INTEGER NULL
)
;



CREATE TABLE Tour_status
(
    status_id   INTEGER  NOT NULL,
    status_name CHAR(18) NOT NULL UNIQUE,
    PRIMARY KEY (status_id)
)
;



CREATE TABLE Users
(
    user_id    INTEGER     NOT NULL auto_increment,
    login      VARCHAR(20) NOT NULL,
    password   VARCHAR(20) NOT NULL,
    first_name CHAR(18)    NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    role_id    INTEGER     NULL,
    user_email VARCHAR(40) NOT NULL,
    user_phone VARCHAR(20),
    valid_user BOOLEAN     NOT NULL,
    PRIMARY KEY (user_id)


)
;



CREATE UNIQUE INDEX XAK1Users ON Users
    (
     login
        )
;



ALTER TABLE Tour
    ADD FOREIGN KEY R_2 (hotel_id) REFERENCES Hotel (hotel_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
;


ALTER TABLE Tour
    ADD FOREIGN KEY R_3 (description_id) REFERENCES Description (description_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
;


ALTER TABLE Tour
    ADD FOREIGN KEY R_5 (discount_id) REFERENCES Discount (discount_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
;


ALTER TABLE Tour
    ADD FOREIGN KEY R_8 (tour_duration_id) REFERENCES Tour_duration (tour_duration_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
;

ALTER TABLE Tour_reservation
    ADD FOREIGN KEY R_9 (tour_id) REFERENCES Tour (tour_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
;


ALTER TABLE Tour_reservation
    ADD FOREIGN KEY R_11 (status_id) REFERENCES Tour_status (status_id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
;



ALTER TABLE Reservation_list
    ADD FOREIGN KEY R_14 (reservation_id) REFERENCES Tour_reservation (reservation_id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
;

ALTER TABLE Reservation_list
    ADD FOREIGN KEY R_15 (user_id) REFERENCES Users (user_id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
;



ALTER TABLE Users
    ADD FOREIGN KEY R_7 (role_id) REFERENCES Roles (role_id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
;

ALTER TABLE Description
    ADD FOREIGN KEY R_13 (type_id) REFERENCES Tour_type (type_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
;
ALTER TABLE Discount
    ADD CONSTRAINT ck_date CHECK (
        (discount_step < discount_percent)
        );



INSERT INTO Roles
VALUES (0, 'admin'),
       (1, 'manager'),
       (2, 'customer');

INSERT INTO Tour_status
VALUES (0, 'register'),
       (1, 'paid'),
       (2, 'canceled');

INSERT INTO Users
VALUES (default, 'admin', 'admin', 'Karina', 'Ohol', 0, 'orotour@mail.com','7428421137', true),
       (default, 'salesmanager', 'salemanager', 'Olga', 'Smirnova', 1, 'manager@mail.com', null, true),
       (default, 'manager', 'manager', 'Svetlana', 'Chet', 1, 'asdf@gmail.com', null, true),
       (default, 'ohol', 'p111', 'Vasylii', 'Tomps', 2, 'karkar200070@gmail.com', '+380933154216', true);


INSERT INTO Discount
VALUES (default, 15, 1, 'Holi Special Offer 2020', '2020-03-18 12:00', '2020-07-18', null, null),
       (default, 20, 2, 'Holi Special Offer 2020', '2020-03-18', '2020-07-18', null, null),
       (default, 13, 1, 'Sea vacation Offer 2020', '2020-03-18', '2020-05-18', null, null),
       (default, 15, 1, 'Mountain adventure Offer 2020', '2020-03-18', '2020-07-18', null, null);


INSERT INTO Tour_type
VALUES (default, 'City Tours'),
       (default, 'Cultural & Thematic Tours'),
       (default, 'Holiday & Seasonal Tours'),
       (default, 'Indulgence & Luxury Tours'),
       (default, 'Outdoor Activites'),
       (default, 'Relaxation Tours'),
       (default, 'Wild & Adventure Tours');

INSERT INTO Tour_duration
VALUES (default, '2020-06-03', '2020-06-10'),
       (default, '2020-06-12', '2020-06-15');

INSERT INTO Hotel
VALUES (default, 'Ocean Beach', 3, 'https://www.oceanbeachinn.com');


INSERT INTO Description
VALUES (default, 3, 'Bali', 'Seminyak – Arrival in Bali and Day at Leisure
Seminyak – Full Day Excursion to Mengwi Beach and Exploration of North Bali.Kuta – Dolphin Tour, Kintamani
Tour and Ubud Tour.Kuta – Half Day Tour of South Bali and Transfer to Private Pool Villa.
Kuta – Day of Relaxation in Private Pool Villa', 'Water Sports (1 parasailing, 1 Jet Ski, 1 Banana Boat Ride', 'Romantic Dinner on the Beach with Special Setup.
2 hrs Spa',LOAD_FILE('E://01bali.jpg'));

INSERT INTO Tour
VALUES (default, 'Bali Romantic Honeymoon Tour', 'New Delhi',
        1, 500, 25, 25, 1, null, false, 1);

INSERT INTO Tour_reservation
VALUES (default, 1, 0);
INSERT INTO Reservation_list
VALUES (4, 1);












