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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {
    private MainMenuUI mainMenuUI;
    private static final String LOGIN = "Login";
    @FXML
    public TextField txtEmail;

    @FXML
    public PasswordField txtPassword;

    @FXML
    public Button btnLogin;

    @FXML
    public Button btnCancel;


    public void loginAction(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void associateParentUI(MainMenuUI mainMenuUI) {
        this.mainMenuUI = mainMenuUI;
    }

}


