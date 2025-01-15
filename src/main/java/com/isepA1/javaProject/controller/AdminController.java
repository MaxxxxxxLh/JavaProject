package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.service.EmployeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminController {
    @Autowired
    private EmployeService employeService;
    @FXML
    private Button addAdminButton;
    @FXML
    private ListView nonUsersAdminList;
    @FXML
    private Button createProjectButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private ListView usersList;
    @FXML
    private void addAdmin(ActionEvent event){
        nonUsersAdminList.getItems().setAll(employeService.getAllEmployes().stream().filter(employe -> !employe.isAdmin()).map(Employe::getNom).toList());
    }
    @FXML
    private void createProject(ActionEvent event){

    }
    @FXML
    private void deleteUser(ActionEvent event){

    }

}
