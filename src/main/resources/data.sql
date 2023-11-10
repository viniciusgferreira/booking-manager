INSERT INTO GUESTS (uid, first_name, last_name, birth_date, picture_url, address, city, state, phone_number, email, created_time)
VALUES ('9a1a5e6a-fa59-4d33-814b-1a9438b3a546', 'John', 'Doe', '1990-05-15', 'example.jpg', '123 Main St', 'New York', 'NY', '555-555-5555', 'john@example.com', '2023-11-08T14:30:00.000');
INSERT INTO guests (uid, first_name, last_name, birth_date, picture_url, address, city, state, phone_number, email, created_time)
VALUES ('f3e62516-9063-498b-b30a-24dca471471e', 'Vinicius', 'Ferreira', '1993-02-01', 'photo.jpg', '123 Cabo Frio St', 'Orlando', 'FL', '555-555-5555', 'vinicius@example.com', '2023-11-08T14:34:00.000');

INSERT INTO properties (uid, type, name, is_active, base_guests, maximum_guests, bedrooms, bathrooms, address, city, state, picture_url)
VALUES ('4b674b0c-1e3c-4843-88f7-7031f24a4dbf', 'Apartment', 'Sample Property', true, 4, 8, 3, 2, '123 Main St', 'New York', 'NY', 'example.jpg');
INSERT INTO properties (uid, type, name, is_active, base_guests, maximum_guests, bedrooms, bathrooms, address, city, state, picture_url)
VALUES ('2e44f70a-9b56-482c-a24e-5d68f4f7a6b1', 'Villa', 'Luxury Retreat', true, 10, 16, 6, 5, '456 Villa Drive', 'Orlando', 'FL', 'luxury-villa.jpg');

INSERT INTO bookings (check_in_date,check_out_date,guest_id,guest_number,property_id,uid) values ('2023-10-20', '2023-10-25', 'f3e62516-9063-498b-b30a-24dca471471e', 2, '2e44f70a-9b56-482c-a24e-5d68f4f7a6b1','8c5d8c43-1342-458a-bd7b-67a32562e81a');

INSERT INTO blocks (start_date,end_date,reason,uid,property_id) values ('2023-10-26','2023-10-28','fixing all bathrooms', 'e1d26b69-3dba-435e-ba24-de77bd9c66ac','2e44f70a-9b56-482c-a24e-5d68f4f7a6b1');
