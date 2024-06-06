package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.MainMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuUI extends Application {
    private MainMenuController mainMenuController;

    private static Stage primaryStage;
    private static final String ENTERPRISE_NAME = "Musgo Sublime";

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadMainMenu();
    }

    public void loadMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(ENTERPRISE_NAME);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            mainMenuController = loader.getController();
            mainMenuController.setMainMenuUI(new MainMenuUI());
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(ENTERPRISE_NAME);
                    alert.setHeaderText("Confirmation of action.");
                    alert.setContentText("Do you really want to close the application?");

                    ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(yesButton, noButton);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == noButton) {
                        event.consume();
                    }
                }
            });
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            AlertUI.createAnAlert(Alert.AlertType.ERROR, ENTERPRISE_NAME, "Problems in the application startup.", ex.getMessage()).show();
        }
    }



    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}