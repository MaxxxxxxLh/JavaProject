package com.isepA1.javaProject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

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
    private TextArea dropArea;

    @FXML
    private TextField chatInputField;

    @FXML
    private Button sendChatButton;

    private boolean isAdmin = true; // Simule si l'utilisateur est un patron

    @FXML
    public void initialize() {
        setupEditableLabel(projectTitle, isAdmin);
        setupEditableTextArea(projectDescription, isAdmin);
        setupFileDropArea();
        sendChatButton.setOnAction(event -> sendMessage());
        setupTaskDragAndDrop();
    }

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

    private void setupFileDropArea() {
        dropArea.setOnDragOver(event -> {
            if (event.getGestureSource() != dropArea && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
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
    }

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

    @FXML
    private void addTask() {
        HBox taskBox = new HBox(10);
        CheckBox taskCheckBox = new CheckBox();
        Label taskLabel = new Label("Tâche");

        taskLabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TextField textField = new TextField(taskLabel.getText());
                textField.setOnAction(e -> {
                    taskLabel.setText(textField.getText().isEmpty() ? "Tâche" : textField.getText());
                    ((HBox) taskLabel.getParent()).getChildren().set(1, taskLabel);
                });
                textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        taskLabel.setText(textField.getText().isEmpty() ? "Tâche" : textField.getText());
                        ((HBox) taskLabel.getParent()).getChildren().set(1, taskLabel);
                    }
                });
                ((HBox) taskLabel.getParent()).getChildren().set(1, textField);
                textField.requestFocus();
            }
        });

        taskBox.getChildren().addAll(taskCheckBox, taskLabel);
        taskBox.setOnDragDetected(event -> {
            Dragboard db = taskBox.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(taskLabel.getText());
            db.setContent(content);
            event.consume();
        });

        toStartTaskList.getChildren().add(taskBox);
    }
}

