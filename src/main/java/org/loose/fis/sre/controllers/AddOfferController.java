package org.loose.fis.sre.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.TAAlreadyExistsException;
import org.loose.fis.sre.model.Offers;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.OfferService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddOfferController {
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
    private TextField titleAdd;
    @FXML
    private TextArea descriptAdd;
    @FXML
    private Text addMessage;

    private String photoAdd;

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
                    System.out.println(file.getAbsolutePath());
                    photoAdd = file.getAbsolutePath();

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

    public void handleAddOffer() {
            //TouristAttractionService.addPhoto(photoAdd);
                OfferService.addOffer(titleAdd.getText(), OfferService.getPhotoTitle(photoAdd), descriptAdd.getText());
                addMessage.setText("Ofertă adaugata!");
    }
    public void handleBack(javafx.event.ActionEvent actionEvent) throws IOException {
        //600 600
        root = FXMLLoader.load(getClass().getClassLoader().getResource("adminMenu.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
