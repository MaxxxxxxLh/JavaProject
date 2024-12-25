package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.service.ProjetService;
import com.isepA1.javaProject.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    private final ProjetService projetService;
    private final EmployeService employeService;

    @Autowired
    public ProjetController(ProjetService projetService, EmployeService employeService) {
        this.projetService = projetService;
        this.employeService = employeService;
    }
    @GetMapping("/")
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable long id) {
        Optional<Projet> projet = projetService.getProjetById(id);
        return projet.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet createdProjet = projetService.createProjet(projet);
        return new ResponseEntity<>(createdProjet, HttpStatus.CREATED);
    }

    @PostMapping("/{projetId}/membres/{employeId}")
    public ResponseEntity<Projet> addMembreToProjet(
            @PathVariable long projetId,
            @PathVariable long employeId) {

        Optional<Projet> projet = projetService.getProjetById(projetId);
        Optional<Employe> employe = employeService.getEmployeById(employeId);

        if (projet.isPresent() && employe.isPresent()) {
            projetService.addMembreToProjet(projet.get(), employe.get());
            return new ResponseEntity<>(projet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{projetId}/membres")
    public ResponseEntity<List<Employe>> getMembresOfProjet(@PathVariable long projetId) {
        Optional<Projet> projet = projetService.getProjetById(projetId);
        if (projet.isPresent()) {
            return new ResponseEntity<>(projet.get().getMembres(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
