package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}