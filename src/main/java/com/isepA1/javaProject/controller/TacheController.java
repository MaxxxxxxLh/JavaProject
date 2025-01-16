package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.enums.Priorite;
import com.isepA1.javaProject.model.enums.Etat;
import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.service.TacheService;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Date;

import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Controller
public class TacheController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextArea CommentairesTextArea;

    @FXML
    private ChoiceBox<String> prioriteChoiceBox;

    @FXML
    private ChoiceBox<String> etatChoiceBox;

    @FXML
    private DatePicker DateLimiteDatePicker;

    @FXML
    private Button SauvegardeButton;

    private Tache currentTache;

    @Autowired
    private TacheService tacheService;
    @Autowired
    private ProjetController projetController;

    public void loadTache(long tacheId, Projet projet) {
        currentTache = tacheService.getTacheById(tacheId).orElse(new Tache("Nouvelle Tache", new Date(), projet));
        if (tacheService.findTacheById(currentTache.getId()).isEmpty()){
            tacheService.createTache(currentTache);
        }
        nomTextField.setText(currentTache.getNom());
        CommentairesTextArea.setText(currentTache.getCommentaires());
        prioriteChoiceBox.setValue(currentTache.getPriorite().name());
        etatChoiceBox.setValue(currentTache.getEtat().name());
        DateLimiteDatePicker.setValue(currentTache.getDateLimite().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());

    }

    @FXML
    private void saveTache(ActionEvent event) {
        if (currentTache != null) {
            currentTache.setNom(nomTextField.getText());
            currentTache.setCommentaires(CommentairesTextArea.getText());
            currentTache.setPriorite(Priorite.valueOf(prioriteChoiceBox.getValue()));
            currentTache.setEtat(Etat.valueOf(etatChoiceBox.getValue()));
            currentTache.setDateLimite(java.sql.Date.valueOf(DateLimiteDatePicker.getValue()));

            tacheService.updateTache(currentTache.getId(), currentTache);
            redirect(event,getClass(),"/com/isepA1/javaProject/projetView.fxml","Projet");
            projetController.initializeWithProjetId(currentTache.getProjet().getId());
        }
    }

    @FXML
    private void initialize() {
        prioriteChoiceBox.getItems().addAll(
                Arrays.stream(Priorite.values()).map(Enum::name).toList()
        );

        etatChoiceBox.getItems().addAll(
                Arrays.stream(Etat.values()).map(Enum::name).toList()
        );

        SauvegardeButton.setOnAction(event -> saveTache(event));
    }
}
