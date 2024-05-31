package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle(ENTERPRISE_NAME);
            primaryStage.setScene(scene);
            LoginUI loginUI = loader.getController();
            loginUI.associateParentUI(this);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertUI.createAnAlert(Alert.AlertType.CONFIRMATION, ENTERPRISE_NAME,
                            "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                }
            });
            primaryStage.show();

        }catch (IOException ex){
            AlertUI.createAnAlert(Alert.AlertType.ERROR, ENTERPRISE_NAME, "Problems in the application startup.", ex.getMessage()).show();
        }
    }


    @FXML
    private void handleLoginButtonAction() {
        LoginUI loginUI = new LoginUI();
        loginUI.loginAction();
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
            e.printStackTrace();
            AlertUI.createAnAlert(Alert.AlertType.ERROR, ENTERPRISE_NAME, "Problems in the application startup.", e.getMessage()).show();
        }

    }




    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
}