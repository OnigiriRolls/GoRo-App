package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.stage.Stage;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.TouristAttractionService;

import java.io.IOException;

public class TouristAttractionListController {
    @FXML
    private ListView attractionsListView;

    public static final ObservableList data =
            FXCollections.observableArrayList();

    public void initialize(){
        for (TouristAttractions at : TouristAttractionService.attractionsRepository.find()) {
            data.add(at.getTitle());
        }

        attractionsListView.setItems(data);
    }

    public void handleModifyAttraction() throws IOException {
        Stage primaryStage = null;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyTourist.fxml"));
        primaryStage.setTitle("Modify Attraction");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    public void handleDeleteAttraction(){
        int selectedIdx = attractionsListView.getSelectionModel().getSelectedIndex();
        TouristAttractionService.deleteAttraction(attractionsListView.getSelectionModel().getSelectedItem().toString());
        data.remove(selectedIdx);
    }
}
