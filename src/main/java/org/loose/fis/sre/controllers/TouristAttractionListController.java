package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.TouristAttractionService;

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

    public void handleModifyAttraction(){
        //deschide fxml modify
    }

    public void handleDeleteAttraction(){
        int selectedIdx = attractionsListView.getSelectionModel().getSelectedIndex();
        data.remove(selectedIdx);
    }
}
