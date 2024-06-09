package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.ListTaskController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.Output.ShowTaskListUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        try {
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to list all the tasks?");
        alert.setContentText("The data provided will be used to all the tasks attributed to you.");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Date dateI = getStartDate();
                Date dateF = getEndDate();
                if (startDate.getValue() == null || endDate.getValue() == null || statusList.getValue() == null) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "List Task", "Error.", "Please fill in all the fields.").show();
                    throw new IllegalArgumentException("Please fill in all the fields.");
                } else if (startDate.getValue().isAfter(endDate.getValue())) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "List Task", "Error.", "The start date must be before the end date.").show();
                    throw new IllegalArgumentException("The start date must be before the end date.");

                } else if (dateI.compareTo(Date.currentDate()) >= 0 || dateF.compareTo(Date.currentDate()) >= 0) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "List Task", "Error.", "Please insert a date that is greater or equal to the current date.").show();
                    throw new IllegalArgumentException("The end date must be after the start date.");

                }
                else {
                    AgendaEntry.StatusOfEntry status = null;
                    if (!statusList.getValue().toString().equalsIgnoreCase("None")) {
                        status = AgendaEntry.StatusOfEntry.getStatusOfEntry((String) statusList.getValue());
                    }
                    Date startDate = getStartDate();
                    Date endDate = getEndDate();
                    List<AgendaEntryDto> taskList = controller.getTaskList(status, startDate, endDate);
                    List<String> taskListString = new ArrayList<>();
                    String[][] dates = new String[taskList.size()][2];
                    for (AgendaEntryDto task : taskList) {
                        taskListString.add(task.getTask().getTaskId());
                    }
                    for (int i = 0; i < taskList.size(); i++) {
                        dates[i][0] = taskList.get(i).getStartDate().toString();
                        dates[i][1] = taskList.get(i).getEndDate().toString();
                    }
                    showTaskListUI = new ShowTaskListUI();
                    showTaskListUI.showUI(MainMenuUI.getPrimaryStage(), taskListString, dates);
                }

            } catch (InvalidCollaboratorDataException | InvalidEntryDataException e) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "List Task", "Error.", e.getMessage()).show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     * Gets the start date
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
