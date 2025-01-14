package com.isepA1.javaProject.repository;

import com.isepA1.javaProject.model.enums.Etat;
import com.isepA1.javaProject.model.postgres.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByEtat(Etat etat);
    List<Tache> findByDateLimite(Date dateLimite);
}
