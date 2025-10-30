-- database
DROP DATABASE IF EXISTS hotel_db;
CREATE DATABASE hotel_db;
USE hotel_db;

-- customers
CREATE TABLE customers (
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
	name TEXT,
    email TEXT,
    city TEXT
);

-- rooms
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    is_available BOOLEAN NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    room_type TEXT NOT NULL
);

-- bookings
CREATE TABLE bookings (
	booking_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT,
    customer_id INT,
    end DATE,
	FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE,
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

-- data
INSERT INTO customers(name, email, city)
	VALUES	('Alice Johnson', 'alice.johnson@example.com', 'New York'),
			('Brian Smith', 'brian.smith@example.com', 'Los Angeles'),
			('Catherine Lee', 'catherine.lee@example.com', 'Chicago'),
			('David Brown', 'david.brown@example.com', 'Houston'),
			('Emma Wilson', 'emma.wilson@example.com', 'Phoenix');
            
INSERT INTO rooms(is_available, price, room_type)
	VALUES	(true, 100, "Single"),
			(true, 200, "Double"),
            (true, 200, "Haunted"),
            (false, 3000, "Presidential"),
            (false, 400, "Suite"),
            (false, 900, "Deluxe");
            
INSERT INTO bookings(room_id, customer_id, end)
	VALUES	(4, 1, "2025-12-24"),
			(5, 2, "2026-01-05"),
			(6, 3, "2025-11-05");
