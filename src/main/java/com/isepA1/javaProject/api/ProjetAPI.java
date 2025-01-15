package com.isepA1.javaProject.api;

import com.isepA1.javaProject.model.postgres.Projet;
import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.service.ProjetService;
import com.isepA1.javaProject.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

@RestController
@RequestMapping("/api/projets")
public class ProjetAPI {

    private final ProjetService projetService;
    private final EmployeService employeService;

    @Autowired
    public ProjetAPI(ProjetService projetService, EmployeService employeService) {
        this.projetService = projetService;
        this.employeService = employeService;
    }
    @GetMapping("/")
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable long id) {
        Optional<Projet> projet = projetService.getProjetById(id);
        return projet.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet createdProjet = projetService.createProjet(projet);
        return new ResponseEntity<>(createdProjet, HttpStatus.CREATED);
    }

    @PostMapping("/{projetId}/membres/{employeId}")
    public ResponseEntity<Projet> addMembreToProjet(
            @PathVariable long projetId,
            @PathVariable long employeId) {

        Optional<Projet> projet = projetService.getProjetById(projetId);
        Optional<Employe> employe = employeService.getEmployeById(employeId);

        if (projet.isPresent() && employe.isPresent()) {
            projetService.addMembreToProjet(projet.get(), employe.get());
            return new ResponseEntity<>(projet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{projetId}/membres")
    public ResponseEntity<List<Employe>> getMembresOfProjet(@PathVariable long projetId) {
        Optional<Projet> projet = projetService.getProjetById(projetId);
        if (projet.isPresent()) {
            return new ResponseEntity<>(projet.get().getMembres(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    private boolean isAdmin = true; // Variable pour simuler si l'utilisateur est un patron

    @FXML
    public void initialize() {
        // Configuration pour double-cliquer sur le titre du projet
        setupEditableLabel(projectTitle, isAdmin);

        // Configuration pour double-cliquer sur la description du projet
        setupEditableTextArea(projectDescription, isAdmin);

        // Configuration pour le drag-and-drop des fichiers
        setupFileDropArea();

        // Bouton d'envoi de message
        sendChatButton.setOnAction(event -> sendMessage());

        // Configuration pour le drag-and-drop des tâches
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
        if (editable) {
            textArea.setEditable(true);
        } else {
            textArea.setEditable(false);
        }
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
        // Création de la boîte horizontale pour la tâche
        HBox taskBox = new HBox(10);

        // Création de la checkbox
        CheckBox taskCheckBox = new CheckBox();

        // Création du label avec un texte par défaut
        Label taskLabel = new Label("Tâche");

        // Permet au patron de modifier le nom de la tâche en double-cliquant
        taskLabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TextField textField = new TextField(taskLabel.getText());
                textField.setOnAction(e -> {
                    taskLabel.setText(textField.getText().isEmpty() ? "Tâche" : textField.getText());
                    ((HBox) taskLabel.getParent()).getChildren().set(1, taskLabel);
                });
                textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) { // Si on clique ailleurs, le label est restauré
                        taskLabel.setText(textField.getText().isEmpty() ? "Tâche" : textField.getText());
                        ((HBox) taskLabel.getParent()).getChildren().set(1, taskLabel);
                    }
                });
                ((HBox) taskLabel.getParent()).getChildren().set(1, textField);
                textField.requestFocus();
            }
        });

        // Ajout des composants à la boîte de la tâche
        taskBox.getChildren().addAll(taskCheckBox, taskLabel);

        // Configuration pour le drag-and-drop de la tâche
        taskBox.setOnDragDetected(event -> {
            Dragboard db = taskBox.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(taskLabel.getText());
            db.setContent(content);
            event.consume();
        });

        // Ajout de la tâche à la liste des tâches à démarrer
        toStartTaskList.getChildren().add(taskBox);
    }
}