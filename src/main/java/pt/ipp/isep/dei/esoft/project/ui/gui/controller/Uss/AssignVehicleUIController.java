package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

public class AssignVehicleUIController {

    @FXML
    private ComboBox<AgendaEntry> agendaEntryComboBox;

    @FXML
    private ComboBox<Vehicle> vehicleComboBox;

    @FXML
    private Label statusLabel;

    private final AssignVehicleController controller;

    public AssignVehicleUIController() {
        this.controller = new AssignVehicleController();
    }

    @FXML
    public void initialize() {
        agendaEntryComboBox.getItems().setAll(controller.getAgendaEntries());
        agendaEntryComboBox.setOnAction(event -> loadVehicles());
    }

    private void loadVehicles() {
        AgendaEntry selectedEntry = agendaEntryComboBox.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            vehicleComboBox.getItems().setAll(controller.getValidVehicles(selectedEntry));
        }
    }

    @FXML
    private void assignVehicle() {
        AgendaEntry selectedEntry = agendaEntryComboBox.getSelectionModel().getSelectedItem();
        Vehicle selectedVehicle = vehicleComboBox.getSelectionModel().getSelectedItem();

        if (selectedEntry != null && selectedVehicle != null) {
            boolean success = controller.assignVehicle(selectedEntry, selectedVehicle);
            if (success) {
                statusLabel.setText("Vehicle assigned successfully.");
            } else {
                statusLabel.setText("Failed to assign vehicle.");
            }
        } else {
            statusLabel.setText("Please select both an agenda entry and a vehicle.");
        }
    }
}