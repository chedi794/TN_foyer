package esprit.tn.tn_foyer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = "idAgence")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;
}