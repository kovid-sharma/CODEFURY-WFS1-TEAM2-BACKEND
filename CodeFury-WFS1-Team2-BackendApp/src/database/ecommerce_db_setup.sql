
-- SQL file to set up the eCommerce database

-- 1. Create Database and Use It
CREATE DATABASE ecommerce_db;
USE ecommerce_db;

-- 2. Create Tables
CREATE TABLE users (
    user_id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type ENUM('CUSTOMER', 'ADMIN') DEFAULT 'CUSTOMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Product (
    productId VARCHAR(50) PRIMARY KEY,
    productName VARCHAR(100) NOT NULL,
    productDescription TEXT,
    price DOUBLE NOT NULL,
    isActive BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS subscriptions (
    subscriptionId VARCHAR(255) PRIMARY KEY,
    productId VARCHAR(255),
    customerId VARCHAR(255),
    type VARCHAR(50) NOT NULL,
    startDate DATE,
    endDate DATE,
    isActive BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (productId) REFERENCES Product(productId),
    FOREIGN KEY (customerId) REFERENCES users(user_id)
);

CREATE TABLE orders (
    order_id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDING', 'SHIPPED', 'DELIVERED') DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE order_items (
    order_item_id VARCHAR(255) PRIMARY KEY,
    order_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(productId) ON DELETE CASCADE
);

-- 3. Insert Dummy Data
INSERT INTO Product (productId, productName, productDescription, price, isActive) VALUES
    ('P001', 'Pizza Margherita', 'Classic pizza with tomato, mozzarella, and basil', 218.08, TRUE),
    ('P002', 'Veggie Burger', 'A healthy burger with fresh vegetables and vegan cheese', 89.00, TRUE),
    ('P003', 'Caesar Salad', 'Fresh romaine lettuce with Caesar dressing and croutons', 78.67, TRUE),
    ('P004', 'Chocolate Cake', 'Rich and moist chocolate cake', 44.00, TRUE),
    ('P005', 'Spaghetti Bolognese', 'Traditional Italian pasta with a rich meat sauce', 99.99, TRUE);

INSERT INTO users (user_id, username, email, password, user_type) VALUES
    ('U001', 'Aarav Sharma', 'aarav.sharma@gmail.com', 'A8d$kP!w7Z', 'ADMIN'),
    ('U002', 'Isha Patel', 'isha.patel@yahoo.in', 'J@4rL9*eQ', 'CUSTOMER'),
    ('U003', 'Rohan Gupta', 'rohan.gupta@gmail.com', 'X2b&V6#sP', 'CUSTOMER'),
    ('U004', 'Priya Singh', 'priya.singh@gmail.com', 'H5v%T3^wR', 'CUSTOMER'),
    ('U005', 'Vikram Reddy', 'vikram.reddy@yahoo.in', 'K9u@L1*oN', 'CUSTOMER');

INSERT INTO subscriptions (subscriptionId, productId, customerId, type, startDate, endDate, isActive) VALUES
    ('S001', 'P001', 'U002', 'MONTHLY', '2024-08-01', '2024-08-31', TRUE),
    ('S002', 'P002', 'U003', 'YEARLY', '2024-07-15', '2025-07-14', TRUE),
    ('S003', 'P003', 'U004', 'MONTHLY', '2024-08-10', '2024-09-09', TRUE),
    ('S004', 'P004', 'U005', 'WEEKLY', '2024-08-17', '2024-08-24', TRUE),
    ('S005', 'P005', 'U003', 'YEARLY', '2024-06-20', '2025-06-19', TRUE);

INSERT INTO orders (order_id, user_id, total_amount, status) VALUES
    ('O001', 'U002', 307.08, 'PENDING'),
    ('O002', 'U003', 89.00, 'SHIPPED'),
    ('O003', 'U004', 78.67, 'DELIVERED'),
    ('O004', 'U005', 143.99, 'PENDING'),
    ('O005', 'U003', 123.67, 'DELIVERED');

INSERT INTO order_items (order_item_id, order_id, product_id, quantity, price) VALUES
    ('OI001', 'O001', 'P001', 1, 218.08),
    ('OI002', 'O001', 'P003', 1, 78.67),
    ('OI003', 'O002', 'P002', 1, 89.00),
    ('OI004', 'O003', 'P003', 2, 78.67),
    ('OI005', 'O004', 'P005', 1, 99.99),
    ('OI006', 'O004', 'P004', 1, 44.00),
    ('OI007', 'O005', 'P001', 1, 218.08),
    ('OI008', 'O005', 'P002', 1, 89.00);

