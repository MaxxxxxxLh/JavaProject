package com.isepA1.javaProject.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

import static com.isepA1.javaProject.JavaFXApplication.getContext;

public class FxmlHelper {
    @FXML
    public static void redirect(ActionEvent event, Class c, String fxmlPath, String title) {
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
