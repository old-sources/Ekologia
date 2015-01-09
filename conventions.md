Conventions pour le projet Ekologia
===================================

Les conventions présentées ci-dessous permettent d'organiser le projet, ainsi que de pouvoir facilement retrouver et différencier les composants du projet.

Nommage, packages et héritages/implémentations
----------------------------------------------

* Les noms des classes et variables respectent la convention oracle
* Les noms des classes et variables doivent être en anglais
* Tous les DTO doivent avoir le suffixe `DTO` (ex: `UserDTO`, `pageDTO`)
* Tous les mapper, permettant un mapping entité/DTO devront :
    * Etre dans le package `coop.ekologia.service.mapper`
    * Etendre l'interface `coop.ekologia.service.mapper.Mapper`
    * Posséder le suffixe `Mapper` (ex: `UserMapper`, `PageMapper`)
* Tous les services doivent :
    * Avoir le suffixe `Service`
    * Ne doit pas renvoyer d'entité, toujours des DTO
    * Etre dans un sous-package de `coop.ekologia.service`
    * Hériter d'une interface dont le nom est le nom du service, suffixé de `Interface` (ex: `UserServiceInterface` pour le service `UserService`)
    * Eviter d'avoir un nom
* Toutes les servlets doivent :
    * Etendre la classe `coop.ekologia.presentation.controller.EkologiaServlet`
    * Avoir le suffixe `Servlet` (ex: `LoginServlet`, `PageServlet`)
    * Posséder une méthode `public static final String routing(HttpServletRequest, <params>)` permettant de générer une url vers cette servlet selon les arguments passés en paramètre (comme les paramètres sont variables, nous n'utilisons pas d'interface)
    * Etre dans un sous-package de `coop.ekologia.presentation.controller`
* Tous les filtres d'accès aux servlet doivent :
    * Avoir le suffixe `Filter`
    * Etre dans le package ou un sous-package de `coop.ekologia.presentation.security`
* Les sessions seront gérées grâce à des classes (permet un typage fort et une maintenance plus facile), ces classes doivent :
    * Avoir le suffixe `Session` (ex: `LoginSession`)
    * Implémenter `Serializable` (sinon, cela provoque une erreur lors du déploiement)
    * Etre dans le package `coop.ekologia.presentation.session`
* Diviser les classes dans différents packages et sous-packages selon les modules et sous-modules (ex: `user`, `group`, `group.wiki`) et ce, pour chaque projet


Documentation et commentaires
-----------------------------

* Utiliser les conventions de la javadoc (`/** doc */`)
* Ne pas mettre de documentation, ni de commentaire vide ou inutile
* Mettre la documentation en priorité dans les interface et non dans les classes concrètes
* Pour des exemple, utiliser la syntaxe `{@code mon code}`
* Pour des liens entre classes, méthodes et champs, utiliser la syntaxe `{@link MaClasse#maMethodOuMonChamp}`


Divers
------

Eviter d'avoir des warning (ex: variable inutilisée)