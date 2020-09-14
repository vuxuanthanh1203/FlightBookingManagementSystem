CREATE DATABASE flightbooking;
USE flightbooking;

CREATE TABLE roles (
	role_id INT AUTO_INCREMENT,
    role_name VARCHAR(10),
    PRIMARY KEY (role_id)
);

CREATE TABLE users(
	user_id INT AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) DEFAULT 'null',
    tel VARCHAR(10) DEFAULT 'null',
    id_card INT DEFAULT null,
    address VARCHAR(255) DEFAULT 'null',
    role_id INT NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE locations(
	loc_id INT AUTO_INCREMENT,
    loc_name VARCHAR(255) NOT NULL,
    loc_code VARCHAR(5) NOT NULL,
    PRIMARY KEY(loc_id)
);

CREATE TABLE fleets(
	fleet_id INT AUTO_INCREMENT,
    total_e_seat INT NOT NULL,
    total_p_seat INT NOT NULL,
    total_b_seat INT NOT NULL,
    PRIMARY KEY(fleet_id)
);

CREATE TABLE fares(
	fare_id INT AUTO_INCREMENT,
    e_fare DOUBLE NOT NULL,
    p_fare DOUBLE NOT NULL,
    b_fare DOUBLE NOT NULL,
    PRIMARY KEY(fare_id)
);

CREATE TABLE flights(
	flight_id INT AUTO_INCREMENT,
    departure_loc INT NOT NULL,
    arrival_loc INT NOT NULL,
    fleet_id INT NOT NULL,
    fare_id INT NOT NULL,
    departure_time TIME,
    arrival_time TIME,
    flight_date DATE,
    PRIMARY KEY(flight_id),
    FOREIGN KEY (departure_loc) REFERENCES locations(loc_id),
    FOREIGN KEY (arrival_loc) REFERENCES locations(loc_id),
    FOREIGN KEY (fleet_id) REFERENCES fleets(fleet_id),
    FOREIGN KEY (fare_id) REFERENCES fares(fare_id)
);

CREATE TABLE flightStatus(
	status_id INT AUTO_INCREMENT,
    remaining_e_seat INT NOT NULL,
    remaining_p_seat INT NOT NULL,
    remaining_b_seat INT NOT NULL,
    flight_id INT NOT NULL,
    PRIMARY KEY(status_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

CREATE TABLE bookings(
	booking_id INT AUTO_INCREMENT,
    booking_number INT NOT NULL,
    booking_date DATE NOT NULL,
    total_cost DOUBLE NOT NULL,
    flight_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY(booking_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

/* SELECT ACCOUNT*/
DELIMITER //
CREATE PROCEDURE select_account (IN email VARCHAR(255), IN pass VARCHAR(255))
BEGIN
	SELECT email, pass
	FROM users
    WHERE email = email AND pass = pass;
END //
DELIMITER ;

INSERT INTO roles VALUES
(null, 'admin'),
(null, 'customer');

INSERT INTO users VALUES
(null, 'vxt@gmail.com', 'thanh1203', 'VU XUAN THANH', '0965301752', '152216442', 'QUYNH PHU, THAI BINH', 2),
(null, 'thanhvx@gmail.com', 'thanh1203', 'VU XUAN THANH', '0123456789', '152216442', 'HOANG MAI, HA NOI', 1);

-- DROP database flightbooking;

-- select * from users;
-- SELECT email, pass FROM users WHERE email = 'vxt@gmail.com' AND pass = '1234';