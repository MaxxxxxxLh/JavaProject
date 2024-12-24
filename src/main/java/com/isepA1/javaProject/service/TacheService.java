package com.isepA1.javaProject.service;

import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.model.enums.Etat;
import com.isepA1.javaProject.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TacheService {
    @Autowired
    private TacheRepository tacheRepository;


}
