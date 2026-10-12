package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {
}