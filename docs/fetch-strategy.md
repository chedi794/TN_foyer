# Fetch Strategy

| Association | Fetch | Cascade | Justification |
|---|---|---|---|
| Contrat → Paiement | LAZY | ALL + orphanRemoval | Un paiement n'existe que rattaché à son contrat ; supprimer le contrat supprime ses paiements. |
| Agence → Vehicule | LAZY | Aucune | Un véhicule survit à la suppression de son agence. |
| Agence → Employe | LAZY | Aucune | Aucune cascade n'est demandée. |
| Vehicule ↔ Equipement | LAZY | Aucune | Les équipements sont partagés ; aucune suppression en cascade. |
| Client → Reservation | LAZY | PERSIST | La création d'un client peut propager la persistance de ses réservations. |
| Reservation → Vehicule | LAZY | Aucune | Le véhicule existe indépendamment de la réservation. |
| Reservation ↔ Contrat | LAZY | ALL côté Reservation | Le contrat dépend de la réservation. |
| Vehicule → Maintenance | LAZY | PERSIST | La création du véhicule peut propager la persistance de ses maintenances. |