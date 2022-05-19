package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class AdminMenuController {

    @FXML
    public void handleAddOfferAction() throws Exception{
        Stage primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addOffer.fxml"));
        primaryStage.setTitle("Add Offer");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public void handleModifyAttraction() throws IOException {
        Stage primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("TouristAttractionList.fxml"));
        primaryStage.setTitle("Modify Attraction");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public void handleAddAttraction() throws IOException {
        Stage primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("TouristAttractionList.fxml"));
        primaryStage.setTitle("Modify Attraction");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }
}
