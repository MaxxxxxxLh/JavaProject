package com.isepA1.javaProject.repository;

import com.isepA1.javaProject.model.postgres.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
