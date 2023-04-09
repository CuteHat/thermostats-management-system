-- Insert users
INSERT INTO users (email, name, password, created_at)
VALUES ('skinnyeric@gmail.com', 'Eric Cartman', '$2a$10$ZymzxI3d9sK6Uk5K7bykDumTuCDCJUMf07Kzg7KDC0Ci.2Ou/WPdW', now()),
       ('feminineandrew@gmail.com', 'Andrew Tate', '$2a$10$ZymzxI3d9sK6Uk5K7bykDumTuCDCJUMf07Kzg7KDC0Ci.2Ou/WPdW',
        now()),
       ('datafeeder@gmail.com', 'Wise Capybara', '$2a$10$ZymzxI3d9sK6Uk5K7bykDumTuCDCJUMf07Kzg7KDC0Ci.2Ou/WPdW', now());

-- Assign scopes to users
INSERT INTO users_scopes (user_id, scope_id)
SELECT u.id, s.id
FROM users u
         JOIN scopes s ON
    (u.email = 'datafeeder@gmail.com' AND s.name = 'FEED_TEMPERATURE_DATA');
