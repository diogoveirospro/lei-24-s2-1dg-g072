package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AssignTeamUI;

import java.io.IOException;
import java.util.List;

public class AssignTeamUIController {

    @FXML
    private ListView<String> cbAgendaEntries;

    @FXML
    private ListView<Team> cbTeams;

    private AssignTeamController controller = new AssignTeamController();
    private AssignTeamUI assignTeamUI = new AssignTeamUI();

    private AgendaEntry selectedAgendaEntry;
    private Team selectedTeam;

    @FXML
    public void initialize() {
        populateAgendaEntriesListView();
        setupAgendaEntriesListViewClickListener();
        populateTeamsListView();
        setupTeamsListViewClickListener();
    }

    public void populateAgendaEntriesListView() {

        List<AgendaEntry> agendaEntries = controller.getAgendaEntriesWithoutTeam();

        for (AgendaEntry agendaEntry : agendaEntries) {
            cbAgendaEntries.getItems().add(agendaEntry.getName());
        }

    }

    private void setupAgendaEntriesListViewClickListener() {
        cbAgendaEntries.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedAgendaEntry = controller.getAgendaEntry(newValue);
        });
    }

    public void populateTeamsListView() {
        List<Team> teams = controller.getValidTeams(selectedAgendaEntry);
        cbTeams.getItems().addAll(teams);
    }

    private void setupTeamsListViewClickListener() {
        cbTeams.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedTeam = newValue;
        });
    }

    @FXML
    public void handleAssignTeam() {
        try {
            controller.assignTeamToAgendaEntry(selectedAgendaEntry, selectedTeam);
            assignTeamUI.showUI(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void handleCancel() {
        GSMUI gsmui = new GSMUI();
        try {
            gsmui.showUI(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAssignTeamUI (AssignTeamUI assignTeamUI) {
        this.assignTeamUI = assignTeamUI;
    }

}
