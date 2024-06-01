package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LoginUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    private MainMenuUI mainMenuUI;
    private LoginUI loginUI;
    private DevTeamUI devTeamUI;

    @FXML
    public Button loginButton;

    @FXML
    public Button viewTeamButton;


    public MainMenuController() {
        this.loginUI = new LoginUI();
        this.devTeamUI = new DevTeamUI();
    }

    public void handleLoginButtonAction() {
        try {
            loginUI.loginAction(MainMenuUI.getPrimaryStage());
        } catch (IOException e) {
            System.out.println("An error occurred while handling the login button action: " + e.getMessage());
        }
    }

    public void handleViewTeamButtonAction() {
        try {
            devTeamUI.viewTeamAction(MainMenuUI.getPrimaryStage());
        } catch (IOException e) {
            System.out.println("An error occurred while handling the view team button action: " + e.getMessage());
        }
    }

    public void setMainMenuUI(MainMenuUI mainMenuUI) {
        this.mainMenuUI = mainMenuUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}