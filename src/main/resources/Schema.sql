create database grocery;
use grocery;

CREATE TABLE grocery_items (
	`id` int AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(255) NOT NULL UNIQUE,
	`description` TEXT,
	`price` DECIMAL(10,2) NOT NULL,
	`quantity` INT NOT NULL,
	`created_at` TIMESTAMP NOT NULL,
	`created_by` varchar(50) NOT NULL,
	`updated_at` TIMESTAMP DEFAULT NULL,
	`updated_by` varchar(50) DEFAULT NULL
);