CREATE DATABASE bloodbankdb;
USE bloodbankdb;

CREATE TABLE donor (
    donor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10),
    blood_group VARCHAR(5) NOT NULL,
    phone VARCHAR(15),
    address VARCHAR(200)
);

CREATE TABLE recipient (
    recipient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10),
    blood_group VARCHAR(5) NOT NULL,
    phone VARCHAR(15),
    disease VARCHAR(100)
);

CREATE TABLE blood_stock (
    stock_id INT PRIMARY KEY AUTO_INCREMENT,
    blood_group VARCHAR(5) NOT NULL UNIQUE,
    units_available INT NOT NULL
);