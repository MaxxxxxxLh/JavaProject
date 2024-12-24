package com.isepA1.javaProject.service;

import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {

    private final ProjetRepository projetRepository;

    @Autowired
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
}
