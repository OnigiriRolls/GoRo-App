package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import org.loose.fis.sre.model.Offers;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.OfferService;
import org.loose.fis.sre.services.TouristAttractionService;

public class OffersListController {
    @FXML
    private ListView offersListView;

    public static final ObservableList data =
            FXCollections.observableArrayList();

    public void initialize(){
        for (Offers at : OfferService.offersRepository.find()) {
            data.add(at.getTitle());
        }

        offersListView.setItems(data);
    }
}
