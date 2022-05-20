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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UncompletedBuyException;
import org.loose.fis.sre.model.Request;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class BuyTicketController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField phoneText;
    @FXML
    private Text addMessage;
    @FXML
    private ChoiceBox typeBox;

    @FXML
    private void initialize() {
        typeBox.getItems().addAll("Normal", "Student","Elder","Kid","Group");
    }

    public void handleBuyTicket(ActionEvent actionEvent) {
        try{
            checkInfo();

            String completeName=nameText.getText()+surnameText.getText();
            UserService.requestsRepository.insert(new Request(completeName,emailText.getText(),addressText.getText(),
                    phoneText.getText(),BuyTicketListController.atTitle, typeBox.getSelectionModel().getSelectedItem().toString()));
            addMessage.setText("Ticket requested!");
        }catch (UncompletedBuyException e){
            addMessage.setText(e.getMessage());
        }
    }

    private void checkInfo() throws UncompletedBuyException {
        if(nameText.getText()=="" || surnameText.getText()=="" || emailText.getText()==""
                ||addressText.getText()=="" || phoneText.getText()=="" || typeBox.getSelectionModel().getSelectedItem()==null)
            throw new UncompletedBuyException("Introduceti toate datele!");
        if(!emailText.getText().contains("@"))
            throw new UncompletedBuyException("Mailul nu este valid!");
    }

    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("buyTicketList.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
