package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}