package pt.ipp.isep.dei.esoft.project.ui.gui.applicationJavaFx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Login loginApp = new Login();
        loginApp.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
