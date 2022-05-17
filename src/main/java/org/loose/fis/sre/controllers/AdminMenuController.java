package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

public class AdminMenuController {

    @FXML
    public void handleAddOfferAction() throws Exception{
        Stage primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addOffer.fxml"));
        primaryStage.setTitle("Add Offer");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
}
