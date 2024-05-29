package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler; // Correct import
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.MainMenuController;

import java.io.IOException;

public class MainMenuUI {
    public static final String INTERPRISE_NAME = "MusgoSublime";
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(scene);

            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAnAlert(Alert.AlertType.CONFIRMATION, INTERPRISE_NAME,
                            "Confirm Action", "Do you want to stop the application.");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        MainMenuController mainMenuController = ((JanelaPrincipalUI) loader.getController()).getMainMenuController();
                    }
                }
            });
            primaryStage.show();
        }catch (IOException ex) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, INTERPRISE_NAME,
                    "Problemas no arranque da aplicação.", ex.getMessage()).show();
        }
    }
}