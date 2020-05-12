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

