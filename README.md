Ekologia
========

Commande de Ekologia pour IMIE Nantes. réalisé les stagiaires 4IM03.


========


# Lancer le produit sur Wyldfly
## prés requis
- gradle installé
- wyldfly 8.1 installé
- une base de donnée initialisé avec les scripts d'initialisation executé
- une datasource jta déclarée sur le serveur wyldfy sur l'adresse jndi : java:/jdbc/Ekologia 
## build
- à la racine lancer "gradle assemble"
## déploiement
- dans l'interface d'administration de wyldly, onglet runtime, déployer l'EAR généré dans EkologiaEAR/buid

