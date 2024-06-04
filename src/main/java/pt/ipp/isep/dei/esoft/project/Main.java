package pt.ipp.isep.dei.esoft.project;

import javafx.application.Application;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;

import static javafx.application.Application.launch;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Main extends Application {

    public static void main(String[] args) throws InvalidCollaboratorDataException, InvalidTaskDataException, InvalidEntryDataException, InvalidGreenSpaceDataException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainMenuUI mainMenuUI = new MainMenuUI();
        mainMenuUI.start(primaryStage);
    }
}
