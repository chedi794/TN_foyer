package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}