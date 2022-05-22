package org.loose.fis.sre;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TouristAttractionService;
import org.loose.fis.sre.services.UserService;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class TouristAttractionServiceTest {


    @BeforeAll
    static void start() throws IOException {
        System.out.println("Start");
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.APPLICATION_HOME_PATH = Paths.get(FileSystemService.USER_FOLDER, FileSystemService.APPLICATION_FOLDER);
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }


    @Test
    @DisplayName("Attraction is successfully persisted to Database")
    void testAttractionIsAddedToDatabase() {
        TouristAttractionService.addTouristAttraction("Cluj","photo1","1.may.2022;5.may.2022",
                "abc",500);
        assertThat(TouristAttractionService.getAllUsers()).isNotEmpty();
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(1);
        TouristAttractions ta = TouristAttractionService.getAllUsers().get(0);
        assertThat(ta).isNotNull();
        assertThat(ta.getTitle()).isEqualTo("Cluj");
        assertThat(ta.getPhotoTitle()).isEqualTo("photo1");
        assertThat(ta.getAvailability()).isEqualTo("1.may.2022;5.may.2022");
        assertThat(ta.getDescription()).isEqualTo("abc");
        assertThat(ta.getPrice()).isEqualTo(500);
    }

    @Test
    @DisplayName("Attraction is successfully found in Database")
    void testAttractionIsFoundByTitle(){
        TouristAttractionService.addTouristAttraction("Arad","photo1","1.may.2022;5.may.2022",
                "abc",500);
        assertThat(TouristAttractionService.getTAByTitle("Arad").getTitle().equals("Arad"));
    }

    @Test
    @DisplayName("Attraction is successfully deleted from Database")
    void testAttractionIsDeletedFromDatabase() {
        TouristAttractionService.addTouristAttraction("Cluj", "photo1", "1.may.2022;5.may.2022",
                "abc", 500);
        TouristAttractionService.addTouristAttraction("Arad", "photo1", "1.may.2022;5.may.2022",
                "abc", 500);
        assertThat(TouristAttractionService.getAllUsers()).isNotEmpty();
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(2);
        TouristAttractionService.deleteAttraction("Cluj");
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(1);
        TouristAttractionService.deleteAttraction("Arad");
        assertThat(TouristAttractionService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("Attraction is successfully modified")
    void testAttractionIsModified() {
        TouristAttractionService.addTouristAttraction("Timisoara", "photo1", "1.may.2022;5.may.2022",
                "abc", 500);
        assertThat(TouristAttractionService.getAllUsers()).isNotEmpty();
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(3);
        TouristAttractionService.selectedTitle="Timisoara";
        TouristAttractionService.saveChanges("Bucuresti","photo2","3.may.2022;5.may.2022","abcd",600);
        assertThat(TouristAttractionService.getAllUsers()).isNotEmpty();
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(3);
        assertThat(TouristAttractionService.getAllUsers().get(2).getTitle().equals("Arad"));
        assertThat(TouristAttractionService.getAllUsers().get(2).getPhotoTitle().equals("photo2"));
        assertThat(TouristAttractionService.getAllUsers().get(2).getAvailability().equals("3.may.2022;5.may.2022"));
        assertThat(TouristAttractionService.getAllUsers().get(2).getDescription().equals("abcd"));
        assertThat(TouristAttractionService.getAllUsers().get(2).getPrice()==600);
    }
}
