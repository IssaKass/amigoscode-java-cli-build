-- It is important to delete from child tables first to avoid foreign key constraint issues.
DELETE
FROM car_booking;
DELETE
FROM car;
DELETE
FROM app_user;

-- Insert some users
-- Using a known UUID for clarity and easy referencing in other tables.
INSERT INTO app_user (id, name)
VALUES ('47e5b5e1-5e26-444f-801b-c689945c71d6', 'John Doe'),
       ('b0d7a9d0-c116-434a-91d1-6e3e5c9b6f4e', 'Jane Smith'),
       ('2a1b32d2-8b6a-4f24-9c5c-7e3f2d2b5c4a', 'Peter Jones');

-- Insert some cars
INSERT INTO car (reg_number, rental_price_per_day, brand, electric)
VALUES ('ABC-123', 50.00, 'AUDI', FALSE),
       ('XYZ-456', 75.50, 'TESLA', TRUE),
       ('MNO-789', 60.00, 'MERCEDES', FALSE),
       ('PQR-012', 80.00, 'VW', FALSE),
       ('DEF-234', 55.00, 'AUDI', TRUE),
       ('GHI-567', 65.00, 'MERCEDES', FALSE);

-- Insert some car bookings
-- A confirmed booking for John Doe and the Audi
INSERT INTO car_booking (booking_id, user_id, car_id, booking_time, canceled)
VALUES (RANDOM_UUID(), '47e5b5e1-5e26-444f-801b-c689945c71d6', 'ABC-123', '2025-09-11 10:00:00', FALSE);

-- A confirmed booking for Jane Smith and the Tesla
INSERT INTO car_booking (booking_id, user_id, car_id, booking_time, canceled)
VALUES (RANDOM_UUID(), 'b0d7a9d0-c116-434a-91d1-6e3e5c9b6f4e', 'XYZ-456', '2025-09-10 15:30:00', FALSE);

-- A canceled booking for Peter Jones and the Mercedes
INSERT INTO car_booking (booking_id, user_id, car_id, booking_time, canceled)
VALUES (RANDOM_UUID(), '2a1b32d2-8b6a-4f24-9c5c-7e3f2d2b5c4a', 'MNO-789', '2025-09-09 11:45:00', FALSE);

-- Another confirmed booking for John Doe, this time for the VW
INSERT INTO car_booking (booking_id, user_id, car_id, booking_time, canceled)
VALUES (RANDOM_UUID(), '47e5b5e1-5e26-444f-801b-c689945c71d6', 'PQR-012', '2025-09-11 09:15:00', FALSE);