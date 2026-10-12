# Atelier 4 — IoC, Injection de dépendances et couche Service

## A. Comparaison des modes d'injection

| Critère | Injection par constructeur | Injection par attribut |
|---|---|---|
| Champ `final` possible | Oui | Non |
| Dépendance visible dans le constructeur | Oui | Non |
| Objet toujours correctement initialisé | Oui | Non |
| Utilisable avec `new` hors conteneur Spring | Oui, si on fournit les dépendances | Risque de dépendance `null` |
| Qualité / maintenabilité | Recommandée | Déconseillée |
| SonarQube | Non signalée comme problème | Peut signaler la règle S6813 |

Dans notre projet, nous utilisons l'injection par constructeur avec
`@RequiredArgsConstructor`. Les repositories sont déclarés avec `final`.
Spring injecte automatiquement les dépendances lors de la création du service.

Exemple :

```java
@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
}   