package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class AdminMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void handleAddOffer(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getClassLoader().getResource("addOffer.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleModifyAttraction(javafx.event.ActionEvent actionEvent) throws IOException {
        //600 600
        root = FXMLLoader.load(getClass().getClassLoader().getResource("TouristAttractionList.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleAddAttraction(javafx.event.ActionEvent actionEvent) throws IOException {
        //600 600
        root = FXMLLoader.load(getClass().getClassLoader().getResource("addAttraction.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
