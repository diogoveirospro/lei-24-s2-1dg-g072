package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterCollaboratorUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterCollaboratorUI implements Initializable {
    private RegisterCollaboratorUIController controller;
    private static final String REGISTER_JOB = "Register Collaborator";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/RegisterCollaborator.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(REGISTER_JOB);
        stage.setScene(scene);
        controller = loader.getController();
        controller.setRegisterCollaboratorUI(new RegisterCollaboratorUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
