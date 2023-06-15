-- Inserting Users.
INSERT INTO t_user (name, email, password, roles) VALUES
('Administrator', 'admin@email.com', '{bcrypt}$2a$10$tzCoRr5b3qcMZHNaO.m8Rez28dzZhJNJE/PwkzOAUQEYd9Uworv6q', 'ROLE_USER,ROLE_ADMIN'),
('Edson Mendes', 'edson@email.com', '{bcrypt}$2a$10$x2ITWhDxP6qOAYTo/OxLVeRil/F9HOEJA7eojbIfAx6eUZJx4c.qe', 'ROLE_USER'),
('Lorem Ipsum', 'lorem@email.com', '{bcrypt}$2a$10$ah5/oU701WBM/2A1wNFp1uT5iGa61VIwcRcE8V9080VQa1XmE2mSy', 'ROLE_USER'),
('Dolor Sit', 'dolor@email.com', '{bcrypt}$2a$10$ah5/oU701WBM/2A1wNFp1uT5iGa61VIwcRcE8V9080VQa1XmE2mSy', 'ROLE_USER');

-- Inserting Events.
INSERT INTO t_event (title, description, event_date, user_id) VALUES
('Edson Event 1', 'Description 1', '2023-06-15T10:00:00', 2),
('Edson Event 2', 'Description 2', '2023-06-15T12:00:00', 2),
('Edson Event 3', 'Description 3', '2023-06-16T08:00:00', 2),
('Edson Event 4', 'Description 4', '2023-06-16T11:00:00', 2),
('Lorem Event 1', 'Description 1', '2023-06-15T10:00:00', 3),
('Lorem Event 2', 'Description 2', '2023-06-16T10:00:00', 3),
('Lorem Event 3', 'Description 3', '2023-06-16T17:00:00', 3),
('Lorem Event 4', 'Description 4', '2023-06-15T17:00:00', 3),
('Lorem Event 5', 'Description 5', '2023-06-15T19:00:00', 3),
('Lorem Event 6', 'Description 6', '2023-06-17T18:00:00', 3),
('Lorem Event 7', 'Description 7', '2023-06-17T18:00:00', 3);
