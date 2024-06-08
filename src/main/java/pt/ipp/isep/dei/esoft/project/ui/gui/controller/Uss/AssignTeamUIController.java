package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AssignTeamUI;

import java.io.IOException;
import java.util.List;

public class AssignTeamUIController {

    @FXML
    private ListView<String> lvAgendaEntries;

    @FXML
    private ListView<Team> lvTeams;

    @FXML
    private ComboBox<String> cbEmailService;

    private AssignTeamController controller = new AssignTeamController();
    private AssignTeamUI assignTeamUI = new AssignTeamUI();

    private AgendaEntry selectedAgendaEntry;
    private Team selectedTeam;
    private String selectedEmailService;

    @FXML
    public void initialize() {
        populateAgendaEntriesListView();
        setupAgendaEntriesListViewClickListener();
        populateTeamsListView();
        setupTeamsListViewClickListener();
        populateEmailServiceListView();
        setupEmailServiceListViewClickListener();
    }

    public void populateAgendaEntriesListView() {

        List<AgendaEntry> agendaEntries = controller.getAgendaEntriesWithoutTeam();

        for (AgendaEntry agendaEntry : agendaEntries) {
            lvAgendaEntries.getItems().add(agendaEntry.getName());
        }

    }

    private void setupAgendaEntriesListViewClickListener() {
        lvAgendaEntries.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedAgendaEntry = controller.getAgendaEntry(newValue);
        });
    }

    public void populateTeamsListView() {
        List<Team> teams = controller.getValidTeams(selectedAgendaEntry);
        lvTeams.getItems().clear();
        lvTeams.getItems().addAll(teams);
    }

    private void setupTeamsListViewClickListener() {
        lvTeams.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedTeam = newValue;
        });
    }

    private void populateEmailServiceListView() {
        try {
            List<String> emailServices = controller.showEmailServices();
            cbEmailService.getItems().addAll(emailServices);
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Email Services",
                    "Email services could not be shown.", e.getMessage());
        }

    }

    private void setupEmailServiceListViewClickListener() {
        cbEmailService.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedEmailService = newValue;
        });
    }

    @FXML
    public void handleAssignTeam() {
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        List<Team> teams = teamRepository.getTeams();
        try {
            Team team = null;
            for (Team t : teams) {
                if (t.toString().equalsIgnoreCase(selectedTeam.toString())) {
                    team = t;
                    break;
                }
            }
            if (team == null) {
                throw new IllegalArgumentException("The team does not exist.");
            }
            controller.assignTeamToAgendaEntry(selectedAgendaEntry, team);
            controller.sendEmailToTeamMembers(team, selectedEmailService);
            AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Assign Team",
                    "The team was successfully assigned to the agenda entry.", "").show();
            assignTeamUI = new AssignTeamUI();
            assignTeamUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Assign Team",
                    "It was not possible to assign the team to the agenda entry.", e.getMessage()).show();
        }

    }

    @FXML
    public void handleCancel() {
        GSMUI gsmui = new GSMUI();
        try {
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Cancel Assign Team",
                    "It was not possible to return to the menu.", e.getMessage());
        }
    }

    public void setAssignTeamUI (AssignTeamUI assignTeamUI) {
        this.assignTeamUI = assignTeamUI;
    }

}
