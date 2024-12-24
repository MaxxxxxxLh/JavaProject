package com.isepA1.javaProject.mapper;

import com.isepA1.javaProject.model.dto.TacheDto;
import com.isepA1.javaProject.model.postgres.Tache;

import static com.isepA1.javaProject.mapper.ProjetMapper.projetToDto;
import static com.isepA1.javaProject.mapper.ProjetMapper.projetToEntity;

public class TacheMapper {

    public static TacheDto tacheToDto(Tache tache) {
        return new TacheDto(
                tache.getNom(),
                tache.getId(),
                tache.getPriorite(),
                tache.getDateLimite(),
                tache.getCategorie(),
                tache.getCommentaires(),
                tache.getEtat(),
                projetToDto(tache.getProjet())
        );
    }

    public static Tache tacheToEntity(TacheDto tacheDto) {
        Tache tache = new Tache(
                tacheDto.getNom(),
                tacheDto.getId(),
                tacheDto.getDateLimite(),
                projetToEntity(tacheDto.getProjetDto())
        );
        tache.setPriorite(tacheDto.getPriorite());
        tache.setCategorie(tacheDto.getCategorie());
        tache.setCommentaires(tacheDto.getCommentaires());
        tache.setEtat(tacheDto.getEtat());
        return tache;
    }
}
