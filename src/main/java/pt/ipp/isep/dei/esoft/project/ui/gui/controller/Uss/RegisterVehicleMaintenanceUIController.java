package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleMaintenanceUI;

public class RegisterVehicleMaintenanceUIController {

    private FMUI fmui;
    private RegisterVehicleMaintenanceUI registerVehicleMaintenanceUI;
    @FXML
    private Button btnCancel;

    @FXML
    private ListView<?> lstVehiclesRegistred;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnRemoveVehicle;

    @FXML
    private Button btnAddVehicle;

    @FXML
    private ListView<?> lvSelectedVehicles;

    @FXML
    private ListView<?> lvVehicles;


    public void handleAddVehicle() {
        // TODO
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
