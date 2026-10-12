package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}