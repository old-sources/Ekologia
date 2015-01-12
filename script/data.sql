--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2015-01-12 02:40:19 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2021 (class 0 OID 91321)
-- Dependencies: 175
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (id, email, password, phone_number, addressstreet, addresszipcode, addresscity, country, avatar, description, roles) VALUES (3, 'simon.louvet.zen@gmail.com', 'zenate', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '');
INSERT INTO account (id, email, password, phone_number, addressstreet, addresszipcode, addresscity, country, avatar, description, roles) VALUES (2, 'user3', 'user1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '');


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 174
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_id_seq', 7, true);


--
-- TOC entry 2023 (class 0 OID 91333)
-- Dependencies: 177
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 176
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('group_id_seq', 1, false);


--
-- TOC entry 2017 (class 0 OID 91299)
-- Dependencies: 171
-- Data for Name: page; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO page (id, url, javascript, html, css) VALUES (6, 'apage', NULL, 'bonjour2
https://lh4.googleusercontent.com/-dUQf94H_zmw/VLMhMFbf8-I/AAAAAAAAC9M/OipBy0et2Jk/w1000-h400-no/logo%2Btexte%2Bbanniere.png', NULL);
INSERT INTO page (id, url, javascript, html, css) VALUES (1, 'home', NULL, '<div class="uk-grid" data-uk-grid-margin="">
	<div class="uk-width-1-1 uk-text-center">
		<img src="
https://lh4.googleusercontent.com/-dUQf94H_zmw/VLMhMFbf8-I/AAAAAAAAC9M/OipBy0et2Jk/w1000-h400-no/logo%2Btexte%2Bbanniere.png" style="height:200px"/>
		<h1 class="uk-heading-large">Ekologia</h1>
		<p class="uk-text-large">bienvenu sur le nouveau site</p>
	</div>
</div>', NULL);


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 170
-- Name: page_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('page_id_seq', 6, true);


--
-- TOC entry 2019 (class 0 OID 91310)
-- Dependencies: 173
-- Data for Name: security; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO security (id, url, diseable) VALUES (1, 'admin/userList', false);


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 172
-- Name: security_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('security_id_seq', 1, true);


--
-- TOC entry 2024 (class 0 OID 91344)
-- Dependencies: 178
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2026 (class 0 OID 91356)
-- Dependencies: 180
-- Data for Name: wiki; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 179
-- Name: wiki_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wiki_id_seq', 1, false);


--
-- TOC entry 2030 (class 0 OID 91396)
-- Dependencies: 184
-- Data for Name: wikicomment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 183
-- Name: wikicomment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wikicomment_id_seq', 1, false);


--
-- TOC entry 2028 (class 0 OID 91379)
-- Dependencies: 182
-- Data for Name: wikiversion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 181
-- Name: wikiversion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wikiversion_id_seq', 1, false);


-- Completed on 2015-01-12 02:40:19 CET

--
-- PostgreSQL database dump complete
--

