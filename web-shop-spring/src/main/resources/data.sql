INSERT INTO users ("username", "password", "first_name", "last_name") VALUES
    ('pera', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca', 'petar', 'panin'),
    ('mika', '$2y$12$ZSaHrzb6qz.8nsdWgYUfaOugfoLPBIdOKiA9x4sjyVcf.06/F27Ca', 'miki', 'mikic');


INSERT INTO authorities ("name") VALUES
    ('ROLE_USER'),
    ('ROLE_SALES_MANAGER'),
    ('ROLE_SELLER');

INSERT INTO user_authority("user_id", "authority_id") VALUES
    ('1', '1'),
    ('2', '1');


INSERT INTO item_category("code", "name") VALUES
    ('1KS', 'Mass consumption'),
    ('2K3', 'Audio and video'),
    ('13S', 'Desktop computers'),
    ('11Q', 'Laptops');

INSERT INTO item("code", "name", "item_category_id", "price", "number_on_stock", "refill", "status") VALUES
    ('990', 'ASUS ROG Strix', '4', '30000.99', '7', 'false', 'true'),
    ('800', 'Logitech Bluetooth Speakers', '2', '9999.99', '12', 'false', 'true'),
    ('750', 'Milk', '1', '120.99', '100', 'false', 'true');
