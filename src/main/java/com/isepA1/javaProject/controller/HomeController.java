package com.isepA1.javaProject.controller;
import com.isepA1.javaProject.service.ProjetService;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Component
public class HomeController {
    public Hyperlink taskHyperLink;
    @FXML
    private Button logoutButton;

    @FXML
    private ListView<String> projectListView;

    @Autowired
    private ProjetService projetService;


    @FXML
    public void initialize() {

        projectListView.getItems().addAll(projetService.getAllProjectNames());

        logoutButton.setOnAction(e -> redirect(e,getClass(),"/com/isepA1/javaProject/loginView.fxml", "Login"));

        taskHyperLink.setOnAction(e -> redirect(e,getClass(),"/com/isepA1/javaProject/calendrierView.fxml","Calendrier"));
    }
}


