package com.isepA1.javaProject.controller;
import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.service.ProjetService;
import javafx.event.Event;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;

import java.util.Date;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Component
public class HomeController {
    @FXML
    private Hyperlink pageAdminLink;
    @FXML
    private Hyperlink taskHyperLink;
    @FXML
    private Button logoutButton;

    @FXML
    private ListView<String> projectListView;

    @Autowired
    private ProjetService projetService;

    @Autowired
    private ProjetController projetController;
    @FXML
    public void initialize() {
        long employeId = getCurrentEmployeId();
        projectListView.getItems().addAll(projetService.getProjectNamesByEmployeId(employeId));

        logoutButton.setOnAction(e -> redirect(e, getClass(), "/com/isepA1/javaProject/loginView.fxml", "Login"));

        taskHyperLink.setOnAction(e -> redirect(e, getClass(), "/com/isepA1/javaProject/calendrierView.fxml", "Calendrier"));
        
        if(LoginController.loggedEmployed.isAdmin()){
            pageAdminLink.setOnAction(e -> redirect(e,getClass(),"/com/isepA1/javaProject/adminView.fxml","Page Admin"));
        }else{
            pageAdminLink.setText("");
        }

        projectListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedProjectName = projectListView.getSelectionModel().getSelectedItem();
                if (selectedProjectName != null) {
                    long projectId = projetService.getProjetByNom(selectedProjectName).getId();
                    redirectToProjectPage(event, projectId);
                }
            }
        });
    }

    private long getCurrentEmployeId() {
        return 1L;
    }


    private void redirectToProjectPage(Event event, long projetId) {
        redirect(event,getClass(),"/com/isepA1/javaProject/projetView.fxml","Projet");
        Projet projet = new Projet("Nouveau projet",new Date());
        projet.getMembres().add(LoginController.loggedEmployed);
        projetController.initializeWithProjetId(projet.getId());
    }
}
