package org.loose.fis.sre.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Offers;
import org.loose.fis.sre.model.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

import java.util.StringTokenizer;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class OfferService {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static ObjectRepository<Offers> offersRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-exemple.db").toFile())
                .openOrCreate("test","test");

        offersRepository = database.getRepository(Offers.class);
    }

    public static void addOffer(String title, String photo, String details) {
        offersRepository.insert(new Offers(title,photo,details));
    }

    public static Offers getOByTitle(String title){
        for (Offers o : OfferService.offersRepository.find()) {
            if (Objects.equals(title, o.getTitle()))
                return o;
        }
        return null;
    }

    public static void addPhoto(String absPath){
        BufferedImage bImage = null;
        try {
            File initialImage = new File(absPath);
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "jpg", new File("images"));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    public static void saveChanges(String title, String photoTitle, String availability, String description, float price){
        Offers toChange = getOByTitle(title);
        toChange.setTitle(title);
        toChange.setDetails(description);
        toChange.setPhoto(photoTitle);

        OfferService.offersRepository.update(toChange);
    }


    public static String getPhotoTitle(String path){
        String title="";

        StringTokenizer st = new StringTokenizer(path, "\\");
        while (st.hasMoreTokens()) {
            title = st.nextToken();
        }

        return title;
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
