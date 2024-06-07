package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleUI;

public class RegisterVehicleUIController {

    private RegisterVehicleUI registerVehicleUI;
    private FMUI fmui;
    @FXML
    private TextField modelField;

    @FXML
    private TextField grossWeightField;

    @FXML
    private TextField kmAtLastMaintenanceField;

    @FXML
    private TextField plateNumberField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField currKmsField;

    @FXML
    private TextField tareField;

    @FXML
    private DatePicker acquisitionDatePicker;

    @FXML
    private TextField maintenanceFrequencyField;

    @FXML
    private DatePicker registrationDatePicker;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField typeField;

    @FXML
    private Button btnCancel;


    public void handleRegisterAction() {
        // TODO implement here
    }

    public void handleCancelButtonAction() {
        try {
            fmui = new FMUI();
            fmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setRegisterVehicleUI(RegisterVehicleUI registerVehicleUI) {
        this.registerVehicleUI = registerVehicleUI;
    }
}
