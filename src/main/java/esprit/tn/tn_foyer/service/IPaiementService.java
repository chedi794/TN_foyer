package esprit.tn.tn_foyer.service;

import esprit.tn.tn_foyer.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    Paiement findById(Long id);

    List<Paiement> findAll();
}