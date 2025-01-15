package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.service.EmployeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Component
public class ForgotPasswordController {

    @Autowired
    private EmployeService employeService;

    @FXML
    private TextField emailField;

    @FXML
    private Label feedbackLabel;

    @FXML
    public void handleValidateButtonClick(ActionEvent event) {
        String email = emailField.getText();

        if (email.isEmpty()) {
            feedbackLabel.setStyle("-fx-text-fill: red;");
            feedbackLabel.setText("Veuillez entrer une adresse email.");
            return;
        }

        boolean emailExists = employeService.getAllEmployes()
                .stream()
                .anyMatch(emp -> emp.getEmail().equals(email));

        if (emailExists) {
            feedbackLabel.setStyle("-fx-text-fill: green;");
            feedbackLabel.setText("Veuillez contacter lman.teamgroup@gmail.com");
        } else {
            feedbackLabel.setStyle("-fx-text-fill: red;");
            feedbackLabel.setText("L'adresse écrite n'est pas connue ou pas enregistrée.");
        }
    }

    @FXML
    public void handleReturnButtonClick(ActionEvent event) {
        redirect(event, getClass(), "/com/isepA1/javaProject/loginView.fxml", "Connexion");
    }
}
