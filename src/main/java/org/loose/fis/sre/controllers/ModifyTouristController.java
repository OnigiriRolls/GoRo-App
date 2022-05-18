package org.loose.fis.sre.controllers;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifyTouristController {

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

    void addImage(Image i, StackPane pane){
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
                    System.out.println(file.getAbsolutePath());
                    try {
                        if(!contentPane.getChildren().isEmpty()){
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

    private  void mouseDragOver(final DragEvent e) {
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

    public void handleModify(){
        //save in baza de date, se inchide fxml
        //data.getValue().getDayOfMonth() + "  " + data.getValue().getMonth()+" " + data.getValue().getYear()
    }
}
