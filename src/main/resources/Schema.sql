create database grocery;
use grocery;

CREATE TABLE products (
	`product_id` int AUTO_INCREMENT PRIMARY KEY,
	`product_name` VARCHAR(255) NOT NULL UNIQUE,
	`product_description` TEXT,
	`product_price` DECIMAL(10,2) NOT NULL,
	`product_quantity` INT NOT NULL,
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
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);


CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users(person_Id)
);

CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
	product_id INT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
	FOREIGN KEY (product_id) REFERENCES products(product_id)
);

