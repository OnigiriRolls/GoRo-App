package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.stage.Stage;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.TouristAttractionService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class TouristAttractionListController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView attractionsListView;

    public static final ObservableList data =
            FXCollections.observableArrayList();

    public void initialize(){
        data.clear();

        if(UserService.attractionsRepository != null) {
            for (TouristAttractions at : UserService.attractionsRepository.find()) {
                data.add(at.getTitle());
            }
        }

        attractionsListView.setItems(data);
    }

    public void handleDeleteAttraction() {
        if (!data.isEmpty() && attractionsListView.getSelectionModel().getSelectedItem()!=null) {
            int selectedIdx = attractionsListView.getSelectionModel().getSelectedIndex();

            TouristAttractionService.deleteAttraction(attractionsListView.getSelectionModel().getSelectedItem().toString());
            data.remove(selectedIdx);
        }
    }

    public void handleModifyAttraction(javafx.event.ActionEvent actionEvent) throws IOException {
        if(!data.isEmpty() && attractionsListView.getSelectionModel().getSelectedItem()!=null) {
            //800 800
            root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyTourist.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {
        //600 600
        root = FXMLLoader.load(getClass().getClassLoader().getResource("adminMenu.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
