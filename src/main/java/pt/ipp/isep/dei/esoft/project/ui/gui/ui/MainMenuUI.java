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

public class MainMenuUI extends Application implements Initializable {
    private MainMenuController mainMenuController;
    @FXML
    public Button loginButton;

    @FXML
    public Button viewTeamButton;

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
            MainMenuUI loginUI = loader.getController();
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
            ex.printStackTrace(System.out);
            AlertUI.createAnAlert(Alert.AlertType.ERROR, ENTERPRISE_NAME, "Problems in the application startup.", ex.getMessage()).show();
        }
    }


    @FXML
    public void handleLoginButtonAction() {
        LoginUI loginUI = new LoginUI();
        loginUI.loginAction();
    }

    @FXML
    public void handleViewTeamButtonAction() {
        DevTeamUI devTeamUI = new DevTeamUI();
        devTeamUI.viewTeamAction();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}