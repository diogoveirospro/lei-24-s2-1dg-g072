package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DevTeamController implements Initializable{
    public DevTeamUI devTeamUI;
    private MainMenuUI mainMenuUI;
    @FXML
    public Button goBackButton;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtName1;
    @FXML
    public TextField txtEmail1;
    @FXML
    public TextField txtName2;
    @FXML
    public TextField txtEmail2;
    @FXML
    public TextField txtName3;
    @FXML
    public TextField txtEmail3;
    @FXML
    public TextField txtName4;
    @FXML
    public TextField txtEmail4;
    @FXML
    public TextField txtName5;
    @FXML
    public TextField txtEmail5;

    public void handleGoBackButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the go back action: " + e.getMessage());
        }
    }

    public void setDevTeamUI(DevTeamUI devTeamUI) {
        this.devTeamUI = devTeamUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
