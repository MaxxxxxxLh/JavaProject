package com.isepA1.javaProject.model.postgres;

import com.isepA1.javaProject.model.enums.Priorite;
import com.isepA1.javaProject.model.enums.Etat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;
    private Date dateLimite;
    private String categorie;
    private String commentaires;

    @Enumerated(EnumType.STRING)
    private Etat etat;
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    public Tache(String nom, long id, Date dateLimite, Projet projet){
        this.nom = nom;
        this.id = id;
        this.dateLimite = dateLimite;
        this.priorite = Priorite.URGENT;
        this.categorie = "";
        this.commentaires = "";
        this.etat = Etat.A_FAIRE;
        this.projet = projet;
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
    public Projet getProjet(){
        return this.projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
