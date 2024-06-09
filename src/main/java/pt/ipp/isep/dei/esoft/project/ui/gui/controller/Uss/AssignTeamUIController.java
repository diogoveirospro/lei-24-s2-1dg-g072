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

/**
 * Controller class for the Assign Team UI.
 */

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

    /**
     * Initializes the controller and sets up the UI components.
     */

    @FXML
    public void initialize() {
        populateAgendaEntriesListView();
        setupAgendaEntriesListViewClickListener();
        populateTeamsListView();
        setupTeamsListViewClickListener();
        populateEmailServiceListView();
        setupEmailServiceListViewClickListener();
    }
    /**
     * Populates the agenda entries list view with the available agenda entries.
     */

    public void populateAgendaEntriesListView() {

        List<AgendaEntry> agendaEntries = controller.getAgendaEntriesWithoutTeam();

        for (AgendaEntry agendaEntry : agendaEntries) {
            lvAgendaEntries.getItems().add(agendaEntry.getName());
        }

    }
    /**
     * Sets up a listener for the agenda entries list view selection.
     */

    private void setupAgendaEntriesListViewClickListener() {
        lvAgendaEntries.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedAgendaEntry = controller.getAgendaEntry(newValue);
        });
    }
    /**
     * Populates the teams list view with the available teams for the selected agenda entry.
     */

    public void populateTeamsListView() {
        List<Team> teams = controller.getValidTeams(selectedAgendaEntry);
        lvTeams.getItems().clear();
        lvTeams.getItems().addAll(teams);
    }
    /**
     * Sets up a listener for the teams list view selection.
     */

    private void setupTeamsListViewClickListener() {
        lvTeams.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedTeam = newValue;
        });
    }

    /**
     * Populates the email service combo box with the available email services.
     */

    private void populateEmailServiceListView() {
        try {
            List<String> emailServices = controller.showEmailServices();
            cbEmailService.getItems().addAll(emailServices);
        } catch (IOException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Email Services",
                    "Email services could not be shown.", e.getMessage());
        }

    }

    /**
     * Sets up a listener for the email service combo box selection.
     */

    private void setupEmailServiceListViewClickListener() {
        cbEmailService.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedEmailService = newValue;
        });
    }

    /**
     * Handles the action of assigning a team to the selected agenda entry.
     */

    @FXML
    public void handleAssignTeam() {
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        List<Team> teams = teamRepository.getTeams();
        try {
            Team team = null;

            if (selectedAgendaEntry == null) {
                throw new IllegalArgumentException("Please select an entry from the agenda");
            }

            if (selectedTeam == null) {
                throw new IllegalArgumentException("Please select a team");
            }

            if (selectedEmailService == null) {
                throw new IllegalArgumentException("Please select an email service");
            }

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Assign Team",
                    "It was not possible to assign the team to the agenda entry.", e.getMessage()).show();
        }

    }
    /**
     * Handles the action of canceling the team assignment.
     */

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
