package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.exceptions.UserDoesNotExistException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.net.http.WebSocketHandshakeException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Text rule1;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    public void handleRegisterAction() {
        rule1.setText("");

        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (PasswordNotOkException e){
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLogInAction(javafx.event.ActionEvent event) {
        rule1.setText("");

        try {
            UserService.checkPassAndUsername(usernameField.getText());
           // UserService.wrongPassword(usernameField.getText());

            if(role.getValue().toString().equals("Admin")){

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (UserDoesNotExistException e){
            registrationMessage.setText(e.getMessage());
        //} catch (WrongPasswordException e){
        //    registrationMessage.setText((e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
