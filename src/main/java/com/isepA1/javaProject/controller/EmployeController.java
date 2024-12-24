package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.service.EmployeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    public Employe getEmployeById(@PathVariable Long id) {
        return employeService.getEmployeById(id).orElse(null);
    }

    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.saveEmploye(employe);
    }

    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable Long id) {
        employeService.deleteEmploye(id);
    }
}
