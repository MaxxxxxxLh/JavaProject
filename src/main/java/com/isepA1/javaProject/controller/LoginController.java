package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.service.EmployeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Component
public class LoginController {

    @Autowired
    private EmployeService employeService;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Hyperlink forgotPasswordLink;
    @FXML
    private Label errorMessage;
    @FXML
    public void handleLoginButtonClick(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();
            Employe employe1 = employeService.getAllEmployes().stream().filter(employe -> employe.getEmail().equals(email)).findFirst().orElse(null);
            if (employe1 != null && employeService.verifyPassword(password, employe1.getPassword())) {
                redirect(event, getClass(),"/com/isepA1/javaProject/homeView.fxml", "Home Page");
            } else {
                errorMessage.setText("Adresse mail ou mot de passe incorrect");
            }
        }catch (Error error){
            errorMessage.setText("Veuillez remplir tous les champs");
        }
    }

    @FXML
    public void redirectToSignUp(ActionEvent event){
        redirect(event, getClass(), "/com/isepA1/javaProject/signUpView.fxml", "Sign Up");
    }

    @FXML
    public void handleForgotPasswordLinkClick(ActionEvent event) {
        //A implementer uniquement à la fin
        errorMessage.setText("Fonctionnalité de réinitialisation non implémentée pour le moment");
    }

    @FXML
    public void handleForgotPasswordButtonClick(ActionEvent event) {
        redirect(event, getClass(), "/com/isepA1/javaProject/forgotPasswordView.fxml", "Mot de passe oublié");
    }

}
