# TpArchitectureService_INSA

Ce projet d'architecture de services repose sur l'utilisation de l'Enterprise Service Bus (ESB) de Talend pour developper trois flux distincts :

# Flux 1 - Intégration de fichiers CSV dans la base de données
L'objectif principal du premier flux est de traiter un ou plusieurs fichiers CSV correctement structurés. Ces fichiers seront déposés dans un répertoire spécifique, et le flux s'occupera de l'intégration directe des utilisateurs extraits de ces fichiers dans la base de données. Il s'agit essentiellement d'une opération d'intégration de données entre un système de fichiers et une base de données.

# Flux 2 - API REST pour l'insertion d'utilisateurs
Le deuxième flux expose une API REST gérée par la méthode POST. Cette API permet de recevoir des utilisateurs au format JSON dans le corps de la requête. Les données reçues sont ensuite intégrées directement dans la base de données des utilisateurs, fournissant ainsi une méthode simple et flexible pour ajouter de nouveaux utilisateurs à la base.

# Flux 3 - Service Web SOAP pour l'interrogation de la base de données
Le troisième connecteur met en œuvre un service Web de type SOAP. Ce service permet d'interroger la base de données des utilisateurs et de récupérer à la demande un utilisateur en fonction de son nom. Cette fonctionnalité offre une approche efficace pour obtenir des informations spécifiques sur les utilisateurs stockés dans la base de données.
