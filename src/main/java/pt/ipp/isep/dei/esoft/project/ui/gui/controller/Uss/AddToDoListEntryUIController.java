package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.AddToDoListController;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AddToDoListEntryUI;

import java.util.List;

/**
 * Controller class for the Add To-Do List Entry UI.
 * This class handles user interactions with the Add To-Do List Entry interface.
 */

public class AddToDoListEntryUIController {
    private AddToDoListEntryUI addToDoListEntryUI;
    private GSMUI gsmui;
    @FXML
    private TextField taskNameTextField;

    @FXML
    private TextField taskDurationTextField;

    @FXML
    private ComboBox<String> greenSpaceNameCB;

    @FXML
    private ComboBox<String> degreeOfUrgencyComboBox;

    @FXML
    private ListView<String> taskListView;

    @FXML
    private Button confirmButton;

    @FXML
    private Button btnCancel;


    private AddToDoListController controller = new AddToDoListController();

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     * It initializes the degree of urgency combo box and the AddToDoListController.
     */
    @FXML
    public void initialize() {
        try {
            degreeOfUrgencyComboBox.getItems().addAll(controller.getDegreeOgUrgencyList());
            List<GreenSpaceDto> greenSpaceList = controller.getGreenSpaceList();
            for (GreenSpaceDto greenSpace : greenSpaceList) {
                greenSpaceNameCB.getItems().add(greenSpace.getParkName());
            }
        } catch (Exception e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error loading lists.",e.getMessage());
        }

    }

    /**
     * Handles the action of adding a new to-do list entry.
     * This method is triggered when the confirm button is clicked.
     * It retrieves input values, calls the controller to add the new entry, and updates the status label and task list view.
     */
    @FXML
    private void addToDoListEntry() {
        try {
            String taskName = taskNameTextField.getText();
            String taskDuration = taskDurationTextField.getText();
            String greenSpaceName = greenSpaceNameCB.getValue();
            String degreeOfUrgency = degreeOfUrgencyComboBox.getValue();

            controller.addNewToDoListEntry(taskName, taskDuration, greenSpaceName, degreeOfUrgency);
            taskListView.getItems().add(taskName + " - " + taskDuration + " - " + greenSpaceName + " - " + degreeOfUrgency);
            AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success", "Entry added successfully.", "The entry was added successfully.");
        } catch (Exception e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error adding entry.", e.getMessage());
        }
    }

    /**
     * Handles the action of cancelling the entry.
     * This method is triggered when the cancel button is clicked.
     * It clears all input fields and updates the status label.
     */
    @FXML
    private void cancelEntry() {
        try {
            gsmui = new GSMUI();
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setAddToDoListEntryUI(AddToDoListEntryUI addToDoListEntryUI) {
        this.addToDoListEntryUI = addToDoListEntryUI;
    }
}