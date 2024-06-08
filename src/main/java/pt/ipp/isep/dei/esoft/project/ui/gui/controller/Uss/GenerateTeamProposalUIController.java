package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.GenerateTeamProposalUI;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class GenerateTeamProposalUIController implements Initializable {

    @FXML
    public TextField tfMinSize;

    @FXML
    public TextField tfMaxSize;

    @FXML
    public ListView<String> lvSkills;

    @FXML
    public ListView<String> lvSelectedSkills;

    @FXML
    public Button btnAddSkill;

    @FXML
    public Button btnRemoveSkill;

    @FXML
    public Button btnGenerate;

    @FXML
    public ListView<String> lvTeamProposal;

    @FXML
    public Button btnAcceptProposal;

    @FXML
    public Button btnDeclineProposal;

    @FXML
    public Button btnAddMember;

    @FXML
    public Button btnRemoveMember;

    @FXML
    public ListView<String> lvCollaborators;

    GenerateTeamProposalController generateTeamProposalController = new GenerateTeamProposalController();
    GenerateTeamProposalUI generateTeamProposalUI = new GenerateTeamProposalUI();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateSkillsListView();

        lvCollaborators.setVisible(false);
        btnAddMember.setVisible(false);
        btnRemoveMember.setVisible(false);
        lvCollaborators.setMouseTransparent(true);

        btnAcceptProposal.setDisable(true);
        btnDeclineProposal.setDisable(true);
    }

    private void populateSkillsListView() {
        List<Skill> lstSkills = generateTeamProposalController.listSkills();

        for (Skill skill : lstSkills) {
            lvSkills.getItems().add(skill.getName());
        }
    }

    private void populateCollaboratorsListView() {
        List<Collaborator> lstCollaborators = generateTeamProposalController.getCollaborators();
        List<String> teamProposalString = lvTeamProposal.getItems();

        for (String member : teamProposalString) {
            String[] memberParts = member.split(" - ");
            String memberIDNumber = memberParts[1];
            for (Collaborator collaborator : lstCollaborators) {
                if (collaborator.getIdDocNumber().equals(memberIDNumber)) {
                    lstCollaborators.remove(collaborator);
                    break;
                }
            }
        }

        for(Collaborator collaborator : lstCollaborators){
            if(collaborator.hasTeam()){
                lstCollaborators.remove(collaborator);
            }
        }

        for (Collaborator collaborator : lstCollaborators) {

            lvCollaborators.getItems().add(collaborator.getName() + " - " + collaborator.getIdDocNumber());

        }
    }


    public void handleAddSkill() {
        Skill selectedSkill = generateTeamProposalController.getSkill(lvSkills.getSelectionModel().getSelectedItem());
        if (selectedSkill != null) {
            lvSelectedSkills.getItems().add(selectedSkill.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a skill to add");
            alert.showAndWait();
        }
    }


    public void handleRemoveSkill() {
        Skill selectedSkill = generateTeamProposalController.getSkill(lvSelectedSkills.getSelectionModel().getSelectedItem());
        if (selectedSkill != null) {
            lvSelectedSkills.getItems().remove(selectedSkill.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a skill to remove");
            alert.showAndWait();
        }
    }


    public void handleGenerate() {

        if (tfMaxSize.getText().isEmpty() || tfMinSize.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please write the minimum and maximum size of the team");
            alert.showAndWait();
        } else {
            int minSize = Integer.parseInt(tfMinSize.getText());
            int maxSize = Integer.parseInt(tfMaxSize.getText());
            List<String> selectedSkillsString = lvSelectedSkills.getItems();
            List<Collaborator> collaborators = generateTeamProposalController.getCollaborators();
            if (minSize > maxSize) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Minimum size must be less than maximum size");
                alert.showAndWait();

            } else if (selectedSkillsString.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select at least one skill");
                alert.showAndWait();
            } else if (collaborators.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select at least one collaborator");
                alert.showAndWait();
            } else {
                try {

                    List<Skill> selectedSkills = new ArrayList<>();
                    List<String> teamProposalString = new ArrayList<>();

                    for (String skill : selectedSkillsString) {
                        selectedSkills.add(generateTeamProposalController.getSkill(skill));
                    }

                    Team teamProposal = generateTeamProposalController.generateTeamProposal(minSize, maxSize, selectedSkills, collaborators);

                    for (Collaborator collaborator : teamProposal.getTeam()) {
                        teamProposalString.add(collaborator.getName() + " - " + collaborator.getIdDocNumber());
                    }

                    lvTeamProposal.setItems(FXCollections.observableArrayList(teamProposalString));

                    btnAcceptProposal.setDisable(false);
                    btnDeclineProposal.setDisable(false);

                } catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                }
            }
        }

    }

    public void handleAcceptProposal() {
        List<String> membersString = lvTeamProposal.getItems();
        List<Collaborator> members = new ArrayList<>();

        for (String member : membersString) {
            String[] memberParts = member.split(" - ");
            String memberIDNumber = memberParts[1];
            for (Collaborator collaborator : generateTeamProposalController.getCollaborators()) {
                if (collaborator.getIdDocNumber().equals(memberIDNumber)) {
                    members.add(collaborator);
                    break;
                }
            }
        }

        Team teamProposal = new Team(members);

        if (members.size() < Integer.parseInt(tfMinSize.getText()) || members.size() > Integer.parseInt(tfMaxSize.getText())) {

            Alert alert = AlertUI.createAnAlert(Alert.AlertType.CONFIRMATION, "Error",
                    "Team size must be between the minimum and maximum size", "Are you sure you want to create this team?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                return;
            }
        }

        List<Skill> selectedSkills = new ArrayList<>();

        for (String skill : lvSelectedSkills.getItems()) {
            selectedSkills.add(generateTeamProposalController.getSkill(skill));
        }

        boolean hasSkill = true;

        for (Skill skill : selectedSkills) {
            for (Collaborator collaborator : members) {
                if (!collaborator.getSkillSet().contains(skill)) {
                    hasSkill = false;
                }
            }
        }

        if (!hasSkill) {
            Alert alert = AlertUI.createAnAlert(Alert.AlertType.CONFIRMATION, "Error",
                    "Not all members have the required skills", "Are you sure you want to create this team?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                return;
            }
        }


        if (generateTeamProposalController.addTeam(teamProposal)) {

            AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success",
                    "Team proposal accepted", "Team proposal accepted").show();

            for (Collaborator collaborator : members) {
                collaborator.setHasTeam(true);
            }

            lvTeamProposal.getItems().clear();
            lvCollaborators.getItems().clear();
            lvCollaborators.setVisible(false);
            btnAddMember.setVisible(false);
            btnRemoveMember.setVisible(false);
            lvCollaborators.setMouseTransparent(true);

            tfMaxSize.clear();
            tfMinSize.clear();
            lvSelectedSkills.getItems().clear();

            btnAcceptProposal.setDisable(true);
            btnDeclineProposal.setDisable(true);

        } else {

            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error",
                    "Team proposal not accepted", "Team proposal not accepted").show();
        }

    }

    public void handleDeclineProposal() {

        populateCollaboratorsListView();

        lvCollaborators.setVisible(true);
        btnAddMember.setVisible(true);
        btnRemoveMember.setVisible(true);
        lvCollaborators.setMouseTransparent(false);
    }


    public void handleAddMember() {
        String selectedCollaborator = lvCollaborators.getSelectionModel().getSelectedItem();
        if (selectedCollaborator != null) {
            lvTeamProposal.getItems().add(selectedCollaborator);
            lvCollaborators.getItems().remove(selectedCollaborator);
            Collaborator collaborator = generateTeamProposalController.getCollaborator(selectedCollaborator.split(" - ")[1]);
            collaborator.setHasTeam(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a collaborator to add");
            alert.showAndWait();
        }
    }

    public void handleRemoveMember() {
        String selectedCollaborator = lvTeamProposal.getSelectionModel().getSelectedItem();
        if (selectedCollaborator != null) {
            lvCollaborators.getItems().add(selectedCollaborator);
            lvTeamProposal.getItems().remove(selectedCollaborator);
            Collaborator collaborator = generateTeamProposalController.getCollaborator(selectedCollaborator.split(" - ")[1]);
            collaborator.setHasTeam(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a collaborator to remove");
            alert.showAndWait();
        }
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
