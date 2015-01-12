-- NEVER CHANGE A FILE WHICH IS COMMITTED IN THIS DIRECTORY
-- Wiki creation
CREATE TABLE wiki
(
  id serial NOT NULL,
  title character varying(255) NOT NULL,
  language character varying(10) NOT NULL,
  canonical character varying(255) NOT NULL UNIQUE,
  editable boolean NOT NULL,
  visible boolean NOT NULL,
  group_id integer,
  parent_id integer,
  CONSTRAINT wiki_pkey PRIMARY KEY (id)
);

ALTER TABLE wiki
    ADD CONSTRAINT fk_wiki_group_id FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE wiki
    ADD CONSTRAINT fk_wiki_parent_id FOREIGN KEY (parent_id) REFERENCES wiki (id);
    

-- Wiki version creation
CREATE TABLE wikiversion
(
  id serial NOT NULL,
  date date NOT NULL DEFAULT now(),
  content text NOT NULL,
  active boolean NOT NULL,
  wiki_id integer NOT NULL,
  user_id integer,
  image character varying(255),
  CONSTRAINT wikiversion_pkey PRIMARY KEY (id)
);

ALTER TABLE wikiversion
    ADD CONSTRAINT fk_wikiversion_user_id FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE wikiversion
    ADD CONSTRAINT wikiversion_wiki_id_fkey FOREIGN KEY (wiki_id) REFERENCES wiki (id);


-- Wiki comment creation
CREATE TABLE wikicomment
(
  id serial NOT NULL,
  title character varying(255) NOT NULL,
  content text NOT NULL,
  date date NOT NULL DEFAULT now(),
  wiki_id integer,
  parent_id integer,
  user_id integer,
  CONSTRAINT wikicomment_pkey PRIMARY KEY (id)
);

ALTER TABLE wikicomment
    ADD CONSTRAINT fk_wikicomment_user_id FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE wikicomment
    ADD CONSTRAINT wikicomment_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES wikicomment (id);

ALTER TABLE wikicomment
    ADD CONSTRAINT wikicomment_wiki_id_fkey FOREIGN KEY (wiki_id) REFERENCES wiki (id);