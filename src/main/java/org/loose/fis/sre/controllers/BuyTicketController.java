package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UncompletedBuyException;
import org.loose.fis.sre.model.Request;
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

    public void handleBuyTicket(ActionEvent actionEvent) {
        try{
            checkInfo();
            String completeName=nameText.getText()+surnameText.getText();
            //introdus titlul atractiei
            //UserService.requestsRepository.insert(new Request(completeName,,emailText.getText(),addressText.getText(),phoneText.getText()));
        }catch (UncompletedBuyException e){
            addMessage.setText(e.getMessage());
        }
    }

    private void checkInfo() throws UncompletedBuyException {
        if(nameText.getText()=="" || surnameText.getText()=="" || emailText.getText()==""
                ||addressText.getText()=="" || phoneText.getText()=="")
            throw new UncompletedBuyException("Introduceti toate datele!");
        if(!emailText.getText().contains("@"))
            throw new UncompletedBuyException("Mailul nu este valid!");
    }

    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("adminMenu.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
