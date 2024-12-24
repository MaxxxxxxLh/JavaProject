package com.isepA1.javaProject.model.dto;

import com.isepA1.javaProject.model.enums.Priorite;
import com.isepA1.javaProject.model.enums.Etat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

public class TacheDto {

    @NotNull
    @Size(min = 1, message = "Le nom de la tâche ne peut pas être vide.")
    private String nom;

    private long id;

    @NotNull(message = "La priorité doit être définie.")
    private Priorite priorite;

    private Date dateLimite;

    @Size(max = 100, message = "La catégorie ne peut pas dépasser 100 caractères.")
    private String categorie;

    @Size(max = 500, message = "Le commentaire ne peut pas dépasser 500 caractères.")
    private String commentaires;

    @NotNull(message = "L'état de la tâche est obligatoire.")
    private Etat etat;

    public TacheDto(String nom, long id, Priorite priorite, Date dateLimite, String categorie, String commentaires, Etat etat) {
        this.nom = nom;
        this.id = id;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.categorie = categorie;
        this.commentaires = commentaires;
        this.etat = etat;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
