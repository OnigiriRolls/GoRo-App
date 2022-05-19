package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.UncompletedBuyException;

public class BuyTicketController {
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
            //create request
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
}
