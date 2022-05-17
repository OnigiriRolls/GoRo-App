package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.TouristAttractions;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class TouristAttractionService {

    public static ObjectRepository<TouristAttractions> attractionsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        attractionsRepository = database.getRepository(TouristAttractions.class);
    }
}
