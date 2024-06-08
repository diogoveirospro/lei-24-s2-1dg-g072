package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AddAgendaEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AssignSkillUI;

import java.io.IOException;
import java.util.List;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class AssignSkillUIController {

    @FXML
    private ListView<String> lvSkills;

    @FXML
    private ListView<String> lvCollaborators;

    private AssignSkillController controller = new AssignSkillController();
    private AssignSkillUI assignSkillUI = new AssignSkillUI();

    public void initialize() {

        populateSkillsListView();
        populateCollaboratorsListView();
    }

    private void populateSkillsListView() {
        List<Skill> lstSkills = controller.listSkills();

        for (Skill skill : lstSkills) {
            lvSkills.getItems().add(skill.toString());
        }
    }

    private void populateCollaboratorsListView() {
        List<Collaborator> lstCollaborators = controller.getCollaborators();

        for (Collaborator collaborator : lstCollaborators) {
            if (!collaborator.getEmail().contains("@hrm.com") && !collaborator.getEmail().contains("@vfm.com") &&
                    !collaborator.getEmail().contains("@gsm.com") && !collaborator.getEmail().contains("@qam.com")) {
                lvCollaborators.getItems().add(collaborator.getName() + " - " + collaborator.getIdDocNumber());
            }
        }
    }

    public void handleAssignSkill() {
        String skill = lvSkills.getSelectionModel().getSelectedItem();
        String collaborator = lvCollaborators.getSelectionModel().getSelectedItem();

        if (skill == null && collaborator == null) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error",
                    "Please select a skill and a collaborator.", "Please select a skill and a collaborator.");
        }

        assert collaborator != null;
        String[] collaboratorParts = collaborator.split(" - ");
        String collaboratorIDNumber = collaboratorParts[1];

        Skill skillObj = controller.getSkill(skill);
        Collaborator collaboratorObj = controller.getCollaborator(collaboratorIDNumber);

        try {
            controller.assignSkill(skillObj, collaboratorObj);
            AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success",
                    "Skill assigned successfully.", "Skill assigned successfully.").show();
            HRMUI hrmui = new HRMUI();
            hrmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (IllegalArgumentException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error",
                    "Error assigning skill.", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void handleCancel() {
        HRMUI hrmui = new HRMUI();
        try {
            hrmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAssignSkill (AssignSkillUI assignSkillUI) {
        this.assignSkillUI = assignSkillUI;
    }
}
