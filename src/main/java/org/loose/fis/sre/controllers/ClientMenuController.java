package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void handleMenuAction() throws Exception{
        Stage  primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenuO&A.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenu.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
