--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2015-03-27 19:44:08 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1977 (class 0 OID 91321)
-- Dependencies: 175
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (id, email, password, phone_number, addressstreet, addresszipcode, addresscity, country, avatar, description, roles, salt, discriminator, firstname, lastname, birthday, orgname, activity, type) VALUES (26, 'contact@ekologia.coop', '$6$FiRAMp48IE$p4YcKd27pHrPw3Y0Aax1yfEvGh2nzgFYLtlDGu/6yKlBHp1z.gYDAWKK3WXo19olb28aP41pWlNH4y4q2SXZB/', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '$6$FiRAMp48IE', 'i', '', '', '2015-03-01 00:00:00', NULL, NULL, NULL);


--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 174
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_id_seq', 26, true);


-- Completed on 2015-03-27 19:44:09 CET

--
-- PostgreSQL database dump complete
--

