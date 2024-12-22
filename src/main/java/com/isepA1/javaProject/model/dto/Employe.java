package com.isepA1.javaProject.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Employe {
    @Id
    private String nom;
    private String prenom;
    private int id;
    private List<Projet> historiqueProjets;
}
