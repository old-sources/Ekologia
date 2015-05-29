CREATE TABLE menu
(
    id SERIAL PRIMARY KEY NOT NULL,
    lang VARCHAR(10) NOT NULL,
    role VARCHAR(255) NOT NULL,
    json VARCHAR NOT NULL
);
ALTER TABLE menu ADD CONSTRAINT u_lang_role UNIQUE (lang, role);

INSERT INTO menu(lang, role, json) values ('fr', 'admin', '[]');
INSERT INTO menu(lang, role, json) values ('fr', 'all', '[]');
INSERT INTO menu(lang, role, json) values ('en', 'admin', '[]');
INSERT INTO menu(lang, role, json) values ('en', 'all', '[]');