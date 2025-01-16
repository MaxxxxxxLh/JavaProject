package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.service.EmployeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Component
public class AdminController {
    @Autowired
    private EmployeService employeService;
    @Autowired
    private ProjetController projetController;

    @FXML
    private Button addAdminButton;

    @FXML
    private ListView<String> nonUsersAdminList;

    @FXML
    private Button createProjectButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private ListView<String> usersList;

    @FXML
    public void initialize() {
        refreshLists();
    }

    @FXML
    private void addAdmin(ActionEvent event) {
        String selectedName = nonUsersAdminList.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            Employe employe = employeService.getAllEmployes()
                    .stream()
                    .filter(e -> e.getNom().equals(selectedName))
                    .findFirst()
                    .orElse(null);

            if (employe != null) {
                employe.setAdmin(true);
                employeService.saveEmploye(employe);
                refreshLists();
            }
        }
    }

    @FXML
    private void createProject(ActionEvent event) {
        redirect(event,getClass(),"/com/isepA1/javaProject/projetView.fxml","Projet");
        Projet projet = new Projet("Nouveau projet",new Date());
        projet.getMembres().add(LoginController.loggedEmployed);
        projetController.initializeWithProjetId(projet.getId());
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        String selectedName = usersList.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            Employe employe = employeService.getAllEmployes()
                    .stream()
                    .filter(e -> e.getNom().equals(selectedName))
                    .findFirst()
                    .orElse(null);

            if (employe != null) {
                employeService.deleteEmploye(employe.getId());
                refreshLists();
            }
        }
    }



    private void refreshLists() {
        nonUsersAdminList.getItems().setAll(
                employeService.getAllEmployes()
                        .stream()
                        .filter(employe -> !employe.isAdmin())
                        .map(Employe::getNom)
                        .toList()
        );

        usersList.getItems().setAll(
                employeService.getAllEmployes()
                        .stream()
                        .map(Employe::getNom)
                        .toList()
        );
    }
    @FXML
    private void redirectHomePage(ActionEvent event){
        redirect(event, getClass(), "/com/isepA1/javaProject/homeView.fxml", "Home Page");
    }
}
