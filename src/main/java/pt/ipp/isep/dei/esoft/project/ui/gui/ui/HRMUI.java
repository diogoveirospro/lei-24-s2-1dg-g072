package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.HRMController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HRMUI implements Initializable {
    public MainMenuUI mainMenuUI;
    public HRMController hrmController;
    public static final String HRM = "HRM";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HRMUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(HRM);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setScene(scene);
        hrmController = loader.getController();
        hrmController.setHrmui(new HRMUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public HRMController getHrmController() {
        return hrmController;
    }
}
