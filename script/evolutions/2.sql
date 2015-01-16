-- NEVER CHANGE A FILE WHICH IS COMMITTED IN THIS DIRECTORY
-- User modification
ALTER TABLE "user" ADD COLUMN addressstreet character varying(255);
ALTER TABLE "user" ADD COLUMN addresszipcode character varying(255);
ALTER TABLE "user" ADD COLUMN addresscity character varying(255);
ALTER TABLE "user" ADD COLUMN country character varying(255);
ALTER TABLE "user" ADD COLUMN avatar character varying(255);
ALTER TABLE "user" ADD COLUMN description text;
ALTER TABLE "user" ADD COLUMN roles character varying(255);
ALTER TABLE "user" DROP COLUMN first_name;
ALTER TABLE "user" DROP COLUMN last_name;


-- Group creation
CREATE TABLE "group"
(
  id serial NOT NULL,
  name character varying(255),
  canonical character varying(255),
  CONSTRAINT pk_group PRIMARY KEY (id),
  CONSTRAINT u_group_canonical UNIQUE (canonical)
);


-- User Group creation
CREATE TABLE user_group
(
  user_id integer NOT NULL,
  group_id integer NOT NULL,
  requested date,
  accepted date,
  roles character varying(255),
  CONSTRAINT pk_user_group PRIMARY KEY (user_id, group_id)
);

ALTER TABLE user_group
    ADD CONSTRAINT fk_user_group_group_id FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE user_group
    ADD CONSTRAINT fk_user_group_user_id FOREIGN KEY (user_id) REFERENCES "user" (id);