package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    private final TacheService tacheService;

    @Autowired
    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @GetMapping("/")
    public List<Tache> getAllTaches() {
        return tacheService.getAllTaches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable long id) {
        Optional<Tache> tache = tacheService.getTacheById(id);
        return tache.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        Tache createdTache = tacheService.createTache(tache);
        return new ResponseEntity<>(createdTache, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable long id, @RequestBody Tache updatedTache) {
        Optional<Tache> existingTache = tacheService.getTacheById(id);
        if (existingTache.isPresent()) {
            Tache tache = tacheService.updateTache(id, updatedTache);
            return new ResponseEntity<>(tache, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable long id) {
        Optional<Tache> tache = tacheService.getTacheById(id);
        if (tache.isPresent()) {
            tacheService.deleteTache(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
