package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContratRepository extends JpaRepository<Contrat, Long> {
}