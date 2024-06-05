package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.*;

public class HRMController {
    public HRMUI hrmui;
    public RegisterSkillUI registerSkillUI;
    public RegisterCollaboratorUI registerCollaboratorUI;
    public MainMenuUI mainMenuUI;
    public RegisterJobUI registerJobUI;
    public GenerateTeamProposalUI generateTeamProposalUI;
    public AssignSkillUI assignSkillUI;

    @FXML
    public Button btnRegisterSkill;

    @FXML
    public Button btnRegisterCollaborator;

    @FXML
    public Button btnRegisterJob;

    @FXML
    public Button btnGenerateTeamProposal;

    @FXML
    public Button btnAssignSkills;

    @FXML
    public Button btnLogout;

    public void setHrmui(HRMUI hrmui) {
        this.hrmui = hrmui;
    }

    public void handleLogoutButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void handleRegisterSkillButtonAction() {
        try {
            registerSkillUI = new RegisterSkillUI();
            registerSkillUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the register skill action: " + e.getMessage());
        }
    }

    public void handleRegisterCollaboratorButtonAction() {
        try {
            registerCollaboratorUI = new RegisterCollaboratorUI();
            registerCollaboratorUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the register collaborator action: " + e.getMessage());
        }
    }

    public void handleRegisterJobButtonAction() {
        try {
            registerJobUI = new RegisterJobUI();
            registerJobUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the register job action: " + e.getMessage());
        }
    }

    public void handleGenerateTeamProposalButtonAction() {
        try {
            generateTeamProposalUI = new GenerateTeamProposalUI();
            generateTeamProposalUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the generate team proposal action: " + e.getMessage());
        }
    }

    public void handleAssignSkillsButtonAction() {
        try {
            assignSkillUI = new AssignSkillUI();
            assignSkillUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the assign skill action: " + e.getMessage());
        }
    }
}
