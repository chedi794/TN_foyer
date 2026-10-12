package esprit.tn.tn_foyer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode(exclude = {
        "idReservation",
        "client",
        "vehicule",
        "contrat"
})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    // Plusieurs réservations appartiennent à un client
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Client client;

    // Plusieurs réservations peuvent concerner un véhicule
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Vehicule vehicule;

    // Une réservation possède un contrat
    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private Contrat contrat;
}