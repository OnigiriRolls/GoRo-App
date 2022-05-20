package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMenuController {
    @FXML
    public void handleMenuAction() throws Exception{
        Stage  primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenuO&A.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
}
