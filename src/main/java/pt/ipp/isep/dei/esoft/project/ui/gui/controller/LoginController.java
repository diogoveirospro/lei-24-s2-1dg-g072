package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.*;

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
    private MainMenuUI mainMenuUI;
    private AuthenticationController authController = new AuthenticationController();
    private LoginUI loginUI = new LoginUI();

    public void handleLoginButtonAction() {
        authController.logout();

        String email = txtEmail.getText();
        String password = txtPassword.getText();
        try {
            if (authController.login(email, password)) {
                String role = authController.getAuthenticatedUserRole();
                switch (role) {
                    case "HRM":
                        hrmUI = new HRMUI();
                        hrmUI.showUI(MainMenuUI.getPrimaryStage());
                        break;
                    case "GSM":
                        gsmUI = new GSMUI();
                        gsmUI.showUI(MainMenuUI.getPrimaryStage());
                        break;
                    case "COLLABORATOR":
                        collaboratorUI = new CollaboratorUI();
                        collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
                        break;
                    case "VFM":
                        fmUI = new FMUI();
                        fmUI.showUI(MainMenuUI.getPrimaryStage());
                        break;
                    default:
                        throw new Exception("Invalid role.");
                }
            } else {
                showLoginError();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "An error occurred while handling the login action: " + e.getMessage());
        }
    }

    public void handleCancelButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Cancel Error", "An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    private void showLoginError() {
        txtEmail.clear();
        txtPassword.clear();
        showAlert(Alert.AlertType.ERROR, "Login Error", "The email or password is incorrect. Please try again.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
