create schema bloodbank;
use bloodbank;
CREATE TABLE blood_donor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT,
    bloodGroup VARCHAR(10) NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    state VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    area VARCHAR(255) NOT NULL,
    availability VARCHAR(3) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO blood_donor (name, age, blood_Group, mobile, state, district, area, availability, email, password,gender)
VALUES
('Saravanan', 21, 'B+', '6383526900', 'TamilNadu', 'Chennai', 'Thiruvanmiyur', 'No', 'dass001@gmail.com', 'password123','Male');