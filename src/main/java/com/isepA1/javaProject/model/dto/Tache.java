package com.isepA1.javaProject.model.dto;

import com.isepA1.javaProject.model.enums.Priorite;
import com.isepA1.javaProject.model.enums.Etats;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Tache {
    @Id
    private String nom;
    private int id;
    private Priorite priorite;
    private Date dateLimite;
    private String categorie;
    private String commentaires;
    private Etats etat;
}
