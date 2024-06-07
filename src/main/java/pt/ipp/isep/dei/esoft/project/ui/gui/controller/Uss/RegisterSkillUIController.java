package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterSkillUI;

import java.util.Optional;

public class RegisterSkillUIController {
    private HRMUI hrmui;
    private RegisterSkillUI registerSkillUI;
    private RegisterSkillController registerSkillController = new RegisterSkillController();

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtSkill;

    @FXML
    private Button btnRegister;


    public void handleRegisterAction() {
        String skill = txtSkill.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to Register this skill?");
        alert.setContentText("The data provided will be used to register a skill.");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Skill newskill = new Skill(skill);
                newskill.validateSkill(skill);
                if (skill.isEmpty()) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Please write a skill.", "You need to write a skill to proceed.").show();
                } else {
                    registerSkillController.registerSkill(skill);
                    AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success", "Skill registered successfully.", "The skill was registered successfully.").show();
                    hrmui = new HRMUI();
                    hrmui.showUI(MainMenuUI.getPrimaryStage());
                }
            } catch (Exception e) {
                if (e.getMessage().contains("Skill can't have special characters")){
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering skill. Skill needs to only have letters.", e.getMessage()).show();
                } else if (e.getMessage().contains("This skill already exists " + skill)){
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering skill. Skill already exists.", e.getMessage()).show();
                } else if(e.getMessage().contains("Skill cannot be null")){
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering skill. Skill cannot be null.", e.getMessage()).show();
                } else {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering skill.", e.getMessage()).show();
                }
            }
        }
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
