# Atelier 3 — Spring Data JPA et couche Repository

## 1. Choix des interfaces Repository

| Interface | Étend | Justification |
|---|---|---|
| IAgenceRepository | JpaRepository<Agence, Long> | Fournit le CRUD complet ainsi que les fonctionnalités JPA. |
| IEmployeRepository | JpaRepository<Employe, Long> | Permet les opérations CRUD, les listes, le tri et la pagination. |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | Adapté à une application JPA complète et permet également l'utilisation de flush. |
| IEquipementRepository | JpaRepository<Equipement, Long> | Fournit toutes les opérations nécessaires pour la persistance. |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet avec findAll qui retourne directement une List. |
| IReservationRepository | JpaRepository<Reservation, Long> | Permet de gérer les opérations CRUD et les fonctionnalités JPA. |
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List et saveAndFlush est disponible. |
| IPaiementRepository | JpaRepository<Paiement, Long> | Permet principalement la lecture des paiements tout en conservant les fonctionnalités JPA. |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | Fournit les méthodes CRUD ainsi que les opérations spécifiques à JPA. |

JpaRepository a été retenu car il regroupe les fonctionnalités de CRUD,
les listes, le tri, la pagination ainsi que des méthodes spécifiques à JPA
comme flush(), saveAndFlush() et getReferenceById().

## 2. Rappel sur les interfaces Spring Data JPA

### Repository

Repository est une interface marqueur.
Elle ne contient aucune méthode par défaut.

Elle permet de définir uniquement les méthodes que l'on souhaite exposer.

### CrudRepository

CrudRepository fournit les opérations CRUD de base :

- save()
- findById()
- findAll()
- existsById()
- count()
- deleteById()

### ListCrudRepository

ListCrudRepository fournit les mêmes opérations que CrudRepository,
mais les méthodes retournant plusieurs entités utilisent directement List.

### PagingAndSortingRepository

PagingAndSortingRepository permet d'utiliser le tri et la pagination.

### JpaRepository

JpaRepository regroupe les fonctionnalités précédentes et ajoute des méthodes
propres à JPA comme :

- flush()
- saveAndFlush()
- saveAllAndFlush()
- getReferenceById()
- deleteAllInBatch()
- deleteAllByIdInBatch()


## 3. Comportement des opérations CRUD

### save()

Lorsque l'identifiant de l'entité est null, l'entité est considérée comme nouvelle.

Hibernate effectue alors un persist et génère une requête INSERT.

Lorsque l'identifiant est déjà renseigné, Hibernate utilise merge.
Un SELECT peut être exécuté pour charger l'entité existante,
puis un UPDATE est effectué si les valeurs ont changé.

Il est préférable d'utiliser l'objet retourné par save().

### findById()

findById() retourne un Optional<T>.

Cela permet de gérer explicitement le cas où l'entité n'existe pas.

### deleteById()

deleteById() supprime une entité à partir de son identifiant.

Les suppressions classiques passent par le contexte de persistance,
ce qui permet d'appliquer les cascades et orphanRemoval.


## 4. Attention aux suppressions en lot

Les méthodes :

- deleteAllInBatch()
- deleteAllByIdInBatch()

effectuent directement une suppression en lot dans la base de données.

Elles contournent le contexte de persistance.

Par conséquent, les cascades et orphanRemoval ne sont pas appliqués.

Dans notre projet, il faut donc éviter ces méthodes pour supprimer un Contrat
si l'on souhaite également supprimer correctement ses Paiements associés.


## 5. Analyse SonarQube for IDE

| Anomalie SonarQube for IDE | Règle / Explication | Correction apportée |
|---|---|---|
| Entité persistante utilisée directement dans `ContratController` | SonarQube indique : `Replace this persistent entity with a simple POJO or DTO object.` Une entité JPA ne devrait pas être utilisée directement comme objet exposé par la couche REST. | Prévoir l'utilisation d'un DTO ou POJO dédié afin de séparer la couche Web de la couche de persistance. |
| Entité persistante utilisée directement dans une deuxième méthode de `ContratController` | Même anomalie détectée à un autre emplacement du contrôleur. Le contrôleur est directement couplé à l'entité persistante `Contrat`. | Prévoir l'utilisation d'un DTO pour les données reçues et retournées par le contrôleur. |
| Import inutilisé `java.util.ArrayList` dans `IAgenceRepository` | Règle `java:S1128` : un import déclaré mais non utilisé doit être supprimé. | Suppression de l'import `java.util.ArrayList`. |