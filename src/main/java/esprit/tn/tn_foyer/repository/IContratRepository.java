package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContratRepository extends JpaRepository<Contrat, Long> {
}