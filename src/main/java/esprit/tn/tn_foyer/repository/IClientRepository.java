package esprit.tn.tn_foyer.repository;

import esprit.tn.tn_foyer.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}