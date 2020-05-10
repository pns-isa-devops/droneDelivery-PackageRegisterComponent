# j2eSubmodules-PackageRegisterComponent

# Vue d'ensemble
 Cet composant fait partie du projet `projet-isa-devops-20-team-d-20 ` nommé **`Drone Delivery`**. Il représente toute la partie de **`gestion d'enregisterment`** d'un **`colis`** du système.
 Il embarque avec lui : 
 * _Java Persistence API_ (JPA, OpenJPA) pour supporter la peristence des objets
 * _HyperSQL Database_ (HSQLDB) pour stocker les données
 * _Arquillian_, un frameworkde test pour les sytèmes J2e
 * _Maven_ embarquer toute ces technologie ensemble _"plus facilement"_.

## Informations sur le dépôt
  * La branche `master` ( branche par défaut ) repésente la dernière version de `PackageRegisterComponent`.
  
 
  ## Code information
*  Le code utilise `JPA Persistence` pour appliquer la persistence à `l'enregistrement` du colis ou de la `recherche` de ce dernier ou `avoir la liste des colis` dans la base de données.
* Ce sont les principales fonctionnalités de ce composant; Chaque foncion fait appel au `EntityManager` par le biais de l'annotation **`@PersitenceContexte`**.
Ce dernier recherche ou ajoute dans la base de données le colis donné selon la définition des attributs de la classe `Pacakge` ou recherche toutes les instance de ce type dans la base de données.
* Il utlise deux bases de données `HyperSQL Database (HSQLDB)`. Une pour les **`tests`** et une pour la **`production`**.
*  Le composant est  `@Stateless` donc ne conserve aucune valeur d'une action à une autre
* `PackageRegister` expose deux contrats d'utilisation ( les interfaces ) qui sont `PackageFinder` et `PackageRegistration`, on y retrouve des **exceptions**
pour certaines situations comme un colis `non trouvé` ou `déjà enregistré`

  ## Perspectives
  - [ ] Lancer Sonar dans le projet  
