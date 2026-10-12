package esprit.tn.tn_foyer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = {"idMaintenance", "vehicule"})
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idMaintenance;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;

    // Plusieurs maintenances appartiennent à un véhicule
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Vehicule vehicule;
}