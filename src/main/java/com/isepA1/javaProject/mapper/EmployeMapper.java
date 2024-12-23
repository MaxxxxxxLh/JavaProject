package com.isepA1.javaProject.mapper;

import com.isepA1.javaProject.model.dto.EmployeDto;
import com.isepA1.javaProject.model.postgres.Employe;


public class EmployeMapper {

    public static EmployeDto employeToDto(Employe employe) {
        return new EmployeDto(
                employe.getId(),
                employe.getNom(),
                employe.getPrenom()
        );
    }

    public static Employe employeToEntity(EmployeDto dto) {
        Employe employe = new Employe(dto.getNom(),dto.getPrenom(), dto.getId());
        employe.setId(dto.getId());
        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        return employe;
    }
}
