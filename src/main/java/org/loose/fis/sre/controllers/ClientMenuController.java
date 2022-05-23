package org.loose.fis.sre.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;


public class ClientMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void handleMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenuO&A.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void handleBuy(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("buyTicketList.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleLogOut(javafx.event.ActionEvent actionEvent) throws IOException {
        //600 600
        root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  
    @FXML
    public void handleMenuAction() throws Exception{
        Stage  primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenuO&A.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    

   
        root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenu.fxml"));
        EventObject actionEvent = null;
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
