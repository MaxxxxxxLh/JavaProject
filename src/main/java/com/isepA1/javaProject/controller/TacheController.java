package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.enums.Priorite;
import com.isepA1.javaProject.model.enums.Etat;
import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Arrays;

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

    public void loadTache(long tacheId) {
        currentTache = tacheService.getTacheById(tacheId).orElse(null);
        if (currentTache != null) {
            nomTextField.setText(currentTache.getNom());
            CommentairesTextArea.setText(currentTache.getCommentaires());
            prioriteChoiceBox.setValue(currentTache.getPriorite().name());
            etatChoiceBox.setValue(currentTache.getEtat().name());
            DateLimiteDatePicker.setValue(currentTache.getDateLimite().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        }
    }

    @FXML
    private void saveTache() {
        if (currentTache != null) {
            currentTache.setNom(nomTextField.getText());
            currentTache.setCommentaires(CommentairesTextArea.getText());
            currentTache.setPriorite(Priorite.valueOf(prioriteChoiceBox.getValue()));
            currentTache.setEtat(Etat.valueOf(etatChoiceBox.getValue()));
            currentTache.setDateLimite(java.sql.Date.valueOf(DateLimiteDatePicker.getValue()));

            tacheService.updateTache(currentTache.getId(), currentTache);

            Stage stage = (Stage) SauvegardeButton.getScene().getWindow();
            stage.close();
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

        SauvegardeButton.setOnAction(event -> saveTache());
    }
}
