package com.isepA1.javaProject.model.postgres;

import com.isepA1.javaProject.model.dto.ProjetDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employe {
    @Id
    private String nom;
    private String prenom;
    private String email;
    private long id;
    private List<Projet> historiqueProjets;
    public Employe(String nom, String prenom, String email, long id){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.id = id;
        this.historiqueProjets = new ArrayList<>();
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}

    public List<Projet> getHistoriqueProjets() {
        return this.historiqueProjets;
    }

    public void setHistoriqueProjets(List<Projet> historiqueProjets) {
        this.historiqueProjets = historiqueProjets;
    }
}
