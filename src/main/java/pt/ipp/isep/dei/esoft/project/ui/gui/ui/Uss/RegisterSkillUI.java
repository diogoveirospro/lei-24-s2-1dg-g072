package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterSkillUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 *
 * @author Group 072 - Byte Masters - ISEP
 */

public class RegisterSkillUI implements Initializable {

    private RegisterSkillUIController registerSkillUIController;
    private final String REGISTER_SKILL = "Register Skill";
    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/RegisterSkill.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(REGISTER_SKILL);
        stage.setScene(scene);
        registerSkillUIController = loader.getController();
        registerSkillUIController.setRegisterSkillUI(new RegisterSkillUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
