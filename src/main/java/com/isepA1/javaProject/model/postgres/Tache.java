package com.isepA1.javaProject.model.postgres;

import com.isepA1.javaProject.model.enums.Priorite;
import com.isepA1.javaProject.model.enums.Etat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Tache {
    @Id
    private String nom;
    private long id;
    private Priorite priorite;
    private Date dateLimite;
    private String categorie;
    private String commentaires;
    private Etat etat;
    public Tache(String nom, long id, Date dateLimite){
        this.nom = nom;
        this.id = id;
        this.dateLimite = dateLimite;
        this.priorite = Priorite.Urgent;
        this.categorie = "";
        this.commentaires = "";
        this.etat = Etat.A_Faire;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    public Date getDateLimite() {
        return this.dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }
    public Priorite getPriorite(){
        return this.priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCommentaires() {
        return this.commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
