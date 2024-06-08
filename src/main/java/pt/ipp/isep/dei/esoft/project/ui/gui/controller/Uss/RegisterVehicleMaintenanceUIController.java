package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleMaintenanceUI;

import java.util.List;

public class RegisterVehicleMaintenanceUIController {

    private FMUI fmui;
    private RegisterVehicleMaintenanceUI registerVehicleMaintenanceUI;
    private ListMaintenanceController controller = new ListMaintenanceController();
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

    @FXML
    public void initialize() {
        List<VehicleDto> vehicles = controller.getVehicleList();
        for (VehicleDto vehicle : vehicles) {
            String vehicleString = "Vehicle: " + vehicle.getPlateNumber() + "Current kms: " + vehicle.getCurrentKms() + "Last maintenance: " + vehicle.getKmAtLastMaintenance();
            lvVehicles.getItems().add(vehicleString);
        }

        lvSelectedVehicles.setVisible(true);
        btnAddVehicle.setVisible(false);
        btnRemoveVehicle.setVisible(false);


    }

    public void handleAddVehicle() {

    }


    public void handleRemoveVehicle() {
        // TODO
    }


    public void handleShowButtonAction() {
        // TODO
    }


    public void handleCancelButton() {
        try {
            fmui = new FMUI();
            fmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setRegisterVehicleMaintenanceUI(RegisterVehicleMaintenanceUI registerVehicleMaintenanceUI) {
        this.registerVehicleMaintenanceUI = registerVehicleMaintenanceUI;
    }
}
