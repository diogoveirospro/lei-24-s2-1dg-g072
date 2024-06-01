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
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.MainMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuUI extends Application {
    private MainMenuController mainMenuController;


    private static final String ENTERPRISE_NAME = "Musgo Sublime";

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);
            stage.setTitle(ENTERPRISE_NAME);
            stage.setScene(scene);
            mainMenuController = loader.getController();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAnAlert(Alert.AlertType.CONFIRMATION, ENTERPRISE_NAME,
                            "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            stage.show();

        }catch (IOException ex){
            ex.printStackTrace(System.out);
            AlertUI.createAnAlert(Alert.AlertType.ERROR, ENTERPRISE_NAME, "Problems in the application startup.", ex.getMessage()).show();
        }
    }


    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }


}