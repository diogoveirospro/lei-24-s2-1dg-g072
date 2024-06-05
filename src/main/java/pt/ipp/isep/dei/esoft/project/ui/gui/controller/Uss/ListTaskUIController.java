package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import pt.ipp.isep.dei.esoft.project.application.controller.ListTaskController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.Output.ShowTaskListUI;

import java.io.IOException;

/**
 * This class serves as the controller for the functionality of listing all the tasks
 * that the collaborator has.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListTaskUIController {
    private final ListTaskController controller = new ListTaskController();
    private ShowTaskListUI showTaskListUI;
    private CollaboratorUI collaboratorUI;
    /**
     * List of all the tasks
     */
    private ObservableList<String> status = FXCollections.observableArrayList((controller.getStatusList()));
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
                showTaskListUI = new ShowTaskListUI();
                showTaskListUI.showUI(MainMenuUI.getPrimaryStage());
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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


    public void setListTaskUI(ListTaskUI listTaskUI) {
        this.listTaskUI = listTaskUI;
    }
}
