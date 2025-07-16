INSERT INTO users(id, name, surname, username, deleted, role, password) VALUES
(nextval('user_id_seq'), 'Sinad', 'Veerag', 'admin', '0', '1', '$2a$12$mpQLI4Z7VV26i3ZM4eaXkO.HUiAzWmsvvHFGeOd7DYSh3yYCoVRya'),
(nextval('user_id_seq'), 'Sinad', 'Veerag', 'user', '0', '0', '$2a$12$bwy/YYCeq4c8bz1ehP9PEOMC21ZV/IZIFly1/Tga.RU5ZHG82s//e');