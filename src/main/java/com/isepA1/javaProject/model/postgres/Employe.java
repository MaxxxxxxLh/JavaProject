package com.isepA1.javaProject.model.postgres;

import com.isepA1.javaProject.model.dto.ProjetDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "membres")
    private List<Projet> historiqueProjets;
    public Employe(String nom, String prenom, String email, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Projet> getHistoriqueProjets() {
        return this.historiqueProjets;
    }

    public void setHistoriqueProjets(List<Projet> historiqueProjets) {
        this.historiqueProjets = historiqueProjets;
    }
}
