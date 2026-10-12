package esprit.tn.tn_foyer.service;

import esprit.tn.tn_foyer.domain.Paiement;
import esprit.tn.tn_foyer.exception.ResourceNotFoundException;
import esprit.tn.tn_foyer.repository.IPaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(Long id) {

        return paiementRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Paiement", id)
                );
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
}