package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class BuyTicketListController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String atTitle;

    @FXML
    private ListView attractionsListView;

    public static final ObservableList data =
            FXCollections.observableArrayList();

    public void  initialize(){
        if(attractionsListView!=null) {
            data.clear();

            if (UserService.attractionsRepository != null) {
                for (TouristAttractions at : UserService.attractionsRepository.find()) {
                    data.add(at.getTitle());
                }
            }
            attractionsListView.setItems(data);
        }
    }

    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("clientMenu.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleBuy(javafx.event.ActionEvent actionEvent) throws IOException {
        if(attractionsListView.getSelectionModel().getSelectedItem()!=null) {

            atTitle = attractionsListView.getSelectionModel().getSelectedItem().toString();

            root = FXMLLoader.load(getClass().getClassLoader().getResource("buyTicket.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
