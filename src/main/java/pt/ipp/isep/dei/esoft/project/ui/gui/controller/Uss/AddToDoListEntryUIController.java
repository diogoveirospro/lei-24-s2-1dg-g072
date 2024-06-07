package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.AddToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Task;

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
    private Button addButton;

    @FXML
    private Label statusLabel;

    private AddToDoListController controller;

    @FXML
    public void initialize() {
        controller = new AddToDoListController();
        degreeOfUrgencyComboBox.getItems().addAll("LOW", "MEDIUM", "HIGH");
        addButton.setOnAction(event -> addToDoListEntry());
    }

    private void addToDoListEntry() {
        try {
            String taskName = taskNameTextField.getText();
            String taskDuration = taskDurationTextField.getText();
            String greenSpaceName = greenSpaceNameTextField.getText();
            String degreeOfUrgency = degreeOfUrgencyComboBox.getValue();

            controller.addNewToDoListEntry(taskName, taskDuration, greenSpaceName, degreeOfUrgency);
            statusLabel.setText("To-Do list entry added successfully!");
        } catch (InvalidTaskDataException | InvalidEntryDataException e) {
            statusLabel.setText("Failed to add entry: " + e.getMessage());
        } catch (Exception e) {
            statusLabel.setText("An unexpected error occurred: " + e.getMessage());
        }
    }
}