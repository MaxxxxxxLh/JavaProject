package com.isepA1.javaProject.model.postgres;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nom;

    @OneToMany(mappedBy = "projet")
    private List<Tache> listeTaches;
    private Date dateLimite;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="employe_projet",
            joinColumns = @JoinColumn(name="projet_id"),
            inverseJoinColumns = @JoinColumn(name="employe_id")
    )
    private List<Employe> membres;
    public Projet(){}

    public Projet(String nom, Date dateLimite) {
        this.nom = nom;
        this.dateLimite = dateLimite;
        this.listeTaches = new ArrayList<>();
        this.membres = new ArrayList<>();
    }
    // Getters et setters
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

    public List<Tache> getListeTaches() {
        return this.listeTaches;
    }

    public void setListeTaches(List<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public Date getDateLimite() {
        return this.dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public List<Employe> getMembres() {
        return this.membres;
    }

    public void setMembres(List<Employe> membres) {
        this.membres = membres;
    }
}
