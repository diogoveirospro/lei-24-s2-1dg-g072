package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.CollaboratorController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.HRMController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollaboratorUI implements Initializable {
    public MainMenuUI mainMenuUI;
    public CollaboratorController collaboratorController;
    public static final String GSM = "GSM";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollaboratorUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(GSM);
        stage.setScene(scene);
        collaboratorController = loader.getController();
        collaboratorController.setCollaboratorUI(new CollaboratorUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public CollaboratorController getCollaboratorController() {
        return collaboratorController;
    }


}
