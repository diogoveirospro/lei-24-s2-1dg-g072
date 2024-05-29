package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.MainMenuController;

import java.io.IOException;

public class MainMenuUI extends Application {
    private MainMenuController mainMenuController;
    @FXML
    private Button loginButton;

    @FXML
    private Button viewTeamButton;

    private static final String ENTERPRISE_NAME = "Musgo Sublime";
    private static final String LOGIN = "Login";
    private static final String DEV_TEAM = "Development Team";

    public void start(Stage primaryStage) throws IOException {
        initialize(primaryStage);
    }

    public  void  initialize(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            mainMenuController = new MainMenuController();
            primaryStage.show();
        }catch (IOException ex){
            AlertUI.createAnAlert(Alert.AlertType.ERROR, ENTERPRISE_NAME, "Erro.", ex.getMessage());
        }
    }

    @FXML
    private void handleLoginButtonAction() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, MainMenuUI.ENTERPRISE_NAME, "Error.", e.getMessage()).show();
        }


    }

    @FXML
    private void handleViewTeamButtonAction() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, MainMenuUI.ENTERPRISE_NAME, "Error.", e.getMessage()).show();
        }


    }



    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
}