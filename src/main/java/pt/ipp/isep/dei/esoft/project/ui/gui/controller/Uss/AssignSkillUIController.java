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
 * @author Group 072 - Byte Masters - ISEP
 */
public class AssignSkillUIController {

    @FXML
    private ListView<String> lvSkills;

    @FXML
    private ListView<String> lvCollaborators;

    private AssignSkillController controller = new AssignSkillController();
    private AssignSkillUI assignSkillUI = new AssignSkillUI();

    /**
     * Initializes the UI components by populating the skills and collaborators list views.
     * This method is typically called when the UI is initialized.
     */

    public void initialize() {

        populateSkillsListView();
        populateCollaboratorsListView();
    }

    /**
     * Populates the skills list view with the available skills.
     */

    private void populateSkillsListView() {
        List<Skill> lstSkills = controller.listSkills();

        for (Skill skill : lstSkills) {
            lvSkills.getItems().add(skill.toString());
        }
    }

    /**
     * Populates the collaborators list view with the available collaborators.
     */

    private void populateCollaboratorsListView() {
        List<Collaborator> lstCollaborators = controller.getCollaborators();

        for (Collaborator collaborator : lstCollaborators) {
            if (!collaborator.getEmail().contains("@hrm.com") && !collaborator.getEmail().contains("@vfm.com") &&
                    !collaborator.getEmail().contains("@gsm.com") && !collaborator.getEmail().contains("@qam.com")) {
                lvCollaborators.getItems().add(collaborator.getName() + " - " + collaborator.getIdDocNumber());
            }
        }
    }

    /**
     * Handles the action of assigning a skill to a collaborator.
     */

    public void handleAssignSkill() {
        if (lvSkills.getSelectionModel().getSelectedItem() == null) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error",
                    "Please select a skill.", "Please select a skill.").show();
        } else if (lvCollaborators.getSelectionModel().getSelectedItem() == null) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error",
                    "Please select a collaborator.", "Please select a collaborator.").show();
        } else {
            try {
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

    }

    /**
     * Handles the action of canceling the skill assignment.
     */

    @FXML
    public void handleCancel() {
        HRMUI hrmui = new HRMUI();
        try {
            hrmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the AssignSkillUI instance for this controller.
     *
     * @param assignSkillUI the AssignSkillUI instance to set
     */

    public void setAssignSkill(AssignSkillUI assignSkillUI) {
        this.assignSkillUI = assignSkillUI;
    }
}
