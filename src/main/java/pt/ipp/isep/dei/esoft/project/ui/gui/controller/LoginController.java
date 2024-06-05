package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
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
    private AuthenticationController ctrl = new AuthenticationController();


    public void handleLoginButtonAction() {
        ctrl.doLogout();

        String email = txtEmail.getText();
        String password = txtPassword.getText();
        if (ctrl.doLogin(email, password)) {
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
        } else {
            txtEmail.clear();
            txtPassword.clear();
            Alert al = AlertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Slime","Login Error", "The email or password is incorrect. Please try again.");
            al.showAndWait();
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
