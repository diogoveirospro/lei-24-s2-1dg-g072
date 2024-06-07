package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AssignSkillUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class AssignSkillUI implements Initializable {

    public static final String ASSIGNSKILL = "Assign Skill to Collaborator";
    public AssignSkillUIController assignSkillUIController;


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AssignSkills.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(ASSIGNSKILL);
        stage.setScene(scene);
        assignSkillUIController = loader.getController();
        assignSkillUIController.setAssignSkill(new AssignSkillUI());
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
