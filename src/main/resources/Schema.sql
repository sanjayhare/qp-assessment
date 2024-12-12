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

CREATE TABLE IF NOT EXISTS `Users` (
  `person_Id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email_Id` varchar(50) NOT NULL,
  `mobile_Number` varchar(20) NOT NULL,
  `date_Of_Birth` varchar(50) NOT NULL,
  `address` varchar(20) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `authorities` varchar(200) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`person_Id`)
);

CREATE TABLE carts (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(person_Id)
);


CREATE TABLE cart_items (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

