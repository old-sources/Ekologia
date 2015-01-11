-- Page creation
CREATE TABLE page (
    id serial NOT NULL,
    url character varying(10000),
    javascript character varying(10000),
    html character varying(10000),
    css character varying(10000)
);

ALTER TABLE ONLY page
    ADD CONSTRAINT pages_pkey PRIMARY KEY (id);


-- Security creation
CREATE TABLE security (
    id serial NOT NULL,
    url character varying(512),
    diseable boolean
);

ALTER TABLE ONLY security
    ADD CONSTRAINT security_pkey PRIMARY KEY (id);


-- User creation
CREATE TABLE "account" (
    id serial NOT NULL,
    email character varying,
    password character varying,
    phone_number character varying,
	addressstreet character varying(255),
addresszipcode character varying(255),
addresscity character varying(255),
country character varying(255),
 avatar character varying(255),
description text,
roles character varying(255)
);

ALTER TABLE ONLY "account"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
