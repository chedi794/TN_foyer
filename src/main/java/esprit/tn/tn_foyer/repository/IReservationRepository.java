package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}