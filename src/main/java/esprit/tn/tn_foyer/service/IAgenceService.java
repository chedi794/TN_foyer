package esprit.tn.tn_foyer.service;

import esprit.tn.tn_foyer.domain.Agence;

import java.util.List;

public interface IAgenceService {

    Agence create(Agence agence);

    Agence findById(Long id);

    List<Agence> findAll();

    Agence update(Long id, Agence agence);

    void deleteById(Long id);
}