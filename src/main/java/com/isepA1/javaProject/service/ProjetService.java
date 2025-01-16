package com.isepA1.javaProject.service;

import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {

    @Autowired
    private final ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Optional<Projet> getProjetById(long id) {
        return projetRepository.findById(id);
    }

    public Projet createProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    public void addMembreToProjet(Projet projet, Employe employe) {
        projet.getMembres().add(employe);
        projetRepository.save(projet);
    }

    public List<String> getProjectNamesByEmployeId(long employeId) {
        List<Projet> projets = projetRepository.findAll();
        List<String> projectNames = new ArrayList<>();
        for (Projet projet : projets) {
            if (projet.getMembres().stream().anyMatch(e -> e.getId() == employeId)) {
                projectNames.add(projet.getNom());
            }
        }
        return projectNames;
    }

    public Projet getProjetByNom(String nom) {
        return projetRepository.findAll()
                .stream()
                .filter(projet -> projet.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    public void addTacheToProjet(Projet projet, Tache tache) {
        projet.getListeTaches().add(tache);
        projetRepository.save(projet);

    }
}
