package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Offers;

import java.util.StringTokenizer;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class OfferService {

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

    public static String getPhotoTitle(String path){
        String title="";

        StringTokenizer st = new StringTokenizer(path, "\\");
        while (st.hasMoreTokens()) {
            title = st.nextToken();
        }

        return title;
    }
}
