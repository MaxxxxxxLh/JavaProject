package com.isepA1.javaProject.repository;

import com.isepA1.javaProject.model.postgres.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

}
