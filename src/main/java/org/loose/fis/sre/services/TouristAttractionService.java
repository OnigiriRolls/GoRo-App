package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.model.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class TouristAttractionService {

    public static ObjectRepository<TouristAttractions> attractionsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        attractionsRepository = database.getRepository(TouristAttractions.class);
    }

    public static void saveChanges(String title, String photoTitle, String availability, String description, float price){
        attractionsRepository.insert(new TouristAttractions(title, photoTitle, availability, description, price));
    }

    public static void deleteAttraction(String title){
        attractionsRepository.remove(getTAByTitle(title));
    }

    public static TouristAttractions getTAByTitle(String title){
        for (TouristAttractions ta : attractionsRepository.find()) {
            if (Objects.equals(title, ta.getTitle()))
               return ta;
        }
        return null;
    }

    public static void addPhoto(String absPath){
        BufferedImage bImage = null;
        try {
            File initialImage = new File(absPath);
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "jpg", new File("src/main/resources/images"));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    public static String getPhotoTitle(String path){
        String title="";

        StringTokenizer st = new StringTokenizer(path, "\\");
        while (st.hasMoreTokens()) {
            title = st.nextToken();
        }

        return title;
    }
/*
    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException, PasswordNotOkException {
        checkUserDoesNotAlreadyExist(username);
        checkPassword(password);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

 */
}
