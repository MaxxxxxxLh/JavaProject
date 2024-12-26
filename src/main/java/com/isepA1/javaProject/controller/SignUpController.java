package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import com.isepA1.javaProject.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpController {

    @Autowired
    private EmployeService employeService;

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button signUpButton;

    @FXML
    private Label errorMessage;

    @FXML
    private void handleSignUp(ActionEvent event) {
        String newPrenom = prenom.getText();
        String newNom = nom.getText();
        String newEmail = email.getText();
        String newPassword = password.getText();

        if (newPrenom.isEmpty() || newNom.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
            errorMessage.setText("Veuillez remplir tous les champs.");
        } else {
            if(employeService.getAllEmployes().stream().noneMatch(employe -> employe.getEmail().equals(newEmail))){
                try{
                    employeService.saveEmploye(new Employe(newPrenom,newNom,newEmail,newPassword));
                    System.out.println("Utilisateur créé avec succès !");
                }catch(Error error){
                    System.out.println(error.getMessage());
                }

            }else{
                errorMessage.setText("Un utilisateur est déjà créé avec cet email. Veuillez utiliser une autre adresse mail ou connectez vous avec votre compte existant");
            }

        }
    }
}
