package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Modifications;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ModificationsService {
    public static ObjectRepository<Modifications> modificationsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-exemple.db").toFile())
                .openOrCreate("test","test");

        modificationsRepository = database.getRepository(Modifications.class);
    }

    public static void addModification(String title, String photo, String details, String text, Integer integer) {
        modificationsRepository.insert(new Modifications(title,photo,details,text,integer));
    }
}
