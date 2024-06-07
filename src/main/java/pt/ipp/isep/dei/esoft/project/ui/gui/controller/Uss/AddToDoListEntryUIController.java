package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.AddToDoListController;

/**
 * Controller class for the Add To-Do List Entry UI.
 * This class handles user interactions with the Add To-Do List Entry interface.
 */

public class AddToDoListEntryUIController {

    @FXML
    private TextField taskNameTextField;

    @FXML
    private TextField taskDurationTextField;

    @FXML
    private TextField greenSpaceNameTextField;

    @FXML
    private ComboBox<String> degreeOfUrgencyComboBox;

    @FXML
    private ListView<String> taskListView;

    @FXML
    private Button confirmButton;

    @FXML
    private Button btnCancel;

    @FXML
    private Label statusLabel;

    private AddToDoListController controller;

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     * It initializes the degree of urgency combo box and the AddToDoListController.
     */
    @FXML
    public void initialize() {
        controller = new AddToDoListController();
        degreeOfUrgencyComboBox.getItems().addAll("Low", "Medium", "High");
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
            String greenSpaceName = greenSpaceNameTextField.getText();
            String degreeOfUrgency = degreeOfUrgencyComboBox.getValue();

            controller.addNewToDoListEntry(taskName, taskDuration, greenSpaceName, degreeOfUrgency);
            taskListView.getItems().add(taskName + " - " + taskDuration + " - " + greenSpaceName + " - " + degreeOfUrgency);
            statusLabel.setText("To-Do list entry added successfully!");
        } catch (InvalidTaskDataException | InvalidEntryDataException e) {
            statusLabel.setText("Failed to add entry: " + e.getMessage());
        } catch (Exception e) {
            statusLabel.setText("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Handles the action of cancelling the entry.
     * This method is triggered when the cancel button is clicked.
     * It clears all input fields and updates the status label.
     */
    @FXML
    private void cancelEntry() {
        taskNameTextField.clear();
        taskDurationTextField.clear();
        greenSpaceNameTextField.clear();
        degreeOfUrgencyComboBox.getSelectionModel().clearSelection();
        statusLabel.setText("Entry cancelled.");
    }
}