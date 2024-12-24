package com.isepA1.javaProject.model.postgres;

import com.isepA1.javaProject.model.dto.EmployeDto;
import com.isepA1.javaProject.model.dto.TacheDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
public class Projet {
    @Id
    private String nom;
    private long id;
    private List<Tache> listeTaches;
    private Date dateLimite;
    private List<Employe> membres;

    public Projet(long id, String nom, Date dateLimite) {
        this.id = id;
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

    public void setNom(String titre) {
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
