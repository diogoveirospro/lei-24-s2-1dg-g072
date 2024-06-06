package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.GenerateTeamProposalUI;

import java.io.IOException;
import java.util.List;


public class GenerateTeamProposalUIController {

    @FXML
    public TextField tfMinSize;

    @FXML
    public TextField tfMaxSize;

    @FXML
    public ListView<Skill> lvSkills;

    @FXML
    public ListView<Skill> lvSelectedSkills;

    @FXML
    public Button btnAddSkill;

    @FXML
    public Button btnRemoveSkill;

    @FXML
    public Button btnGenerate;

    @FXML
    public ListView<Collaborator> lvTeamProposal;

    @FXML
    public Button btnAcceptProposal;

    @FXML
    public Button btnDeclineProposal;

    @FXML
    public Button btnAddMember;

    @FXML
    public Button btnRemoveMember;

    @FXML
    public ListView<Collaborator> lvCollaborators;

    GenerateTeamProposalController generateTeamProposalController = new GenerateTeamProposalController();
    GenerateTeamProposalUI generateTeamProposalUI = new GenerateTeamProposalUI();


    @FXML
    public void initialize() {
        lvSkills.setItems(FXCollections.observableArrayList(generateTeamProposalController.listSkills()));

        lvCollaborators.setVisible(false);
        btnAddMember.setVisible(false);
        btnRemoveMember.setVisible(false);
        lvCollaborators.setMouseTransparent(true);

        btnAcceptProposal.setDisable(true);
        btnDeclineProposal.setDisable(true);
    }

    @FXML
    public void handleAddSkill() {
        Skill selectedSkill = lvSkills.getSelectionModel().getSelectedItem();
        if (selectedSkill != null) {
            lvSelectedSkills.getItems().add(selectedSkill);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a skill to add");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleRemoveSkill() {
        Skill selectedSkill = lvSelectedSkills.getSelectionModel().getSelectedItem();
        if (selectedSkill != null) {
            lvSelectedSkills.getItems().remove(selectedSkill);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a skill to remove");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleGenerate() {
        int minSize = Integer.parseInt(tfMinSize.getText());
        int maxSize = Integer.parseInt(tfMaxSize.getText());
        java.util.List<Skill> selectedSkills = lvSelectedSkills.getItems();
        java.util.List<Collaborator> collaborators = Repositories.getInstance().getCollaboratorRepository().getCollaborators();

        try {
            Team teamProposal = generateTeamProposalController.generateTeamProposal(minSize, maxSize, selectedSkills, collaborators);
            lvTeamProposal.setItems(FXCollections.observableArrayList(teamProposal.getTeam()));

            btnAcceptProposal.setDisable(false);
            btnDeclineProposal.setDisable(false);

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    public void handleAcceptProposal() {
        ObservableList<Collaborator> members = lvTeamProposal.getItems();
        Team teamProposal = new Team(members);

        if (generateTeamProposalController.addTeam(teamProposal)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Team proposal accepted");
            alert.showAndWait();

            lvTeamProposal.getItems().clear();

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Team proposal not accepted");
            alert.showAndWait();
        }

    }

    @FXML
    public void handleDeclineProposal() {

        lvCollaborators.setVisible(true);
        btnAddMember.setVisible(true);
        btnRemoveMember.setVisible(true);
        lvCollaborators.setMouseTransparent(false);
    }

    /**
     * Handles the action of canceling and returning to the main UI.
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

    public void setGenerateTeamProposal(GenerateTeamProposalUI generateTeamProposalUI) {
        this.generateTeamProposalUI = generateTeamProposalUI;
    }
}
