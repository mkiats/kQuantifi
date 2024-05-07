-- Create table
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    photo_url VARCHAR(255)
);

-- Populate table
INSERT INTO users (username, email, password, photo_url)
VALUES 
    ('user1', 'user1@example.com', 'password1', 'http://example.com/user1.jpg'),
    ('user2', 'user2@example.com', 'password2', 'http://example.com/user2.jpg'),
    ('user3', 'user3@example.com', 'password3', 'http://example.com/user3.jpg'),
    ('user4', 'user4@example.com', 'password4', 'http://example.com/user4.jpg'),
    ('user5', 'user5@example.com', 'password5', 'http://example.com/user5.jpg'),
    ('user6', 'user6@example.com', 'password6', 'http://example.com/user6.jpg'),
    ('user7', 'user7@example.com', 'password7', 'http://example.com/user7.jpg'),
    ('user8', 'user8@example.com', 'password8', 'http://example.com/user8.jpg'),
    ('user9', 'user9@example.com', 'password9', 'http://example.com/user9.jpg'),
    ('user10', 'user10@example.com', 'password10', 'http://example.com/user10.jpg');