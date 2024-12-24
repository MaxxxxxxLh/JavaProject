package com.isepA1.javaProject.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class EmployeDto {
    private long id;
    @NotNull
    @Size(min = 1, message = "Le nom de l'employé ne peut pas être vide.")
    private String nom;
    @NotNull
    @Size(min = 1, message = "Le prénom de l'employé ne peut pas être vide.")
    private String prenom;
    @NotNull
    @Size(min = 1, message = "L'adresse mail de l'employé ne peut aps être vide.")
    private String email;
    private List<ProjetDto> historiqueProjets;


    public EmployeDto(long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.historiqueProjets = new ArrayList<>();
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProjetDto> getHistoriqueProjets() {
        return historiqueProjets;
    }

    public void setHistoriqueProjets(List<ProjetDto> historiqueProjets) {
        this.historiqueProjets = historiqueProjets;
    }
}
