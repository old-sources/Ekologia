--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2015-01-28 17:55:37 CET

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

INSERT INTO account (id, email, password, phone_number, addressstreet, addresszipcode, addresscity, country, avatar, description, roles, salt, discriminator, firstname, lastname, birthday, orgname, activity, type) VALUES (8, 'simon.louvet.zen@gmail.com', '$1$Ekologia$SgatxCVcA5okqmClDpmqq.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, 'u', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO account (id, email, password, phone_number, addressstreet, addresszipcode, addresscity, country, avatar, description, roles, salt, discriminator, firstname, lastname, birthday, orgname, activity, type) VALUES (19, 'admin', '$1$Ekologia$l68O22wPMhX5M92MK71430', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, 'i', NULL, NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 174
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_id_seq', 21, true);


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

INSERT INTO page (id, url, javascript, html, css) VALUES (7, 'testimport', NULL, '<div style="text-align:center">
<span style="font-size:50px;color:#1f8e11">
  <b>
  <div style="display:block;text-align:center;margin-right:auto;margin-left:auto">
   <a href="https://sites.google.com/site/preparationconstituantecoop/accueil/logo+texte%20banniere.png" imageanchor="1"><img src="https://sites.google.com/site/preparationconstituantecoop/accueil/logo+texte%20banniere.png" height="160" width="400" border="0">
   </a>
  </div>
  </b>
 </span></div>
<br>
<div style="text-align:center">
 <span style="color:rgb(31,142,17)"><b><font size="2">anciennement Coopérative écologiste</font></b></span>
 <br>
 <br>
 <font size="5">Bienvenue sur le site</font>
</div>
<div style="text-align:center"><font size="5"><br></font></div>

<div style="text-align:center"><span><font size="4"><font face="arial, sans, sans-serif">coordonner, animer, construire et encourager les initiatives citoyennes de terrain qui protègent l’environnement et se réapproprient le pouvoir citoyen</font></font></span></div>
<br>
<br>
<br>
<table>
<tr>
<td>


<p><font style="font-family:georgia,serif;background-color:transparent" size="4">Comment </font><b style="font-size:10pt;font-family:georgia,serif;background-color:transparent"><font size="5" color="#38761d">mettre en synergie les acteurs</font></b><font style="background-color:transparent" size="4"><font face="georgia, serif"> cap</font><font face="arial, sans-serif">a</font><font face="georgia, serif">ble</font></font><font style="background-color:transparent" size="4" face="georgia, serif">s de répondre aux crises systémiques (financière et économique, sociale et démocratique, environnementale et climatique) ? Cette question est au coeur du projet</font><font style="background-color:transparent" size="4"><font face="georgia, serif">.</font></font></p>

<p><font face="georgia, serif"><font size="4">Ce projet est celui  </font><b><font size="5" color="#38761d">une association indépendante de toute 
organisation commerciale ou partidaire permettant le rassemblement 
trans-national des citoyen-nes et des personnes morales</font></b><font size="4"> <span style="background-color:transparent">qui contribuent </span><span style="background-color:transparent"> </span><span style="background-color:transparent">à l’émergence d’une autre société (voir charte)</span><span style="background-color:transparent">.</span></font></font></p>
<p><font face="georgia, serif"><font size="4">Un des objectif principaux est de coordonner, animer, construire, encourager et agir en faveur des initiatives citoyennes de terrain dont le but est de</font><b> <font size="5" color="#38761d">protéger l’environnement et les écosystèmes, ainsi que d’améliorer la qualité de vie des hommes en harmonie avec la nature</font><font size="4">.</font></b></font></p>
<font size="4" face="georgia, serif">
</font>
<p><font size="4" face="georgia, serif">Suite à la <a href="https://sites.google.com/site/preparationconstituantecoop/lancement-du-19-janvier">réunion de lancement</a>, l’assemblée constituante de l’association a eu lieu <a href="http://www.reporterre.net/spip.php?article4016">les 6 et 7 avril 2013</a>. Elle a réuni 70 personnes d''origine et de conviction différentes.
 Les statuts, la Charte et les objectifs ont été écrits de manière participative au cours des 
deux dernières années. L''association est enregistrée et opérationnelle.<br>
</font></p>
<p style="margin:0cm 0cm 0.0001pt;text-align:justify"><font size="4" face="georgia, serif">Nous tenons à remercier
chaleureusement les participant-e-s à la constituante, pour leur contribution active qui a permis
de rédiger collectivement la charte et les statuts de l''association.</font></p>
<font face="georgia, serif"><font size="4">

</font>
</font>
<p style="margin:0cm 0cm 0.0001pt;text-align:justify"><font size="4" face="georgia, serif">Cette réussite collective représente
une grande satisfaction pour tous ceux qui font vivre ce
réseau.</font></p>

<font face="georgia, serif"><font size="4">
</font>
</font>
<p><font size="4" face="georgia, serif">Ce site est provisoire. Un autre site est en cours de création pour remplacer celui-ci et apporter des outils pour gérer les groupes</font></p>
<p><font size="4" face="georgia, serif">Pour nous rejoindre, cliquer <u><b><span style="color:rgb(0,0,255)"><a href="https://sites.google.com/site/preparationconstituantecoop/pre-adhesion" target="_blank">ICI</a></span></b></u></font></p>
<p><font size="4" face="georgia, serif"><a href="https://www.facebook.com/pages/Constituante-de-la-coop%C3%A9rative-%C3%A9cologiste/274611082672069" target="_blank">Les Actualités sur Facebook</a></font></p>
<p><font size="4" face="georgia, serif"><a href="https://www.facebook.com/groups/constituante.cooperative.ecologiste/">Le groupe de discutions sur Facebook pour échanger</a></font></p>

<table>
<tbody>
<tr>
<td style="width:10%"> </td>
<td style="width:30%">
<div style="text-align:center"><br>
<div style="text-align:left"></div>
</div>
</td>
<td style="width:15%"> </td>



<td><br>
</td>
</tr>
</tbody>
</table>



</td>
<td>
<!--
<div style="width:100%">
<div style="display:table;margin:auto">
<div style="text-align:center;display:table-cell;margin-right:auto;margin-left:auto"><a href="https://sites.google.com/site/preparationconstituantecoop/lancement-du-19-janvier" imageanchor="1"><img alt="réunion de lalncement" src="https://lh3.googleusercontent.com/-_cofmt3FIVI/U0hj3a-5QwI/AAAAAAAAB5w/rETYWKlWOK4/s298/chronomogie%2520coop%25201.png" border="0"></a></div>
<div style="text-align:center;display:table-cell;margin-right:auto;margin-left:auto"><img src="https://lh4.googleusercontent.com/-ws0bPzly3dE/U0hj3bua6iI/AAAAAAAAB5s/bU8hWWF-h3c/s298/chronomogie%2520coop%25202.png" border="0"></div>
<div style="text-align:center;display:table-cell;margin-right:auto;margin-left:auto"><a href="https://sites.google.com/site/preparationconstituantecoop/resultat-vote-nom" imageanchor="1"><img alt="adoption du nom" src="https://lh6.googleusercontent.com/-wh-rSII78zE/U0hj3dWgHAI/AAAAAAAAB5o/kdEHVbsE6b8/s298/chronomogie%2520coop%25203.png" border="0"></a></div>
</div>
</div>
<br>
<br>
<br>
<br>-->
<div style="text-align:center"><br>
</div>

<div style="width:100%">
<div style="margin-left:20%;margin-right:20%">

<iframe frameborder="0" width="480" height="270" src="//www.dailymotion.com/embed/video/x1bn57n" allowfullscreen></iframe><br /><a href="http://www.dailymotion.com/video/x1bn57n_maquette-constituante-cooperative-ecologiste-6-7-avril-2013_webcam" target="_blank">maquette constituante coopérative écologiste 6...</a> <i>par <a href="http://www.dailymotion.com/constituanteecologiste" target="_blank">constituanteecologiste</a></i>

</div>
</div>
<div style="text-align:center"><br>
</div>
<a href="http://www.dailymotion.com/video/x1bn57n_maquette-constituante-cooperative-ecologiste-6-7-avril-2013_webcam" target="_blank">maquette constituante coopérative écologiste 6...</a> <i>par <a href="http://www.dailymotion.com/constituanteecologiste" target="_blank">constituanteecologiste</a></i>
<div style="text-align:center"><span style="font-family:georgia,serif"><font size="4">Une constituante qui s''est passée dans une ambiance à la fois studieuse et très participative</font></span><br>
</div>
<div><br>
<font size="4" face="georgia, serif">Les films qui ont été réalisés sur 
place par des caméramans
professionnels bénévoles sont encours de montage mais une maquette est 
disponible. Merci à Hugo le monteur et à tous ceux qui ont réalisé ces 
images.</font></div>



</td>
<tr>
</table>
', NULL);
INSERT INTO page (id, url, javascript, html, css) VALUES (1, 'home', NULL, '<div class="uk-grid" data-uk-grid-margin="">
	<div class="uk-width-1-1 uk-text-center">
		<img src="
https://lh4.googleusercontent.com/-dUQf94H_zmw/VLMhMFbf8-I/AAAAAAAAC9M/OipBy0et2Jk/w1000-h400-no/logo%2Btexte%2Bbanniere.png" style="height:200px"/>
		<h3 class="uk-heading-large">bienvenue sur le nouveau site</h3>
        <a href="./page/testimport">import page historique</a>
	
	</div>
</div>', NULL);
INSERT INTO page (id, url, javascript, html, css) VALUES (8, 'qui-sommes-nous', NULL, 'qui-sommes-nous', NULL);
INSERT INTO page (id, url, javascript, html, css) VALUES (9, 'communaute', NULL, 'communaute', NULL);
INSERT INTO page (id, url, javascript, html, css) VALUES (10, 'forum-ouvert', NULL, 'forum-ouvert', NULL);


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 170
-- Name: page_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('page_id_seq', 10, true);


--
-- TOC entry 2019 (class 0 OID 91310)
-- Dependencies: 173
-- Data for Name: security; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO security (id, url, diseable) VALUES (2, 'admin/*', NULL);


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 172
-- Name: security_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('security_id_seq', 2, true);


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


-- Completed on 2015-01-28 17:55:38 CET

--
-- PostgreSQL database dump complete
--

