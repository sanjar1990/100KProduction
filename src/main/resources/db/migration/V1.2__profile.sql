INSERT INTO profile (name, surname, email, birthday, phone, password, status, role, id)
SELECT
    'John', 'Doe', 'john.doe@example.com', '1990-01-01', '+1234567890', 'password1', 'ACTIVE', 'ROLE_ADMIN', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'
WHERE NOT EXISTS (SELECT id FROM profile WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d479');

INSERT INTO profile (name, surname, email, birthday, phone, password, status, role, id)
SELECT
    'Alice', 'Smith', 'alice.smith@example.com', '1985-03-15', '+9876543210', 'pass123', 'ACTIVE', 'ROLE_ADMIN', 'f47ac10b-58cc-4372-a567-0e02b2c3d480'
WHERE NOT EXISTS (SELECT id FROM profile WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d480');

INSERT INTO profile (name, surname, email, birthday, phone, password, status, role, id)
SELECT
    'Bob', 'Johnson', 'bob.johnson@example.com', '1978-08-22', '+1122334455', 'p@ssword', 'ACTIVE', 'ROLE_ADMIN', 'f47ac10b-58cc-4372-a567-0e02b2c3d481'
WHERE NOT EXISTS (SELECT id FROM profile WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d481');

INSERT INTO profile (name, surname, email, birthday, phone, password, status, role, id)
SELECT
    'Sarah', 'Miller', 'sarah.miller@example.com', '1993-05-10', '+1122334455', 'secure123', 'ACTIVE', 'ROLE_ADMIN', 'f47ac10b-58cc-4372-a567-0e02b2c3d482'
WHERE NOT EXISTS (SELECT id FROM profile WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d482');

INSERT INTO profile (name, surname, email, birthday, phone, password, status, role, id)
SELECT
    'Eve', 'Brown', 'eve.brown@example.com', '1982-12-05', '+9988776655', 'password123', 'ACTIVE', 'ROLE_ADMIN', 'f47ac10b-58cc-4372-a567-0e02b2c3d483'
WHERE NOT EXISTS (SELECT id FROM profile WHERE id = 'f47ac10b-58cc-4372-a567-0e02b2c3d483');
