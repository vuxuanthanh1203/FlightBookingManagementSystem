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
    full_name VARCHAR(255) NOT NULL,
    tel VARCHAR(10) NOT NULL,
    id_card VARCHAR(9) NOT NULL,
    address VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE locations(
	loc_code VARCHAR(5) NOT NULL,
    loc_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(loc_code)
);

CREATE TABLE routes(
	route_id INT AUTO_INCREMENT,
    departure_loc VARCHAR(5) NOT NULL,
    arrival_loc VARCHAR(5) NOT NULL,
    PRIMARY KEY(route_id),
    FOREIGN KEY (departure_loc) REFERENCES locations(loc_code),
    FOREIGN KEY (arrival_loc) REFERENCES locations(loc_code)
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
    flight_num VARCHAR(255) NOT NULL,
    route_id INT NOT NULL,
    fleet_id INT NOT NULL,
    fare_id INT NOT NULL,
    flight_date DATE NOT NULL,
    flight_time TIME NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    PRIMARY KEY(flight_id),
    FOREIGN KEY (route_id) REFERENCES routes(route_id),
    FOREIGN KEY (fleet_id) REFERENCES fleets(fleet_id),
    FOREIGN KEY (fare_id) REFERENCES fares(fare_id)
);

CREATE TABLE flightStatus(
	status_id INT AUTO_INCREMENT,
    remaining_e_seat INT default(0),
    remaining_p_seat INT default(0),
    remaining_b_seat INT default(0),
    flight_id INT NOT NULL,
    PRIMARY KEY(status_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

CREATE TABLE bookings(
	booking_id INT AUTO_INCREMENT,
    quantity INT NOT NULL,
    booking_date DATE NOT NULL,
    total_cost DOUBLE NOT NULL,
    flight_id INT NOT NULL,
    user_id INT NOT NULL,
    b_status VARCHAR(255) DEFAULT('Complete'),
    PRIMARY KEY(booking_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO roles VALUES
(null, 'admin'),
(null, 'customer'),
(null, 'guest');

INSERT INTO users VALUES
(null, 'vxt@gmail.com', 'f942cf0d8c018a45e529eb0120c6605d', 'VU XUAN THANH', '0965301752', '152216442', 'QUYNH PHU, THAI BINH', 2),
(null, 'thanhvx@gmail.com', '2977ca89f053ca48c4ee8f3ba7c97e98', 'VU XUAN THANH', '0123456789', '152216442', 'HOANG MAI, HA NOI', 1);

INSERT INTO fleets VALUES
(null, 42, 60, 72);

INSERT INTO locations VALUES
('HN', 'Ha Noi'),
('HCM', 'TP.Ho Chi Minh'),
('DN', 'Da Nang');

INSERT INTO routes VALUES
(null, 'HN', 'HCM'),
(null, 'HN', 'DN'),
(null, 'HCM', 'HN'),
(null, 'HCM', 'DN'),
(null, 'DN', 'HN'),
(null, 'DN', 'HCM');

INSERT INTO fares VALUES
(null, 1.5, 3.5, 5.1);

-- route, fleet, fare
INSERT INTO flights VALUES
(null, 'VN 4899', 1, 1, 1, '2020/09/19', '02:15:00', '07:45:00', '10:00:00'),
(null, 'VN 227', 1, 1, 1, '2020/09/19', '02:15:00', '10:00:00', '12:15:00'),
(null, 'VN 239', 1, 1, 1, '2020/09/19', '02:15:00', '14:00:00', '16:15:00'),
(null, 'VN 257', 1, 1, 1, '2020/09/19', '02:15:00', '14:00:00', '16:15:00'),
(null, 'VN 271', 1, 1, 1, '2020/09/19', '02:15:00', '19:30:00', '21:45:00'),
(null, 'VN 285', 1, 1, 1, '2020/09/19', '02:15:00', '21:00:00', '23:15:00'),
(null, 'VN 220', 3, 1, 1, '2020/09/19', '02:10:00', '08:00:00', '10:10:00'),
(null, 'VN 226', 3, 1, 1, '2020/09/19', '02:10:00', '09:00:00', '11:10:00'),
(null, 'VN 236', 3, 1, 1, '2020/09/19', '02:10:00', '12:00:00', '14:10:00'),
(null, 'VN 4920', 3, 1, 1, '2020/09/19', '02:10:00', '15:15:00', '17:25:00'),
(null, 'VN 276', 3, 1, 1, '2020/09/19', '02:10:00', '20:00:00', '22:10:00'),
(null, 'VN 284', 3, 1, 1, '2020/09/19', '02:10:00', '21:00:00', '23:10:00'),
(null, 'VN 155', 2, 1, 1, '2020/09/19', '01:20:00', '09:00:00', '10:20:00'),
(null, 'VN 177', 2, 1, 1, '2020/09/19', '01:20:00', '15:30:00', '16:50:00'),
(null, 'VN 197', 2, 1, 1, '2020/09/19', '01:20:00', '20:00:00', '21:20:00'),
(null, 'VN 158', 5, 1, 1, '2020/09/19', '01:20:00', '08:00:00', '09:20:00'),
(null, 'VN 168', 5, 1, 1, '2020/09/19', '01:20:00', '11:00:00', '12:20:00'),
(null, 'VN 4836', 5, 1, 1, '2020/09/19', '01:20:00', '18:15:00', '19:35:00'),
(null, 'VN 4858', 4, 1, 1, '2020/09/19', '01:20:00', '08:30:00', '09:50:00'),
(null, 'VN 124', 4, 1, 1, '2020/09/19', '01:20:00', '12:00:00', '13:20:00'),
(null, 'VN 136', 4, 1, 1, '2020/09/19', '01:20:00', '17:00:00', '18:20:00'),
(null, 'VN 117', 6, 1, 1, '2020/09/19', '01:30:00', '10:00:00', '11:30:00'),
(null, 'VN 133', 6, 1, 1, '2020/09/19', '01:30:00', '15:30:00', '17:00:00'),
(null, 'VN 4881', 6, 1, 1, '2020/09/19', '01:30:00', '21:30:00', '23:00:00');

INSERT INTO flightStatus VALUES
(null, 0, 0, 0, 1),
(null, 42, 60, 72, 2),
(null, 42, 60, 72, 3),
(null, 42, 60, 72, 4),
(null, 42, 60, 72, 5),
(null, 42, 60, 72, 6),
(null, 42, 60, 72, 7),
(null, 42, 60, 72, 8),
(null, 42, 60, 72, 9),
(null, 42, 60, 72, 10),
(null, 42, 60, 72, 11),
(null, 42, 60, 72, 12),
(null, 42, 60, 72, 13),
(null, 42, 60, 72, 14),
(null, 42, 60, 72, 15),
(null, 42, 60, 72, 16),
(null, 42, 60, 72, 17),
(null, 42, 60, 72, 18),
(null, 42, 60, 72, 19),
(null, 42, 60, 72, 20),
(null, 42, 60, 72, 21),
(null, 42, 60, 72, 22),
(null, 42, 60, 72, 23),
(null, 42, 60, 72, 24);


/* SELECT ACCOUNT*/
DELIMITER //
CREATE PROCEDURE select_account (IN email VARCHAR(255), IN pass VARCHAR(255))
BEGIN
	SELECT email, pass
	FROM users
    WHERE email = email AND pass = pass;
END //
DELIMITER ;

/* SEARCH FLIGHT*/
DELIMITER //
CREATE PROCEDURE search_flight (IN route INT, IN flight_date VARCHAR(255))
BEGIN
	SELECT f.flight_id, f.flight_num, r.departure_loc, r.arrival_loc, f.flight_date, f.flight_time, f.departure_time, f.arrival_time, flt.total_e_seat, flt.total_p_seat, flt.total_b_seat, fr.e_fare, fr.p_fare, fr.b_fare, fs.remaining_e_seat, fs.remaining_p_seat, fs.remaining_b_seat
	FROM flights AS f INNER JOIN routes AS r ON f.route_id = r.route_id
    INNER JOIN fleets AS flt ON f.fleet_id = flt.fleet_id
    INNER JOIN fares AS fr ON f.fare_id = fr.fare_id
    INNER JOIN flightStatus AS fs ON f.flight_id = fs.flight_id
    WHERE f.route_id = route AND f.flight_date = flight_date;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE view_booking (IN cus_key INT)
BEGIN
SELECT b.booking_id, f.flight_num, f.flight_date, f.flight_time, r.departure_loc, r.arrival_loc, f.departure_time, f.arrival_time, u.full_name, b.booking_date, b.quantity, b.total_cost, u.address, b.b_status
FROM flights AS f INNER JOIN bookings AS b ON f.flight_id = b.flight_id
INNER JOIN routes AS r ON f.route_id = r.route_id
INNER JOIN users AS u ON b.user_id = u.user_id
WHERE u.user_id = cus_key;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE view_all_booking ()
BEGIN
SELECT b.booking_id, f.flight_num, f.flight_date, f.flight_time, r.departure_loc, r.arrival_loc, f.departure_time, f.arrival_time, u.full_name, b.booking_date, b.quantity, b.total_cost, u.address, b.b_status
FROM flights AS f INNER JOIN bookings AS b ON f.flight_id = b.flight_id
INNER JOIN routes AS r ON f.route_id = r.route_id
INNER JOIN users AS u ON b.user_id = u.user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE select_booking (IN cus_key INT, IN booking_id INT)
BEGIN
SELECT b.booking_id, f.flight_num, f.flight_date, f.flight_time, r.departure_loc, r.arrival_loc, f.departure_time, f.arrival_time, u.full_name, b.booking_date, b.quantity, b.total_cost, u.address, b.b_status
FROM flights AS f INNER JOIN bookings AS b ON f.flight_id = b.flight_id
INNER JOIN routes AS r ON f.route_id = r.route_id
INNER JOIN users AS u ON b.user_id = u.user_id
WHERE u.user_id = cus_key AND b.booking_id = booking_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE select_a_booking (IN booking_id INT)
BEGIN
SELECT b.booking_id, f.flight_num, f.flight_date, f.flight_time, r.departure_loc, r.arrival_loc, f.departure_time, f.arrival_time, u.full_name, b.booking_date, b.quantity, b.total_cost, u.address, b.b_status
FROM flights AS f INNER JOIN bookings AS b ON f.flight_id = b.flight_id
INNER JOIN routes AS r ON f.route_id = r.route_id
INNER JOIN users AS u ON b.user_id = u.user_id
WHERE b.booking_id = booking_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE display_flight (IN flight_id INT)
BEGIN
SELECT f.flight_id, f.flight_num, f.flight_date, f.flight_time, r.departure_loc, r.arrival_loc, f.departure_time, f.arrival_time
FROM flights AS f INNER JOIN routes AS r ON f.route_id = r.route_id
WHERE f.flight_id = flight_id;
END //
DELIMITER ;

-- SELECT * FROM view_all_booking;


-- call display_flight(1);

-- select * from bookings;


-- SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1;

-- drop procedure display_flight;

-- call search_flight(5, '2020/09/19');
-- SELECT * FROM flights ORDER BY flight_id DESC LIMIT 1;

-- DROP database flightbooking;
-- select * from flights where flight_id = 1;
-- SELECT email, pass FROM users WHERE email = 'vxt@gmail.com' AND pass = '1234';
