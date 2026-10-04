package esprit.tn.tn_foyer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicule")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// toString() sans afficher les noms des attributs
@ToString(includeFieldNames = false)

// equals() et hashCode() sans prendre l'id en compte
@EqualsAndHashCode(exclude = "idVehicule")

public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Aucun setter pour l'id
    @Setter(AccessLevel.NONE)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutVehicule statut;
}