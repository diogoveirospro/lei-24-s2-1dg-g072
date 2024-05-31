package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GenerateTeamProposalUI {

    @FXML
    private TextField tfMinSize;

    @FXML
    private TextField tfMaxSize;

    @FXML
    private ListView<Skill> lvSkills;

    @FXML
    private ListView<Skill> lvSelectedSkills;

    @FXML
    private Button btnAddSkill;

    @FXML
    private Button btnRemoveSkill;

    @FXML
    private Button btnGenerate;

    @FXML
    private ListView<Collaborator> lvTeamProposal;

    @FXML
    private Button btnAcceptProposal;

    @FXML
    private Button btnDeclineProposal;

    @FXML
    private Button btnAddMember;

    @FXML
    private Button btnRemoveMember;

    @FXML
    private ListView<Collaborator> lvCollaborators;

    private final GenerateTeamProposalController generateTeamProposalController;

    public GenerateTeamProposalUI() {
        this.generateTeamProposalController = new GenerateTeamProposalController();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GenerateTeamProposal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Generate Team Proposal", "Error.", e.getMessage()).show();
        }
    }

    @FXML
    public void initialize() {
        lvSkills.setItems(FXCollections.observableArrayList(generateTeamProposalController.listSkills()));

        lvCollaborators.setVisible(false);
        btnAddMember.setVisible(false);
        btnRemoveMember.setVisible(false);
        lvCollaborators.setMouseTransparent(true);
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
        }else {

        }




    }

    @FXML
    public void handleDeclineProposal() {
        lvTeamProposal.getItems().clear();

        lvCollaborators.setVisible(true);
        btnAddMember.setVisible(true);
        btnRemoveMember.setVisible(true);
        lvCollaborators.setMouseTransparent(false);
    }
}
