package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterJobUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterSkillUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterJobUI implements Initializable {
    private RegisterJobUIController controller;
    private static final String REGISTER_JOB = "Register Job";
    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/RegisterJob.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(REGISTER_JOB);
        stage.setScene(scene);
        controller = loader.getController();
        controller.setRegisterJobUI(new RegisterJobUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
