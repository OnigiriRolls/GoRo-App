package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.controllers.AddTouristController;
import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class TouristAttractionAddTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.APPLICATION_HOME_PATH = Paths.get(FileSystemService.USER_FOLDER, FileSystemService.APPLICATION_FOLDER);
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addAttraction.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @AfterEach
    public void tearDown() {
        UserService.closeDatabase();
    }

    @Test
    void testTAAdding(FxRobot robot) {
        robot.clickOn("#titleAdd");
        robot.write("Arad");
        robot.clickOn("#priceAdd");
        robot.write("600");
        robot.clickOn("#descriptAdd");
        robot.write("abc");
        robot.clickOn("#availFromAdd");
        robot.write("01.01.2022");
        robot.clickOn("#availToAdd");
        robot.write("01.10.2022");

        robot.clickOn("#addButton");

        assertThat(robot.lookup("#addMessage").queryText()).hasText("Empty fields!");

      // robot.sleep(10000);

       // robot.clickOn("#addButton");
        //assertThat(robot.lookup("#addMessage").queryText()).hasText("Tourist Attraction added!");

      //  robot.clickOn("#addButton");
       // assertThat(robot.lookup("#addMessage").queryText()).hasText("Tourist Attraction already exists! Create a new one!");
    }
}
