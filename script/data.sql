--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2014-12-31 02:23:58 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11756)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1964 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 74818)
-- Name: page; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE page (
    id integer NOT NULL,
    url character varying,
    javascript character varying(10000),
    html character varying(10000),
    css character varying(10000)
);


--
-- TOC entry 171 (class 1259 OID 74824)
-- Name: pages_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE pages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1965 (class 0 OID 0)
-- Dependencies: 171
-- Name: pages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE pages_id_seq OWNED BY page.id;


--
-- TOC entry 175 (class 1259 OID 74842)
-- Name: security; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE security (
    id integer NOT NULL,
    url character varying(512),
    diseable boolean
);


--
-- TOC entry 174 (class 1259 OID 74840)
-- Name: security_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE security_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1966 (class 0 OID 0)
-- Dependencies: 174
-- Name: security_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE security_id_seq OWNED BY security.id;


--
-- TOC entry 172 (class 1259 OID 74826)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "user" (
    id integer NOT NULL,
    email character varying,
    password character varying,
    phone_number character varying,
    first_name character varying(512),
    last_name character varying(512)
);


--
-- TOC entry 173 (class 1259 OID 74832)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1967 (class 0 OID 0)
-- Dependencies: 173
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 1842 (class 2604 OID 74834)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY page ALTER COLUMN id SET DEFAULT nextval('pages_id_seq'::regclass);


--
-- TOC entry 1844 (class 2604 OID 74845)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY security ALTER COLUMN id SET DEFAULT nextval('security_id_seq'::regclass);


--
-- TOC entry 1843 (class 2604 OID 74835)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 1846 (class 2606 OID 74837)
-- Name: pages_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY page
    ADD CONSTRAINT pages_pkey PRIMARY KEY (id);


--
-- TOC entry 1850 (class 2606 OID 74850)
-- Name: security_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY security
    ADD CONSTRAINT security_pkey PRIMARY KEY (id);


--
-- TOC entry 1848 (class 2606 OID 74839)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


-- Completed on 2014-12-31 02:23:58 CET

--
-- PostgreSQL database dump complete
--

