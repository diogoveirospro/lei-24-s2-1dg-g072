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
            if (email.equals("hrm@this.app") && password.equals("hrm")) {
                hrmUI.showUI();
            } else if (email.equals("gsm@this.app") && password.equals("gsm")) {
                gsmUI.showUI();
            } else if (email.equals("collaborator@this.app") && password.equals("collaborator@this.app")) {
                collaboratorUI.showUI();
            } else if (email.equals("vfm@this.app") && password.equals("vfm@this.app")) {
                fmUI.showUI();
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
