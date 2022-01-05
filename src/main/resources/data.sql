INSERT INTO roles (name) VALUES
('ROLE_EMPLOYE'),
('ROLE_ADMIN'),
('ROLE_CLIENT');

INSERT INTO users ( id, email, password, presentation, username)
VALUES (1, 'admin@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis l une des jumelles de farima', 'user');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);

INSERT INTO users ( id, email, password, presentation, username)
VALUES (2, 'mari@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis mari entrepreneuse', 'mari');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 1);

INSERT INTO users ( id, email, password, presentation, username)
VALUES (3, 'sylvain@mail.com', '$2a$10$YCdwtaJloPlmt8Ry4aOkY.dMClxQpYYjHv7AxvVTF5f7Em1GXR.my',
        'je suis sylvain investisser', 'sylvain');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 3);




