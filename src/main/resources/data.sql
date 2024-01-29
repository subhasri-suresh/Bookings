-- Test data for Users table
INSERT INTO CUSTOMERS(user_name, password, email, address) VALUES('Tim', 'pwd1', 'tim@gmail.com', '12, zollnerstraße, Berlin');
INSERT INTO CUSTOMERS(user_name, password, email, address) VALUES('Mark', 'pwd2', 'mark@gmail.com', '12, zollnerstraße, Munich');
--
---- Test data for Hotels table
INSERT INTO HOTELS(hotel_name, contact_no, address, city, country, zipcode) VALUES('IBIS', '012-21121212', 'Bahnhof straße', 'Munich', 'Germany', '83004');
INSERT INTO HOTELS(hotel_name, contact_no, address, city, country, zipcode) VALUES('STAYIN', '012-31431214', 'Ludwig straße', 'Munich', 'Germany', '83014');
INSERT INTO HOTELS(hotel_name, contact_no, address, city, country, zipcode) VALUES('MOTEL ONE', '012-8981210', 'Ludwig straße', 'Berlin', 'Germany', '87019');
INSERT INTO HOTELS(hotel_name, contact_no, address, city, country, zipcode) VALUES('WaterFront', '031-7981219', 'Ludwig straße', 'Berlin', 'Germany', '87021');
--
--
---- Test data for Rooms table
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(1, 'Basic', 1, 10);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(1, 'Deluxe', 2, 5);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(2, 'Standard', 1, 10);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(2, 'Executive', 2, 20);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(3, 'Standard', 1, 20);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(3, 'Deluxe', 2, 10);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(4, 'Standard', 1, 30);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(4, 'Executive', 2, 20);
INSERT INTO ROOMS(hotel_id, room_name, room_type, total_rooms) VALUES(4, 'Suite', 3, 10);

-- Test data for AvailableRooms table
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(1, 1, 10, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(1, 1, 0, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(1, 1, 10, '2024-01-31');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(1, 2, 5, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(1, 2, 0, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(1, 2, 5, '2024-01-31');

INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(2, 1, 10, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(2, 1, 10, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(2, 1, 0, '2024-01-31');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(2, 2, 20, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(2, 2, 20, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(2, 2, 0, '2024-01-31');

INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(3, 1, 20, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(3, 1, 20, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(3, 1, 20, '2024-01-31');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(3, 2, 10, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(3, 2, 10, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(3, 2, 10, '2024-01-31');

INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 1, 30, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 1, 30, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 1, 30, '2024-01-31');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 2, 20, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 2, 20, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 2, 20, '2024-01-31');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 3, 10, '2024-01-29');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 3, 10, '2024-01-30');
INSERT INTO AVAILABLE_ROOMS(hotel_id, room_id, count, available_date) VALUES(4, 3, 10, '2024-01-31');