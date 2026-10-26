# API Notes — Atelier 5

## A. Tableau des endpoints

| Contrôleur | Verbe HTTP | Route | Service utilisé | Succès attendu | Erreurs possibles |
|---|---|---|---|---|---|
| ContratController | GET | `/api/contrats` | `IContratService.findAll()` | 200 OK | — |
| ContratController | GET | `/api/contrats/{id}` | `IContratService.findById(id)` | 200 OK | 404 Not Found |
| ContratController | POST | `/api/contrats` | `IContratService.create()` | 201 Created | 400 Bad Request |
| ContratController | PUT | `/api/contrats/{id}` | `IContratService.update()` | 200 OK | 404 / 400 |
| ContratController | DELETE | `/api/contrats/{id}` | `IContratService.deleteById()` | 204 No Content | 404 Not Found |
| PaiementController | GET | `/api/paiements` | `IPaiementService.findAll()` | 200 OK | — |
| PaiementController | GET | `/api/paiements/{id}` | `IPaiementService.findById(id)` | 200 OK | 404 Not Found |
| AgenceController | GET | `/api/agences` | `IAgenceService.findAll()` | 200 OK | — |
| AgenceController | GET | `/api/agences/{id}` | `IAgenceService.findById(id)` | 200 OK | 404 Not Found |
| AgenceController | POST | `/api/agences` | `IAgenceService.create()` | 201 Created | 400 Bad Request |
| AgenceController | PUT | `/api/agences/{id}` | `IAgenceService.update()` | 200 OK | 404 / 400 |
| AgenceController | DELETE | `/api/agences/{id}` | `IAgenceService.deleteById()` | 204 No Content | 404 Not Found |
| EmployeController | CRUD | `/api/employes` | `IEmployeService` | 200 / 201 / 204 | 400 / 404 |
| VehiculeController | CRUD | `/api/vehicules` | `IVehiculeService` | 200 / 201 / 204 | 400 / 404 |
| EquipementController | CRUD | `/api/equipements` | `IEquipementService` | 200 / 201 / 204 | 400 / 404 |
| ClientController | CRUD | `/api/clients` | `IClientService` | 200 / 201 / 204 | 400 / 404 |
| ReservationController | CRUD | `/api/reservations` | `IReservationService` | 200 / 201 / 204 | 400 / 404 |
| MaintenanceController | CRUD | `/api/maintenances` | `IMaintenanceService` | 200 / 201 / 204 | 400 / 404 |

Le chemin global `/api` est défini dans `application.properties` avec :

```properties
server.servlet.context-path=/api
```

Les contrôleurs utilisent donc des mappings comme `@RequestMapping("/contrats")` sans répéter `/api`.

---

## B. Gestion des associations Jackson

Les entités JPA contiennent plusieurs associations bidirectionnelles. Lorsqu'elles sont exposées directement en JSON, elles peuvent provoquer des boucles de sérialisation.

Pour l'association entre `Contrat` et `Paiement`, les annotations suivantes peuvent être utilisées :

Dans `Contrat` :

```java
@JsonManagedReference("contrat-paiements")
private List<Paiement> paiements;
```

Dans `Paiement` :

```java
@JsonBackReference("contrat-paiements")
private Contrat contrat;
```

`@JsonManagedReference` indique le côté principal qui peut être sérialisé.

`@JsonBackReference` évite de sérialiser à nouveau la référence vers l'objet parent et permet donc d'éviter une récursion infinie.

`@JsonIgnore` permet quant à lui d'ignorer complètement un attribut pendant la sérialisation JSON.

La différence principale est donc que `@JsonBackReference` est utilisée dans une relation parent-enfant, tandis que `@JsonIgnore` masque simplement un champ.

---

## C. Postman et Swagger

Postman est utilisé pour tester manuellement les endpoints REST en choisissant la méthode HTTP, l'URL et éventuellement un body JSON.

Exemple testé :

```text
GET http://localhost:8081/api/contrats
```

Résultat obtenu :

```text
200 OK
```

Exemple de création :

```text
POST http://localhost:8081/api/contrats
```

Body :

```json
{
  "dateSignature": "2026-11-02",
  "montantTotal": 480.00,
  "valide": true
}
```

Résultat obtenu :

```text
201 Created
```

