package com.isepA1.javaProject.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjetDto {
    private long id;

    @NotNull
    @Size(min = 1, message = "Le nom du projet ne peut pas être vide.")
    private String nom;

    @NotNull(message = "La date limite ne peut pas être nulle.")
    @Future(message = "La date limite doit être dans le futur.")
    private Date dateLimite;
    private List<TacheDto> listeTaches;
    private List<EmployeDto> membres;

    public ProjetDto(long id, String nom, Date dateLimite) {
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<TacheDto> getListeTaches() {
        return this.listeTaches;
    }

    public void setListeTaches(List<TacheDto> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public Date getDateLimite() {
        return this.dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public List<EmployeDto> getMembres() {
        return this.membres;
    }

    public void setMembres(List<EmployeDto> membres) {
        this.membres = membres;
    }
}
