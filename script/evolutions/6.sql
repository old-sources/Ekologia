﻿CREATE TABLE statut (
    id serial NOT NULL,
    code character varying(10),
    CONSTRAINT statut_pkey PRIMARY KEY (id)
);


CREATE TABLE statut_lang (
    id serial NOT NULL,
    description character varying(255),
    langue character varying(2),
    statut_id integer NOT NULL,
    CONSTRAINT statut_lang_pkey PRIMARY KEY (id),
    CONSTRAINT fk_statut_lang_statut_id FOREIGN KEY (statut_id) REFERENCES "statut" (id)
);

CREATE TABLE user_statut (
	user_id integer NOT NULL,
	statut_id integer NOT NULL,
	CONSTRAINT pk_user_statut PRIMARY KEY (user_id, statut_id),
	CONSTRAINT fk_user_statut_user_id FOREIGN KEY (user_id)
		REFERENCES "account" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_user_statut_statut_id FOREIGN KEY (statut_id)
		REFERENCES "statut" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE role_user (
    id serial NOT NULL,
    code character varying(10),
    CONSTRAINT role_user_pkey PRIMARY KEY (id)
);

CREATE TABLE role_user_lang (
	id serial NOT NULL,
	description character varying(255),
	langue character varying(2),
	role_user_id integer NOT NULL,
	CONSTRAINT role_user_lang_pkey PRIMARY KEY(id),
	CONSTRAINT fk_role_user_id FOREIGN KEY (role_user_id) REFERENCES "role_user" (id)
);

CREATE TABLE user_role_user (
	user_id integer NOT NULL,
	role_user_id integer NOT NULL,
	CONSTRAINT pk_user_role_user PRIMARY KEY (user_id, role_user_id),
	CONSTRAINT fk_user_role_user_user_id FOREIGN KEY (user_id)
		REFERENCES "account" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_user_role_user_role_user_id FOREIGN KEY (role_user_id)
		REFERENCES "role_user" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE role_user_group (
	id serial NOT NULL,
	code character varying(10),
	CONSTRAINT role_user_group_pkey PRIMARY KEY (id)
);

CREATE TABLE role_user_group_lang (
	id serial NOT NULL,
	description character varying(255),
	langue character varying(2),
	role_user_group_id integer NOT NULL,
	CONSTRAINT role_user_group_lang_pkey PRIMARY KEY (id),
	CONSTRAINT fk_role_user_group_id FOREIGN KEY (role_user_group_id) REFERENCES "role_user_group" (id)
);


CREATE TABLE user_group_role_user_group (
	user_group_user_id integer NOT NULL,
	user_group_group_id integer NOT NULL,
	role_user_group_id integer NOT NULL,
	CONSTRAINT pk_user_group_role_user_group PRIMARY KEY (user_group_user_id, user_group_group_id, role_user_group_id),
	CONSTRAINT fk_user_group_role_user_group_user_group_id FOREIGN KEY (user_group_user_id, user_group_group_id ) REFERENCES "user_group" (user_id, group_id),
	CONSTRAINT fk_user_group_role_user_group_role_user_group_id FOREIGN KEY (role_user_group_id)
		REFERENCES "role_user_group" (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO role_user_group (id,code) VALUES (1,'ADM'),(2,'MBR');

INSERT INTO role_user_group_lang (id,description,langue,role_user_group_id) VALUES (1,'Administrateur','fr',1)
,(2,'Administrator','en',1)
,(3,'Membre','fr',2)
,(4,'Member','en',2);



