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


INSERT INTO item_category("name") VALUES
    ('Mass consumption'),
    ('Audio and video'),
    ('Desktop computer'),
    ('Laptop'),
    ('TV');


INSERT INTO promotion("name", "start_time", "end_time", "duration","percentage_discount") VALUES
    ('Pre new year sale','2020-12-20', '2020-12-24', '345600000', '20.0'),
    ('Fall promotion','2020-10-14', '2020-10-24','864000000', '10.0');

INSERT INTO promotion_item_category("promotion_id", "item_category_id") VALUES
    ('1', '1'),
    ('1', '2'),
    ('1', '3'),
    ('1', '4'),
    ('2', '3'),
    ('2', '4'),
    ('2', '5');

INSERT INTO item("name", "item_category_id", "price", "number_on_stock", "refill", "status") VALUES
    ('Milk', '1', '120.99', '100', 'false', 'true'),
    ('Logitech Bluetooth Speakers', '2', '9999.99', '12', 'false', 'true'),
    ('Intel Desktop PC', '3', '100000.00', '8', 'false', 'false')  ,
    ('ASUS ROG Strix', '4', '30000.99', '7', 'false', 'true'),
    ('LG LED TV', '5', '47999.99', '40', 'false', 'false');                                                                                                ;
