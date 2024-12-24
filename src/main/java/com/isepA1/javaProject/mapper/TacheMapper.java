package com.isepA1.javaProject.mapper;

import com.isepA1.javaProject.model.dto.TacheDto;
import com.isepA1.javaProject.model.postgres.Tache;

public class TacheMapper {

    public static TacheDto toDto(Tache tache) {
        return new TacheDto(
                tache.getNom(),
                tache.getId(),
                tache.getPriorite(),
                tache.getDateLimite(),
                tache.getCategorie(),
                tache.getCommentaires(),
                tache.getEtat()
        );
    }

    public static Tache toEntity(TacheDto tacheDto) {
        Tache tache = new Tache(
                tacheDto.getNom(),
                tacheDto.getId(),
                tacheDto.getDateLimite()
        );
        tache.setPriorite(tacheDto.getPriorite());
        tache.setCategorie(tacheDto.getCategorie());
        tache.setCommentaires(tacheDto.getCommentaires());
        tache.setEtat(tacheDto.getEtat());
        return tache;
    }
}
