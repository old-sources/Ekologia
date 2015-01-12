
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
    ADD CONSTRAINT fk_user_group_user_id FOREIGN KEY (user_id) REFERENCES "account" (id);
