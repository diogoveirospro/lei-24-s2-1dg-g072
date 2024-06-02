package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LoginUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.Output.ShowTaskList;

import java.util.List;

/**
 * This class serves as the controller for the functionality of listing all the tasks
 * that the collaborator has.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListTaskController {
    private ObservableList<String> status = FXCollections.observableArrayList(getStatusList());
    /**
     * ListTaskUI
     */
    private ListTaskUI listTaskUI;

    /**
     * Lets you pick a start date
     */
    @FXML
    private DatePicker startDate;
    /**
     * Lets you pick an end date
     */
    @FXML
    private DatePicker endDate;
    /**
     * Lets you pick a status
     */
    @FXML
    private ComboBox statusList;
    /**
     * Button to cancel
     */
    @FXML
    private Button btnCancel;
    /**
     * Button to show
     */
    @FXML
    private Button btnShow;

    /**
     * Initializes the ui attributes
     */
    @FXML
    public void initialize() {
        try{
            statusList.setItems(status);

        } catch (Exception e) {
            System.out.println("Error while loading the status list.");
        }
    }
    /**
     * Handles the cancel button action
     */
    public void handleCancelButtonAction() {
        try {
            collaboratorUI = new CollaboratorUI();
            collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
    /**
     * Handles the show button action
     */
    public void handleShowButtonAction() {
        try {
            if (startDate.getValue() == null || endDate.getValue() == null || statusList.getValue() == null) {
                throw new IllegalArgumentException("Please fill in all the fields.");
            } else if (startDate.getValue().isAfter(endDate.getValue())) {
                throw new IllegalArgumentException("The start date must be before the end date.");
            } else{
                /*
                Collaborator collaborator = getCollaboratorByEmail(LoginUI.getEmail());
                String status = (String) statusList.getValue();
                Date startDate = getStartDate();
                Date endDate = getEndDate();
                List<AgendaEntryDto> taskList = getTaskList(collaborator, status, startDate, endDate);
                */
                showTaskList = new ShowTaskList();
                showTaskList.showUI();
            }

        } catch (Exception e) {
            System.out.println("An error occurred while handling the show action: " + e.getMessage());
        }
    }

    /**
     * Gets the start date
     *
     */
    public Date getStartDate() {
        Date startDate = new Date(this.startDate.getValue().getDayOfMonth(), this.startDate.getValue().getMonthValue(), this.startDate.getValue().getYear());
        return startDate;
    }

    /**
     * Gets the end date
     */
    public Date getEndDate() {
        Date endDate = new Date(this.endDate.getValue().getDayOfMonth(), this.endDate.getValue().getMonthValue(), this.endDate.getValue().getYear());
        return endDate;
    }
    /**
     * Gets the status
     */
    public void getStatus() {
        String status = (String) this.statusList.getValue();
    }
    /**
     * taskRepository contains all tasks
     */
    private TaskRepository taskRepository;
    /**
     * collaboratorRepository contains all collaborators
     */
    private CollaboratorRepository collaboratorRepository;
    /**
     * agenda contains all entries inside the agenda
     */
    private Agenda agenda;
    /**
     * teamRepository contains all teams
     */
    private TeamRepository teamRepository;
    /**
     * authenticationRepository authenticates the app
     */
    private AuthenticationRepository authenticationRepository;
    /**
     * Ui of the collaborator
     */
    private CollaboratorUI collaboratorUI;
    /**
     * Ui of the list to be shown
     */
    private ShowTaskList showTaskList;
    /**
     * Empty ListTaskController builder.
     */
    public ListTaskController() {
        this.taskRepository = Repositories.getInstance().getTaskRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.agenda = Repositories.getInstance().getAgenda();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * ListTaskController builder
     *
     * @param taskRepository           contains all tasks
     * @param collaboratorRepository   contains all collaborators
     * @param agenda                   contains all tasks
     * @param teamRepository           contains all teams
     * @param authenticationRepository authenticates the app
     */
    public ListTaskController(TaskRepository taskRepository, CollaboratorRepository collaboratorRepository, Agenda agenda, TeamRepository teamRepository, AuthenticationRepository authenticationRepository) {
        this.taskRepository = taskRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.agenda = agenda;
        this.teamRepository = teamRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Lets the controller get the task repository
     *
     * @return taskRepository
     */
    public TaskRepository getTaskRepository() {
        if (taskRepository == null) {
            Repositories repositories = Repositories.getInstance();

            taskRepository = repositories.getTaskRepository();
        }
        return taskRepository;
    }

    /**
     * Lets the controller get the collaborator repository
     *
     * @return collaboratorRepository
     */
    public CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Lets the controller get the agenda
     *
     * @return agenda
     */
    public Agenda getAgenda() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();

            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    /**
     * Lets the controller get the team repository
     *
     * @return teamRepository
     */
    public TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();

            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }


    /**
     * Lets the controller get the authentication repository
     *
     * @return authenticationRepository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Lets the controller get the collaborator by email
     *
     * @param email email
     * @return collaborator
     */
    public Collaborator getCollaboratorByEmail(String email) {
        return this.collaboratorRepository.getCollaboratorByEmail(email);
    }

    /**
     * Lets the controller get the status list
     *
     * @return status list
     */
    public List<String> getStatusList() {
        agenda = Repositories.getInstance().getAgenda();
        List<String> statusList = this.agenda.getStatusList();
        statusList.add("None");
        return statusList;
    }

    /**
     * Lets the controller get the task list of the collaborator
     *
     * @param collaborator that we want to get all the tasks
     * @param typeStatus  status of the task
     * @param startDate  start date of the task
     * @param endDate   end date of the task
     * @return list of tasks of the collaborator
     */
    public List<AgendaEntryDto> getTaskList(Collaborator collaborator, String typeStatus, Date startDate, Date endDate) {
        List<Team> teamList = this.teamRepository.getTeamsByCollaborator(collaborator);
        List<Task> taskList = this.taskRepository.getCollaboratorTaskList(teamList);
        List<AgendaEntry> agendaEntryList = this.agenda.getAgendaEntryList(taskList, startDate, endDate,typeStatus);
        AgendaEntryMapper mapper = new AgendaEntryMapper();
        return mapper.toDtoList(agendaEntryList);
    }

    public void setListTaskUI(ListTaskUI listTaskUI) {
        this.listTaskUI = listTaskUI;
    }
}
