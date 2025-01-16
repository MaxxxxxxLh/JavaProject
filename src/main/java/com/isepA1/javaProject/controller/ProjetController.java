package com.isepA1.javaProject.controller;

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
import org.springframework.stereotype.Controller;

import java.io.IOException;

import static com.isepA1.javaProject.JavaFXApplication.getContext;
import static com.isepA1.javaProject.utils.FxmlHelper.redirect;

@Controller
public class ProjetController {

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
    private TextArea dropArea;

    @FXML
    private TextField chatInputField;

    @FXML
    private Button sendChatButton;

    private boolean isAdmin = true; // Simule si l'utilisateur est un patron


    /*@FXML
    public void initialize() {
        setupEditableLabel(projectTitle, isAdmin);
        setupEditableTextArea(projectDescription, isAdmin);
        setupFileDropArea();
        sendChatButton.setOnAction(event -> sendMessage());
        setupTaskDragAndDrop();
    }*/

    private void setupEditableLabel(Label label, boolean editable) {
        if (editable) {
            label.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    TextField textField = new TextField(label.getText());
                    textField.setOnAction(e -> {
                        label.setText(textField.getText());
                        ((VBox) label.getParent()).getChildren().set(0, label);
                    });
                    ((VBox) label.getParent()).getChildren().set(0, textField);
                }
            });
        }
    }

    private void setupEditableTextArea(TextArea textArea, boolean editable) {
        textArea.setEditable(editable);
    }
    @FXML
    private void handleDragOver(DragEvent event){
            if ( event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
    }
    @FXML
    private void handleDrop(DragEvent event){

    }
    /*private void setupFileDropArea() {
        dropArea.setOnDragOver(event -> {
            if (event.getGestureSource() != dropArea && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });

        dropArea.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                db.getFiles().forEach(file -> {
                    Label fileLabel = new Label(file.getName());
                    fileListContainer.getChildren().add(fileLabel);
                });
                dropArea.clear();
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }*/
    @FXML
    private void sendMessage() {
        String message = chatInputField.getText().trim();
        if (!message.isEmpty()) {
            Label messageLabel = new Label(message);
            messageLabel.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 15;");
            chatMessagesContainer.getChildren().add(messageLabel);
            chatInputField.clear();
        }
    }

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
                    ((VBox) draggedTask.getParent()).getChildren().remove(draggedTask);
                    taskState.getChildren().add(draggedTask);
                    event.setDropCompleted(true);
                } else {
                    event.setDropCompleted(false);
                }
                event.consume();
            });
        }
    }

    public void addTask() {
        // Créer une HBox pour la tâche
        HBox taskBox = createTaskBox();

        // Ajouter la tâche au conteneur
        toStartTaskList.getChildren().add(taskBox);
    }

    private HBox createTaskBox() {
        // Créer la HBox avec un espacement
        HBox taskBox = new HBox(10);

        // Créer les éléments pour la tâche
        CheckBox taskCheckBox = new CheckBox();
        Label taskLabel = new Label("Tâche");

        // Configurer l'événement de clic pour le label
        configureLabelClickEvent(taskLabel);

        // Ajouter les éléments à la HBox
        taskBox.getChildren().addAll(taskCheckBox, taskLabel);

        return taskBox;
    }

    private void configureLabelClickEvent(Label taskLabel) {
        taskLabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                redirecttache(event, getClass(), "/com/isepA1/javaProject/tacheView.fxml", "Tâche");
            }
        });
    }
    @FXML
    public static void redirecttache(MouseEvent event, Class c, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(c.getResource(fxmlPath));
            loader.setControllerFactory(getContext()::getBean);
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de " + fxmlPath);
        }
    }
}

