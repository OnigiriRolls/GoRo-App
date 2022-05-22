package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TouristAttractionService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.nio.file.Paths;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class TouristAttractionModifyTest {
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ModifyTourist.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }

    @Test
    void testTAModify(FxRobot robot) {
       // TouristAttractionService.addTouristAttraction("Timisoara","photo1","01.03.2022;03.06.2022","abc",700);
       // TouristAttractionService.selectedTitle="Timisoara";

        robot.clickOn("#titleModify");
        robot.write("Arad");
        robot.clickOn("#priceModify");
        robot.write("600");
        robot.clickOn("#descriptModify");
        robot.write("abc");
        robot.clickOn("#availFromModify");
        robot.write("01.01.2022");
        robot.clickOn("#availToModify");
        robot.write("01.10.2022");

        robot.clickOn("#modifyButton");

        assertThat(robot.lookup("#modifyMessage").queryText()).hasText("Empty fields!");

        // robot.sleep(10000);

        //  robot.clickOn("#addButton");
        //  assertThat(robot.lookup("#addMessage").queryText()).hasText("Tourist Attraction added!");

        // robot.clickOn("#addButton");
        // assertThat(robot.lookup("#addMessage").queryText()).hasText("Tourist Attraction already exists! Create a new one!");

    }
}
