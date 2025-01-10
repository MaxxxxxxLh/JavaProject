package com.isepA1.javaProject.controller;
import com.isepA1.javaProject.service.ProjetService;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;

@Component
public class HomeController {
    @FXML
    private Button logoutButton;

    @FXML
    private ListView<String> projectListView;

    @FXML
    private Hyperlink taskHyperlink;

    @Autowired
    private ProjetService projetService;


    @FXML
    public void initialize() {

        projectListView.getItems().addAll(projetService.getAllProjectNames());

        logoutButton.setOnAction(e -> System.out.println("Déconnecté !"));

        taskHyperlink.setOnAction(e -> System.out.println("Lien cliqué !"));
    }
}


