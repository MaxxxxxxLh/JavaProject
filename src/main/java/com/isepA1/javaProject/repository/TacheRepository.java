package com.isepA1.javaProject.repository;

import com.isepA1.javaProject.model.postgres.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache, Long> {
}
