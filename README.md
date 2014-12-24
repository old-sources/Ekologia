Ekologia
========

Commande de Ekologia pour IMIE Nantes. réalisé les stagiaires 4IM03.

# déploiement sur Wyldfly
## prés requis
- gradle installé
- wyldfly 8.1 installé
- une base de donnée initialisé avec les scripts d'initialisation executé
- une datasource jta déclarée sur le serveur wyldfy sur l'adresse jndi : java:/jdbc/Ekologia

## build
- à la racine lancer "gradle assemble"

## déploiement
- démarrer wildfly (sudo service wildfly start)
- dans l'interface d'administration de wildly, onglet runtime, déployer l'EAR généré dans EkologiaEAR/buid

## éxécution
- dans un navigateur saisissez l'url ip:port/Ekologia/User

# developpment sur Eclipse
## prés requis
- eclipse luna
- plugin JBoss
- plugin gradlecontenu 

## intégration
La procedure n'est pas encore bien établie. Le plugin eclipse pour gradle ne produit pas de structure et de contenu de fichier satisfaisante pour eclipse luna. Cela va sans doute s'arranger avec les mise à jour de gradle et de son plugion eclipse et eclipse-wtp. En attendant, la methode qui fonctionne le mieux est d'installer un plugin gradle sur eclipse pour importer les 4 projet gradle à partir des sources qui contiennent les fichiers build.gradle suffisants.
