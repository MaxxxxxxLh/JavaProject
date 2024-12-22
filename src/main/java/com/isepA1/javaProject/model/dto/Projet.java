package com.isepA1.javaProject.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Projet {
    @Id
    private String nom;
    private int id;
    private List<Tache> listeTaches;
    private Date dateLimite;
    private List<Employe> membres;
}
