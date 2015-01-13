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
    * Etendre la classe `coop.ekologia.service.mapper.Mapper`
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
    * L'appel aux jsp doit se faire grâce à la fonction `EkologiaServlet.forwardToJsp(String, HttpServletRequest, HttpServletResponse)` (cf. javadoc pour plus d'explication)
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


Script SQL
----------

Afin de faciliter la mise à jour des bases de données des différents membres de l'équipe, nous allons utiliser les conventions suivantes :
* Chaque modification de la base de données doit se trouver dans le dossier `script\evolutions`
* Le nom de ces fichiers est `<numero>.sql` où `<numero>` permet de connaître l'ordre d'exécution des scripts
* Lors d'une mise à jour du code et donc de la base de données, seules les nouveaux fichiers SQL seront à exécuter
* Si une erreur est remarquée dans un fichier SQL déjà commité, créer un nouveau fichier SQL corrigeant le problème, ne jamais modifier un fichier déjà commité
* Lors du `merge` avant un `commit` et un `push`, des conflits peuvent exister. Le renommage du fichier sql vers le numéro suivant du dernier existant corrige le problème
* Ne jamais récupérer un dump de la base de données, qui recréera l'ensemble des scripts de création de la base, sans pour autant corriger des erreurs existantes et pouvant amener à des conflits

Internationalisation
--------------------

Aussi appelé `i18n` car 18 lettres entre `i` et `n` (terme utilisé dans le code sur internet).

* Préparation:
    * Créer un fichier dans `EkologiaGUI/src/i18n` un fichier nommé `<module>.properties` où `<module>` est votre module. si ce sont des sous-modules, collez les noms (ex: `groupwiki` pour `group/wiki`). Ce fichier contient les traduction par défaut, en anglais.
    * Créer autant de propriétés de d'éléments à traduire.
    * Convention de nommage: `aaa.bbb.ccc` sans accent, en essayant de regrouper les noms comme des packages (ex: `registration.usertype.label`, cf. `jsp/user/registration.jsp` pour exemples).
    * Les traductions par langue sont dans les fichiers `<module>_<lang>.properties` (ex: `groupwiki_fr.properties`, `groupwiki_en.properties`).
* Utilisation:
    * Depuis une JSP:
        * Ajouter `<fmt:setLocale value="${ currentLanguage }" />` au début du fichier. `currentLanguage` est une variable fournie par la méthode `EkologiaServlet.forwardToJsp(String, HttpServletRequest, HttpServletResponse)`
        * Ajouter en dessous `<fmt:setBundle basename="i18n.<module>"/>` en remplaçant `<module>` par votre nom de module (cf. partie `Préparation`)
        * Utiliser `<fmt:message key="your.key" />` pour un affichage direct (en dehors de tout balise)
        * Utiliser `<fmt:message key="your.key" var="message" />`, puis `${ message }` pour des textes dans les balises (cf. `jsp/user/registration.jsp` pour exemples)
    * Depuis java:
        * Injecter le service `I18nService` (`EkologiaGUI` seulement)
        * Appeler la méthode `translate(String, String)`.
            * Le premier paramètre est la langue (obtenue via `EkologiaServlet.getCurrentLanguage(HttpServletRequest)`).
            * Le second est la clef, préfixée par votere module (ex: `user.registration.email.empty` pour la clef `registration.email.empty` du module `user`). Cette convention est obligatoire pour que ça marche.

Gestion des erreurs dans les formulaires
----------------------------------------

Vous trouverez la classe `FormErrors` qui permettra de gérer les erreurs et les traductions associées. Voici comment l'utiliser :
* Dans java:
    * Injecter le service `FormErrors`
    * Avant de l'utiliser, effectuer un `FormErrors.setLanguage(getCurrentLanguage(request))`. Lorsque nous aurons trouver un moyen de récupérer directement depuis la requête, cette opération ne sera plus nécessaire.
    * Pour chaque erreur, ajouter une erreur via `FormErrors.addError(String, String)`
        * Le premier paramètre est le nom de votre champ
        * Le second, la clef qui sera utilisée par le service `I18nService` (cf. partie `Internationalisation`)
    * Chaque clef peut avoir plusieurs erreurs
    * Pour savoir s'il existe des erreurs, utiliser `FormErrors.isEmpty()`
* Dans JSP (on suppose que la liste de erreurs se nomme `errors`):
    * ```
        <c:if test="${ errors != null && errors.get('myfield') != null }">
            <c:forEach var="error" items="${ errors.get('myfield') }">
                <c:out value="${ error }" />
            </c:forEach>
        </c:if>
      ```
* Cf. `registration.jsp` et `RegistrationServlet`

Utilisation de constantes
-------------------------

Afin d'éviter les erreurs de copies, ainsi que de ne pas casser un existant, il est conseillé d'exporter l'ensemble des constantes dans une classe externe.
Ces cosntants seront dans le packages `coop.ekologia.presentation.constants`.
Nous utiliserons une interface par module (et sous module) dont le nom est `ModuleConstants` ou `SousModuleConstants`.
Toutes les constantes sont des objets ou primitifs (`int`, `long`, etc.), déclarées sans modificateur de visibilité (`String AAA = "aaa"`et non `public static final String AAA = "aaa"`).
Le nom des constantes sont en majuscules, séparées par des underscores (ex: `AAA_BBB_CCC`).
Préfixer les constantes par leur type (ex: `PARAMETER_*` ou `ATTRIBUTE_*`).

Divers
------

Eviter d'avoir des warning (ex: variable inutilisée)