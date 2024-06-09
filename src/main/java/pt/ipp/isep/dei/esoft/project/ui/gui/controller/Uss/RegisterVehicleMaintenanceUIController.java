package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleMaintenanceUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the Register Vehicle Maintenance User Interface.
 * This class handles user interactions and events related to registering vehicle maintenance.
 */

public class RegisterVehicleMaintenanceUIController {

    private FMUI fmui;
    private RegisterVehicleMaintenanceUI registerVehicleMaintenanceUI;
    private RegisterVehicleMaintenanceController controller = new RegisterVehicleMaintenanceController();
    @FXML
    private Button btnCancel;

    @FXML
    private ListView<String> lstVehiclesRegistred;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnRemoveVehicle;

    @FXML
    private Button btnAddVehicle;

    @FXML
    private ListView<String> lvSelectedVehicles;

    @FXML
    private ListView<String> lvVehicles;

    /**
     * Initializes the UI with the list of available vehicles.
     */

    @FXML
    public void initialize() {
        List<Vehicle> vehicles = controller.getVehicleList();
        for (Vehicle vehicle : vehicles) {
            String vehicleString = vehicle.getPlateNumber();
            lvVehicles.getItems().add(vehicleString);
        }
    }

    /**
     * Handles the action when the user clicks on the Add Vehicle button.
     * It adds the selected vehicle to the list of selected vehicles.
     */

    public void handleAddVehicle() {
        String selectedVehicle = lvVehicles.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            lvSelectedVehicles.getItems().add(selectedVehicle);
            lvVehicles.getItems().remove(selectedVehicle);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a vehicle to add");
            alert.showAndWait();
        }
    }

    /**
     * Handles the action when the user clicks on the Remove Vehicle button.
     * It removes the selected vehicle from the list of selected vehicles.
     */

    public void handleRemoveVehicle() {
        String selectedVehicle = lvSelectedVehicles.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            lvVehicles.getItems().add(selectedVehicle);
            lvSelectedVehicles.getItems().remove(selectedVehicle);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a vehicle to remove");
            alert.showAndWait();
        }
    }

    /**
     * Handles the action when the user clicks on the Show button.
     * It registers the selected vehicles for maintenance and displays a success message.
     */

    public void handleShowButtonAction() {
        lstVehiclesRegistred.getItems().clear();
        if (lvSelectedVehicles.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select at least one vehicle to register");
            alert.showAndWait();
        } else {
            try {
                List<String> vehicles = lvSelectedVehicles.getItems();
                List<Vehicle> vehicleList = controller.getVehicleList();
                List<Vehicle> selectedVehicles = new ArrayList<>();
                for (String vehicle : vehicles) {

                    for (Vehicle v : vehicleList) {
                        if (v.getPlateNumber().equals(vehicle)) {
                            selectedVehicles.add(v);
                        }
                    }
                }
                controller.registerVehicleMaintenance(selectedVehicles);

                lstVehiclesRegistred.getItems().addAll(vehicles);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Vehicles registered successfully");
                alert.showAndWait();

            } catch (Exception e) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error","Error registering vehicle maintenance.", e.getMessage()).show();
            }
        }
    }

    /**
     * Handles the action when the user clicks on the Cancel button.
     * It navigates back to the Fleet Management UI.
     */

    public void handleCancelButton() {
        try {
            fmui = new FMUI();
            fmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    /**
     * Setter method for the Register Vehicle Maintenance User Interface.
     * @param registerVehicleMaintenanceUI The Register Vehicle Maintenance User Interface to be set.
     */

    public void setRegisterVehicleMaintenanceUI(RegisterVehicleMaintenanceUI registerVehicleMaintenanceUI) {
        this.registerVehicleMaintenanceUI = registerVehicleMaintenanceUI;
    }
}
