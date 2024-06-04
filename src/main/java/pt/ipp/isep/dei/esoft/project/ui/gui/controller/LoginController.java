package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.*;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField txtEmail;

    @FXML
    public PasswordField txtPassword;

    @FXML
    public Button btnLogin;

    @FXML
    public Button btnCancel;

    private HRMUI hrmUI;
    private GSMUI gsmUI;
    private CollaboratorUI collaboratorUI;
    private FMUI fmUI;
    private LoginUI loginUI;
    private MainMenuUI mainMenuUI;

    public void handleLoginButtonAction() {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        try {
            if (email.contains("@hrm.com")) {
                hrmUI = new HRMUI();
                hrmUI.showUI(MainMenuUI.getPrimaryStage());
            } else if (email.contains("@gsm.com")) {
                gsmUI = new GSMUI();
                gsmUI.showUI(MainMenuUI.getPrimaryStage());
            } else if (email.contains("@collaborator.com")) {
                collaboratorUI = new CollaboratorUI();
                collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
            } else if (email.contains("@vfm.com")) {
                fmUI = new FMUI();
                fmUI.showUI(MainMenuUI.getPrimaryStage());
            }
        } catch (Exception e) {
            System.out.println("An error occurred while handling the login action: " + e.getMessage());
        }

    }
    public void handleCancelButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

}
