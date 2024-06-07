package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterSkillUI;

public class RegisterSkillUIController {
    private HRMUI hrmui;
    private RegisterSkillUI registerSkillUI;
    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtSkill;

    @FXML
    private Button btnRegister;


    public void handleRegisterAction() {
        // TODO implement here
    }


    public void handleCancelAction() {
        try {
            hrmui = new HRMUI();
            hrmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setRegisterSkillUI(RegisterSkillUI registerSkillUI) {
        this.registerSkillUI = registerSkillUI;
    }
}
