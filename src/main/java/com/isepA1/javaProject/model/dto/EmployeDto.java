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
    @Size(min = 1, message = "L'adresse mail de l'employé ne peut pas être vide.")
    private String email;
    @NotNull
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;
    private boolean isAdmin;
    private List<ProjetDto> historiqueProjets;


    public EmployeDto(long id, String nom, String prenom, String email, String password, boolean isAdmin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.historiqueProjets = new ArrayList<>();
        this.isAdmin = isAdmin;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProjetDto> getHistoriqueProjets() {
        return historiqueProjets;
    }

    public void setHistoriqueProjets(List<ProjetDto> historiqueProjets) {
        this.historiqueProjets = historiqueProjets;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
}
