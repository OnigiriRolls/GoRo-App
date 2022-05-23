package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.exceptions.PasswordNotOkException;
import org.loose.fis.sre.exceptions.UserDoesNotExistException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
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
class LogInTest {

    public static String USERNAME = "user";
    public static String PASSWORD = "Passs";

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    @AfterEach
    public void tearDown() {
        UserService.closeDatabase();
    }

    @Test
    void testRegistration(FxRobot robot) throws UsernameAlreadyExistsException, PasswordNotOkException {

        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);


        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#loginButton");

        Throwable exception = assertThrows(UserDoesNotExistException.class, () -> UserService.checkPassAndUsername(USERNAME));
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(exception.getMessage());


        UserService.addUser("user","Passs","Client");

        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");

        /*assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#loginButton");*/
        //assertThat(robot.lookup("#loginMessage").queryText()).hasText(
        //        String.format("An account with the username %s does not exist!\nTry registering in instead!", USERNAME)
        //);

        /*robot.clickOn("#username");
        robot.write("1");*/
        //robot.clickOn("#registerButton");

        //assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Register successfully!");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
    }
}