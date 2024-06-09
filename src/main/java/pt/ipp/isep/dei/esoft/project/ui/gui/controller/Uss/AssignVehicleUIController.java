package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AssignVehicleUI;

import java.util.List;

/**
 * Controller class for the Assign Vehicle UI.
 */
public class AssignVehicleUIController {
    private GSMUI gsmui;
    private AssignVehicleUI assignVehicleUI;
    private List<Vehicle> vehicleList;
    List<AgendaEntry> agendaEntries;

    @FXML
    private ComboBox<String> agendaEntryComboBox;

    @FXML
    private ComboBox<String> vehicleComboBox;

    @FXML
    private Button assignVehicleButton;

    @FXML
    private Button CancelBtn;

    private final AssignVehicleController controller;

    /**
     * Constructs an instance of the AssignVehicleUIController.
     */
    public AssignVehicleUIController() {
        this.controller = new AssignVehicleController();
    }

    /**
     * Initializes the controller and sets up the UI components.
     */
    @FXML
    public void initialize() {

        agendaEntries = controller.getAgendaEntries();
        for (AgendaEntry entry : agendaEntries) {
            agendaEntryComboBox.getItems().add(entry.getName());
        }
        agendaEntryComboBox.setOnAction(event -> loadVehicles());
    }

    /**
     * Loads the vehicles for the selected agenda entry.
     */
    private void loadVehicles() {
        String selectedEntryString = agendaEntryComboBox.getSelectionModel().getSelectedItem();
        AgendaEntry selectedEntry = null;
        for (AgendaEntry entry : agendaEntries) {
            if (entry.getName().equals(selectedEntryString)) {
                selectedEntry = entry;
                break;
            }
        }
        if (selectedEntry != null) {
            vehicleList = controller.getValidVehicles(selectedEntry);
            for (Vehicle vehicle : vehicleList) {
                vehicleComboBox.getItems().add(vehicle.getPlateNumber());
            }
        }
    }

    /**
     * Handles the action of assigning a vehicle to the selected agenda entry.
     */
    @FXML
    private void assignVehicle() {

        String selectedEntryString = agendaEntryComboBox.getSelectionModel().getSelectedItem();
        AgendaEntry selectedEntry = null;
        for (AgendaEntry entry : agendaEntries) {
            if (entry.getName().equals(selectedEntryString)) {
                selectedEntry = entry;
                break;
            }
        }
        String selectedVehiclePlateNumber = vehicleComboBox.getSelectionModel().getSelectedItem();
        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getPlateNumber().equals(selectedVehiclePlateNumber)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedEntry != null && selectedVehicle != null) {
            boolean success = controller.assignVehicle(selectedEntry, selectedVehicle);
            if (success) {
                AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success", "", "Vehicle assigned successfully.").show();
                vehicleComboBox.getSelectionModel().clearSelection();
                agendaEntryComboBox.getSelectionModel().clearSelection();
            } else {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "", "Failed to assign vehicle.").show();
            }
        } else {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "", "Please select both an agenda entry and a vehicle.").show();
        }
    }

    /**
     * Sets the AssignVehicleUI instance.
     */
    public void setAssignVehicleUI(AssignVehicleUI assignVehicleUI) {
        this.assignVehicleUI = assignVehicleUI;
    }

    /**
     * Handles the action of canceling the vehicle assignment.
     */
    public void handleCancelButtonAction() {
        try {
            gsmui = new GSMUI();
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
}