Swagger permet de documenter et de tester l'API via une interface Web générée à partir du code Spring.

Postman sert donc principalement aux tests des requêtes HTTP, tandis que Swagger sert principalement à documenter et explorer l'API.

---

## D. Analyse SonarQube for IDE

| Anomalie SonarQube for IDE | Règle / Explication | Correction apportée |
|---|---|---|
| Entité persistante utilisée directement dans `ContratController` | SonarQube indique : `Replace this persistent entity with a simple POJO or DTO object.` Le contrôleur REST est directement lié à une entité JPA. | Cette anomalie est connue. Dans cet atelier, les entités sont volontairement exposées directement. Une séparation avec DTO pourra être réalisée dans un atelier ultérieur. |
| Entité persistante utilisée dans une autre méthode de `ContratController` | Même problème de couplage entre la couche Web et la couche de persistance. | Prévoir ultérieurement des DTO pour les données reçues et retournées. |
| Import inutilisé dans `IAgenceRepository` | Règle `java:S1128` : un import déclaré mais inutilisé doit être supprimé. | Suppression de l'import inutilisé. |

---

## E. Réponses aux questions

### 1. Quel composant reçoit toutes les requêtes HTTP et trouve la méthode du contrôleur ?

Dans Spring MVC, le `DispatcherServlet` reçoit les requêtes HTTP et les redirige vers la méthode correspondante du contrôleur.

### 2. Pourquoi `ContratController` dépend-il de `IContratService` et non du repository ?

Le contrôleur dépend de l'interface du service afin de respecter la séparation des responsabilités.

Le contrôleur gère les requêtes HTTP, le service contient la logique métier et le repository gère l'accès à la base de données.

Cela réduit également le couplage entre les couches.

### 3. Pourquoi POST retourne 201, DELETE retourne 204 et une ressource absente retourne 404 ?

`201 Created` indique qu'une nouvelle ressource a été créée.

`204 No Content` indique que la suppression a réussi et qu'aucun contenu n'est retourné.

`404 Not Found` indique qu'une ressource demandée n'existe pas.

Une ressource absente ne doit pas provoquer une erreur `500`, car il ne s'agit pas d'une erreur interne du serveur.

### 4. Pourquoi `PaiementController` possède-t-il uniquement des GET ?

Dans cet atelier, `PaiementController` est volontairement en lecture seule.

Il possède donc uniquement :

```text
GET /api/paiements
GET /api/paiements/{id}
```

Un appel :

```text
POST /api/paiements
```

retourne :

```text
405 Method Not Allowed
```

Ce comportement a été vérifié avec Postman.

### 5. Quels sont les risques d'exposer directement les entités JPA dans l'API ?

Exposer directement les entités JPA peut :

- créer un couplage fort entre la base de données et l'API ;
- exposer des attributs qui ne devraient pas être visibles ;
- provoquer des problèmes de sérialisation ;
- provoquer des boucles avec les associations bidirectionnelles ;
- rendre les évolutions de l'API plus difficiles.

L'utilisation future de DTO permet de mieux séparer la couche Web de la couche de persistance.

Dans `Contrat.paiements`, `@JsonManagedReference` peut être utilisée car les paiements font partie de la représentation du contrat.

Pour certaines collections inverses comme `Agence.vehicules`, `@JsonIgnore` peut être utilisé lorsque l'on ne souhaite simplement pas sérialiser cette collection.

---

# Tests Postman réalisés

Les tests suivants ont été effectués :

| Test | Résultat |
|---|---|
| `GET /api/contrats` | 200 OK |
| `GET /api/contrats/9999` | 404 Not Found |
| `POST /api/contrats` valide | 201 Created |
| `POST /api/contrats` avec un identifiant | 400 Bad Request |
| `PUT /api/contrats/4` | 200 OK |
| `DELETE /api/contrats/4` | 204 No Content |
| `GET /api/contrats/4` après suppression | 404 Not Found |
| `GET /api/paiements` | 200 OK |
| `GET /api/paiements/9999` | 404 Not Found |
| `POST /api/paiements` | 405 Method Not Allowed |
| `GET /api/agences` | 200 OK |
| `POST /api/agences` | 201 Created |
| `GET /api/agences/9999` | 404 Not Found |
| `POST /api/agences` avec nom vide | 400 Bad Request |