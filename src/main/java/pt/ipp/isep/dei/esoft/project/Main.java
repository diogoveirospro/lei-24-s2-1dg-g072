package pt.ipp.isep.dei.esoft.project;

import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.applicationJavaFx.Login;

import static javafx.application.Application.launch;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Main extends javafx.application.Application {

    public static void main(String[] args) throws InvalidCollaboratorDataException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Login loginApp = new Login();
        loginApp.start(primaryStage);
    }
}
