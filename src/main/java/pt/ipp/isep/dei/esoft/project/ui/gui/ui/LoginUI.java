package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.LoginController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.MainMenuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {
    public LoginController loginController;
    public MainMenuUI mainMenuUI;
    public static final String LOGIN = "Login";


    public void loginAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        loginController = loader.getController();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public LoginController getLoginController() {
        return loginController;
    }
}


