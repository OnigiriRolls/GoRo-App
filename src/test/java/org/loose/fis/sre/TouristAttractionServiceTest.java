package org.loose.fis.sre;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.model.TouristAttractions;
import org.loose.fis.sre.model.User;
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
public class TouristAttractionServiceTest  {

    @BeforeEach
    void start() throws IOException {

        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.APPLICATION_HOME_PATH = Paths.get(FileSystemService.USER_FOLDER, FileSystemService.APPLICATION_FOLDER);
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    public void tearDown() {
        UserService.closeDatabase();
    }

    @Test
    @DisplayName("Attraction is successfully persisted to Database")
    public void testAttractionIsAddedToDatabase() {

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
    public void testAttractionIsFoundByTitle(){

        TouristAttractionService.addTouristAttraction("Arad","photo1","1.may.2022;5.may.2022",
                "abc",500);
        assertThat(TouristAttractionService.getTAByTitle("Arad").getTitle().equals("Arad"));
    }

    @Test
    @DisplayName("Attraction is successfully deleted from Database")
    public void testAttractionIsDeletedFromDatabase() {

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
    public void testAttractionIsModified() {

        TouristAttractionService.addTouristAttraction("Timisoara", "photo1", "1.may.2022;5.may.2022",
                "abc", 500);
        assertThat(TouristAttractionService.getAllUsers()).isNotEmpty();
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(1);
        TouristAttractionService.selectedTitle="Timisoara";
        TouristAttractionService.saveChanges("Bucuresti","photo2","3.may.2022;5.may.2022","abcd",600);
        assertThat(TouristAttractionService.getAllUsers()).isNotEmpty();
        assertThat(TouristAttractionService.getAllUsers()).size().isEqualTo(1);
        assertThat(TouristAttractionService.getAllUsers().get(0).getTitle().equals("Arad"));
        assertThat(TouristAttractionService.getAllUsers().get(0).getPhotoTitle().equals("photo2"));
        assertThat(TouristAttractionService.getAllUsers().get(0).getAvailability().equals("3.may.2022;5.may.2022"));
        assertThat(TouristAttractionService.getAllUsers().get(0).getDescription().equals("abcd"));
        assertThat(TouristAttractionService.getAllUsers().get(0).getPrice()==600);
    }
}
