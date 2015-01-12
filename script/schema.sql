--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2015-01-12 02:41:19 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 185 (class 3079 OID 11756)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2022 (class 0 OID 0)
-- Dependencies: 185
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 91321)
-- Name: account; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE account (
    id integer NOT NULL,
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


--
-- TOC entry 174 (class 1259 OID 91319)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 174
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE account_id_seq OWNED BY account.id;


--
-- TOC entry 177 (class 1259 OID 91333)
-- Name: group; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "group" (
    id integer NOT NULL,
    name character varying(255),
    canonical character varying(255)
);


--
-- TOC entry 176 (class 1259 OID 91331)
-- Name: group_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 176
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE group_id_seq OWNED BY "group".id;


--
-- TOC entry 171 (class 1259 OID 91299)
-- Name: page; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE page (
    id integer NOT NULL,
    url character varying(10000),
    javascript character varying(10000),
    html character varying(10000),
    css character varying(10000)
);


--
-- TOC entry 170 (class 1259 OID 91297)
-- Name: page_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE page_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2025 (class 0 OID 0)
-- Dependencies: 170
-- Name: page_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE page_id_seq OWNED BY page.id;


--
-- TOC entry 173 (class 1259 OID 91310)
-- Name: security; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE security (
    id integer NOT NULL,
    url character varying(512),
    diseable boolean
);


--
-- TOC entry 172 (class 1259 OID 91308)
-- Name: security_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE security_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 172
-- Name: security_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE security_id_seq OWNED BY security.id;


--
-- TOC entry 178 (class 1259 OID 91344)
-- Name: user_group; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE user_group (
    user_id integer NOT NULL,
    group_id integer NOT NULL,
    requested date,
    accepted date,
    roles character varying(255)
);


--
-- TOC entry 180 (class 1259 OID 91356)
-- Name: wiki; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE wiki (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    language character varying(10) NOT NULL,
    canonical character varying(255) NOT NULL,
    editable boolean NOT NULL,
    visible boolean NOT NULL,
    group_id integer,
    parent_id integer
);


--
-- TOC entry 179 (class 1259 OID 91354)
-- Name: wiki_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE wiki_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 179
-- Name: wiki_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE wiki_id_seq OWNED BY wiki.id;


--
-- TOC entry 184 (class 1259 OID 91396)
-- Name: wikicomment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE wikicomment (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    content text NOT NULL,
    date date DEFAULT now() NOT NULL,
    wiki_id integer,
    parent_id integer,
    user_id integer
);


--
-- TOC entry 183 (class 1259 OID 91394)
-- Name: wikicomment_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE wikicomment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 183
-- Name: wikicomment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE wikicomment_id_seq OWNED BY wikicomment.id;


--
-- TOC entry 182 (class 1259 OID 91379)
-- Name: wikiversion; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE wikiversion (
    id integer NOT NULL,
    date date DEFAULT now() NOT NULL,
    content text NOT NULL,
    active boolean NOT NULL,
    wiki_id integer NOT NULL,
    user_id integer,
    image character varying(255)
);


--
-- TOC entry 181 (class 1259 OID 91377)
-- Name: wikiversion_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE wikiversion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 181
-- Name: wikiversion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE wikiversion_id_seq OWNED BY wikiversion.id;


--
-- TOC entry 1876 (class 2604 OID 91324)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY account ALTER COLUMN id SET DEFAULT nextval('account_id_seq'::regclass);


--
-- TOC entry 1877 (class 2604 OID 91336)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "group" ALTER COLUMN id SET DEFAULT nextval('group_id_seq'::regclass);


--
-- TOC entry 1874 (class 2604 OID 91302)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY page ALTER COLUMN id SET DEFAULT nextval('page_id_seq'::regclass);


--
-- TOC entry 1875 (class 2604 OID 91313)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY security ALTER COLUMN id SET DEFAULT nextval('security_id_seq'::regclass);


--
-- TOC entry 1878 (class 2604 OID 91359)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY wiki ALTER COLUMN id SET DEFAULT nextval('wiki_id_seq'::regclass);


--
-- TOC entry 1881 (class 2604 OID 91399)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikicomment ALTER COLUMN id SET DEFAULT nextval('wikicomment_id_seq'::regclass);


--
-- TOC entry 1879 (class 2604 OID 91382)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikiversion ALTER COLUMN id SET DEFAULT nextval('wikiversion_id_seq'::regclass);


--
-- TOC entry 1884 (class 2606 OID 91307)
-- Name: pages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY page
    ADD CONSTRAINT pages_pkey PRIMARY KEY (id);


--
-- TOC entry 1890 (class 2606 OID 91341)
-- Name: pk_group; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT pk_group PRIMARY KEY (id);


--
-- TOC entry 1894 (class 2606 OID 91348)
-- Name: pk_user_group; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT pk_user_group PRIMARY KEY (user_id, group_id);


--
-- TOC entry 1886 (class 2606 OID 91318)
-- Name: security_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY security
    ADD CONSTRAINT security_pkey PRIMARY KEY (id);


--
-- TOC entry 1892 (class 2606 OID 91343)
-- Name: u_group_canonical; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT u_group_canonical UNIQUE (canonical);


--
-- TOC entry 1888 (class 2606 OID 91329)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY account
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 1896 (class 2606 OID 91366)
-- Name: wiki_canonical_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wiki
    ADD CONSTRAINT wiki_canonical_key UNIQUE (canonical);


--
-- TOC entry 1898 (class 2606 OID 91364)
-- Name: wiki_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wiki
    ADD CONSTRAINT wiki_pkey PRIMARY KEY (id);


--
-- TOC entry 1902 (class 2606 OID 91405)
-- Name: wikicomment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikicomment
    ADD CONSTRAINT wikicomment_pkey PRIMARY KEY (id);


--
-- TOC entry 1900 (class 2606 OID 91388)
-- Name: wikiversion_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikiversion
    ADD CONSTRAINT wikiversion_pkey PRIMARY KEY (id);


--
-- TOC entry 1903 (class 2606 OID 91349)
-- Name: fk_user_group_group_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY user_group
    ADD CONSTRAINT fk_user_group_group_id FOREIGN KEY (group_id) REFERENCES "group"(id);


--
-- TOC entry 1904 (class 2606 OID 91367)
-- Name: fk_wiki_group_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wiki
    ADD CONSTRAINT fk_wiki_group_id FOREIGN KEY (group_id) REFERENCES "group"(id);


--
-- TOC entry 1905 (class 2606 OID 91372)
-- Name: fk_wiki_parent_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wiki
    ADD CONSTRAINT fk_wiki_parent_id FOREIGN KEY (parent_id) REFERENCES wiki(id);


--
-- TOC entry 1907 (class 2606 OID 91406)
-- Name: wikicomment_parent_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikicomment
    ADD CONSTRAINT wikicomment_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES wikicomment(id);


--
-- TOC entry 1908 (class 2606 OID 91411)
-- Name: wikicomment_wiki_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikicomment
    ADD CONSTRAINT wikicomment_wiki_id_fkey FOREIGN KEY (wiki_id) REFERENCES wiki(id);


--
-- TOC entry 1906 (class 2606 OID 91389)
-- Name: wikiversion_wiki_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY wikiversion
    ADD CONSTRAINT wikiversion_wiki_id_fkey FOREIGN KEY (wiki_id) REFERENCES wiki(id);


-- Completed on 2015-01-12 02:41:19 CET

--
-- PostgreSQL database dump complete
--

