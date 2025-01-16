package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Tache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.service.ProjetService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.isepA1.javaProject.JavaFXApplication.getContext;
import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Controller
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @FXML
    private Label projectTitle;

    @FXML
    private TextArea projectDescription;

    @FXML
    private VBox fileListContainer;

    @FXML
    private VBox toStartTaskList;

    @FXML
    private VBox inProgressTaskList;

    @FXML
    private VBox completedTaskList;

    @FXML
    private VBox chatMessagesContainer;

    @FXML
    private Button addTaskButton;

    @FXML
    private TextField chatInputField;

    @FXML
    private Button sendChatButton;
    private Projet projet;
    /*
    public void initializeWithProjetId(long projetId) {
        projet = projetService.getProjetById(projetId).orElse(null);
        if (projet != null) {
            projectTitle.setText(projet.getNom());
            projectDescription.setText("Détails du projet : " + projet.getNom());
        }
        setupTaskDragAndDrop();
    *}/

    private void setupTaskDragAndDrop() {
        VBox[] taskStates = {toStartTaskList, inProgressTaskList, completedTaskList};

        for (VBox taskState : taskStates) {
            taskState.setOnDragOver(event -> {
                if (event.getGestureSource() != taskState && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            taskState.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    Node draggedTask = (Node) event.getGestureSource();
                    if (draggedTask.getParent() instanceof VBox) {
                        ((VBox) draggedTask.getParent()).getChildren().remove(draggedTask);
                    }
                    taskState.getChildren().add(draggedTask);
                    event.setDropCompleted(true);
                } else {
                    event.setDropCompleted(false);
                }
                event.consume();
            });
        }
    }*/

    public void addTask() {
        Tache tache = new Tache("",new Date(), projet);
        projetService.addTacheToProjet(projet, tache);
        projet.getListeTaches().add(tache);
        HBox taskBox = createTaskBox(tache.getId());
        toStartTaskList.getChildren().add(taskBox);
    }

    private HBox createTaskBox(long id) {
        HBox taskBox = new HBox(10);
        CheckBox taskCheckBox = new CheckBox();
        Label taskLabel = new Label("Nouvelle Tâche");

        taskBox.setId("task-" + System.currentTimeMillis());

        configureTaskDragAndDrop(taskBox);
        configureLabelClickEvent(taskLabel, id);

        taskBox.getChildren().addAll(taskCheckBox, taskLabel);

        return taskBox;
    }

    private void configureTaskDragAndDrop(HBox taskBox) {
        taskBox.setOnDragDetected(event -> {
            Dragboard db = taskBox.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(taskBox.getId());
            db.setContent(content);
            event.consume();
        });
    }

    private void configureLabelClickEvent(Label taskLabel, long id) {
        taskLabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                redirectToTaskView(event, id);
            }
        });
    }

    private void redirectToTaskView(MouseEvent event, long id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/isepA1/javaProject/tacheView.fxml"));
            loader.setControllerFactory(getContext()::getBean);
            Parent root = loader.load();

            TacheController tacheController = loader.getController();
            tacheController.loadTache(id);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tâche");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sendMessage() {
        String message = chatInputField.getText().trim();
        if (!message.isEmpty()) {
            Label messageLabel = new Label(message);
            messageLabel.setStyle("-fx-background-color: lightblue; -fx-text-fill: black; -fx-padding: 5; -fx-background-radius: 5;");
            chatMessagesContainer.getChildren().add(messageLabel);
            chatInputField.clear();
        }
    }
    @FXML
    private Button BacktoHome;
    @FXML
    private void RetourButton(ActionEvent event){
        redirect(event, getClass(), "/com/isepA1/javaProject/homeView.fxml", "home page");
    }
}
