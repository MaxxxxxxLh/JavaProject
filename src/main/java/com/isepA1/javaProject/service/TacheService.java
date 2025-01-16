package com.isepA1.javaProject.service;

import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.model.enums.Etat;
import com.isepA1.javaProject.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TacheService {
    @Autowired
    private final TacheRepository tacheRepository;

    public TacheService(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    public Optional<Tache> getTacheById(long id) {
        return tacheRepository.findById(id);
    }

    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    public Tache updateTache(long id, Tache updatedTache) {
        return tacheRepository.findById(id)
                .map(existingTache -> {
                    existingTache.setNom(updatedTache.getNom());
                    existingTache.setDateLimite(updatedTache.getDateLimite());
                    existingTache.setPriorite(updatedTache.getPriorite());
                    existingTache.setCategorie(updatedTache.getCategorie());
                    existingTache.setCommentaires(updatedTache.getCommentaires());
                    existingTache.setEtat(updatedTache.getEtat());
                    existingTache.setProjet(updatedTache.getProjet());
                    return tacheRepository.save(existingTache);
                })
                .orElseThrow(() -> new IllegalArgumentException("Tâche non trouvée avec l'ID : " + id));
    }

    public void deleteTache(long id) {
        tacheRepository.deleteById(id);
    }

    public List<Tache> getTachesByEtat(Etat etat) {
        return tacheRepository.findByEtat(etat);
    }

    public List<Tache> findTacheByDateLimite(Date dateLimite) {
        return tacheRepository.findByDateLimite(dateLimite);
    }
    public Optional<Tache> findTacheById(long id){return tacheRepository.findById(id);}
}
