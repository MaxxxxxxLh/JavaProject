package com.isepA1.javaProject.mapper;

import com.isepA1.javaProject.model.dto.ProjetDto;
import com.isepA1.javaProject.model.postgres.Projet;

public class ProjetMapper {
    public static ProjetDto projetToDto(Projet projet) {
        return new ProjetDto(
                projet.getId(),
                projet.getNom(),
                projet.getDateLimite()
        );
    }

    public static Projet projetToEntity(ProjetDto dto){
        return new Projet(
                dto.getId(),
                dto.getNom(),
                dto.getDateLimite()
        );
    }
}
