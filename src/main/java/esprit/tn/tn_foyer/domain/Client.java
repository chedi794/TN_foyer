package esprit.tn.tn_foyer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = {"idClient", "reservations"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idClient;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String numPermis;

    private LocalDate dateInscription;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();
}