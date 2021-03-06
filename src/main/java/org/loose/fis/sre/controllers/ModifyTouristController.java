package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.TAAlreadyExistsException;
import org.loose.fis.sre.services.ModificationsService;
import org.loose.fis.sre.services.TouristAttractionService;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifyTouristController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView imageView;
    @FXML
    StackPane contentPane;
    @FXML
    BorderPane layout;
    @FXML
    private DatePicker availFromModify;
    @FXML
    private DatePicker availToModify;
    @FXML
    private TextField titleModify;
    @FXML
    private TextField priceModify;
    @FXML
    private TextArea descriptModify;
    @FXML
    private Text modifyMessage;

    private String photoModify;

    public void initialize() {

        contentPane.setOnDragOver(new EventHandler() {
            @Override
            public void handle(Event event) {
                mouseDragOver((DragEvent) event);
            }
        });

        contentPane.setOnDragDropped(new EventHandler() {
            @Override
            public void handle(Event event) {
                mouseDragDropped((DragEvent) event);
            }
        });

        contentPane.setOnDragExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                contentPane.setStyle("-fx-border-color: #C6C6C6;");
            }
        });

        layout.setCenter(contentPane);
    }

    void addImage(Image i, StackPane pane) {
        imageView.setImage(i);
        pane.getChildren().add(imageView);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
    }

    private void mouseDragDropped(final DragEvent e) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    photoModify = file.getAbsolutePath();

                    try {
                        if (!contentPane.getChildren().isEmpty()) {
                            contentPane.getChildren().remove(0);
                        }
                        Image img = new Image(new FileInputStream(file.getAbsolutePath()));

                        addImage(img, contentPane);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ModifyTouristController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        e.setDropCompleted(success);
        e.consume();
    }

    private void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();

        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");

        if (db.hasFiles()) {
            if (isAccepted) {
                contentPane.setStyle("-fx-border-color: red;"
                        + "-fx-border-width: 5;"
                        + "-fx-background-color: #C6C6C6;"
                        + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }

    public void handleModify() {
        if (titleModify.getText()!="" && photoModify!=null
                && availFromModify!=null && availToModify!=null && descriptModify!=null
        && priceModify!=null)
        {
            String availFrom = availFromModify.getValue().getDayOfMonth()
                    + "." + availFromModify.getValue().getMonth() + "." + availFromModify.getValue().getYear();
            String availTo = availToModify.getValue().getDayOfMonth()
                    + "." + availToModify.getValue().getMonth() + "." + availToModify.getValue().getYear();
            String avail = availFrom + ";" + availTo;

            //TouristAttractionService.addPhoto(photoModify);
            TouristAttractionService.saveChanges(titleModify.getText(), TouristAttractionService.getPhotoTitle(photoModify), avail, descriptModify.getText(), Integer.valueOf(priceModify.getText()));
            modifyMessage.setText("Tourist Attraction modified!");
        }
        else {
            modifyMessage.setText("Empty fields!");
            return;
        }

        //adaugare modificare atractie turistica
        String avail = null;
        if (titleModify.getText()!="" && photoModify!=null
                && availFromModify!=null && availToModify!=null && descriptModify!=null
                && priceModify!=null) {
            String availFrom = availFromModify.getValue().getDayOfMonth()
                    + "." + availFromModify.getValue().getMonth() + "." + availFromModify.getValue().getYear();
            String availTo = availToModify.getValue().getDayOfMonth()
                    + "." + availToModify.getValue().getMonth() + "." + availToModify.getValue().getYear();
            avail = availFrom + ";" + availTo;

            ModificationsService.addModification(titleModify.getText(), TouristAttractionService.getPhotoTitle(photoModify), avail, descriptModify.getText(), Integer.valueOf(priceModify.getText()));
        }
    }

    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {
        //600 600
        root = FXMLLoader.load(getClass().getClassLoader().getResource("TouristAttractionList.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
