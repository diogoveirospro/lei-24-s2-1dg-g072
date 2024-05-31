package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {
    private MainMenuUI mainMenuUI;
    private static final String LOGIN = "Login";
    public void loginAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, LOGIN, "Error.", e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void associateParentUI(MainMenuUI mainMenuUI) {
        this.mainMenuUI = mainMenuUI;
    }
}


