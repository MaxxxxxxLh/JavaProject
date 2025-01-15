package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.service.EmailService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import com.isepA1.javaProject.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.regex.Pattern;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Component
public class SignUpController {

    @Autowired
    private EmployeService employeService;
    @Autowired
    private NotificationController notificationController;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirmation;

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
        String newPasswordConfirmation = passwordConfirmation.getText();

        if (newPrenom.isEmpty() || newNom.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty() || newPasswordConfirmation.isEmpty()) {
            errorMessage.setText("Veuillez remplir tous les champs.");
        } else if(!isValidEmail(newEmail)){
            errorMessage.setText("Veuillez entre une adresse mail valide");
        }else if(!isValidPassword(newPassword)){
            errorMessage.setText("Veuillez saisir un mot de passe entre 8 et 20 caractères");
        }else if (!newPasswordConfirmation.equals(newPassword)) {
            errorMessage.setText("Mot de passe de confirmation erroné");
        }else{
                if(employeService.getAllEmployes().stream().noneMatch(employe -> employe.getEmail().equals(newEmail))){
                    try{
                        Employe newEmploye = new Employe(newPrenom,newNom,newEmail,newPassword);
                        employeService.saveEmploye(newEmploye);
                        notificationController.sendSignUpConfirmation(newEmploye);
                        System.out.println("Utilisateur créé avec succès !");
                        redirect(event, getClass(), "/com/isepA1/javaProject/homeView.fxml", "Home Page");
                    }catch(Error error){
                        System.out.println(error.getMessage());
                    }

                }else{
                    errorMessage.setText("Un utilisateur est déjà créé avec cet email. Veuillez utiliser une autre adresse mail ou connectez vous avec votre compte existant");
                }

        }

    }
    @FXML
    public void redirectToLogin(ActionEvent event){
        redirect(event, getClass(), "/com/isepA1/javaProject/loginView.fxml", "Login");
    }

    private boolean isValidEmail(String email){
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    private boolean isValidPassword(String password){
        return password.length() >= 8 && password.length() <= 20;
    }
}
