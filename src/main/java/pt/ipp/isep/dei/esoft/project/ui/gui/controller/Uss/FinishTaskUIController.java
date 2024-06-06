package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.FinishTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.FinishTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LoginUI;

import java.util.List;

public class FinishTaskUIController {
    private final FinishTaskController controller = new FinishTaskController();
    private CollaboratorUI collaboratorUI;
    private ObservableList<String> status = FXCollections.observableArrayList(controller.getStatusList());
    private ObservableList<AgendaEntryDto> taskList = FXCollections.observableArrayList();
    private FinishTaskUI finishTaskUI;

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<String> statusList;
    @FXML
    private ListView<AgendaEntryDto> taskListView;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnShow;
    @FXML
    private Button btnComplete;

    @FXML
    public void initialize() {
        try {
            statusList.setItems(status);
            taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (Exception e) {
            System.out.println("Error while loading the status list.");
        }
    }

    public void handleCancelButtonAction() {
        try {
            collaboratorUI = new CollaboratorUI();
            collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void handleShowButtonAction() {
        try {
            if (startDate.getValue() == null || endDate.getValue() == null || statusList.getValue() == null) {
                throw new IllegalArgumentException("Please fill in all the fields.");
            } else if (startDate.getValue().isAfter(endDate.getValue())) {
                throw new IllegalArgumentException("The start date must be before the end date.");
            } else {
                Collaborator collaborator = controller.getCollaboratorByEmail(LoginUI.getEmail());
                String status = statusList.getValue();
                Date start = getStartDate();
                Date end = getEndDate();
                List<AgendaEntryDto> tasks = controller.getTaskList(collaborator, status, start, end);
                taskList.setAll(tasks);
                taskListView.setItems(taskList);
            }
        } catch (IllegalArgumentException | InvalidCollaboratorDataException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handleCompleteButtonAction() {
        try {
            AgendaEntryDto selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask == null) {
                throw new IllegalArgumentException("Please select a task to complete.");
            } else {
                controller.markTaskAsCompleted(selectedTask.getTask().getId());
                taskList.remove(selectedTask);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Date getStartDate() {
        return new Date(this.startDate.getValue().getDayOfMonth(), this.startDate.getValue().getMonthValue(), this.startDate.getValue().getYear());
    }

    public Date getEndDate() {
        return new Date(this.endDate.getValue().getDayOfMonth(), this.endDate.getValue().getMonthValue(), this.endDate.getValue().getYear());
    }

    public void setFinishTaskUI(FinishTaskUI finishTaskUI) {
        this.finishTaskUI = finishTaskUI;
    }
}
