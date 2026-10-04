package esprit.tn.tn_foyer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = "idEquipement")
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idEquipement;

    private String libelle;
}