package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleUI;

import java.util.Optional;

public class RegisterVehicleUIController {

    private RegisterVehicleUI registerVehicleUI;
    private FMUI fmui;
    private RegisterVehicleController controller;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to Register this vehicle?");
        alert.setContentText("The data provided will be used to register a vehicle.");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if (modelField.getText().isEmpty() || brandField.getText().isEmpty() || plateNumberField.getText().isEmpty() || typeField.getText().isEmpty() || currKmsField.getText().isEmpty() || grossWeightField.getText().isEmpty() || tareField.getText().isEmpty() || kmAtLastMaintenanceField.getText().isEmpty() || maintenanceFrequencyField.getText().isEmpty() || acquisitionDatePicker.getValue() == null || registrationDatePicker.getValue() == null) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering vehicle.", "Please fill in all fields.").show();

                } else {
                    String model = modelField.getText();
                    String brand = brandField.getText();
                    String plateNumber = plateNumberField.getText();
                    String type = typeField.getText();
                    String currKms = currKmsField.getText();
                    String grossWeight = grossWeightField.getText();
                    String tare = tareField.getText();
                    String kmAtLastMaintenance = kmAtLastMaintenanceField.getText();
                    String maintenanceFrequency = maintenanceFrequencyField.getText();
                    String acquisitionDateStr = acquisitionDatePicker.getValue().toString();
                    String registrationDateStr = registrationDatePicker.getValue().toString();
                    double tareDouble = Double.parseDouble(tare);
                    double grossWeightDouble = Double.parseDouble(grossWeight);
                    double kmAtLastMaintenanceDouble = Double.parseDouble(kmAtLastMaintenance);
                    double maintenanceFrequencyDouble = Double.parseDouble(maintenanceFrequency);
                    double currKmsDouble = Double.parseDouble(currKms);

                    String[] dateParts = acquisitionDateStr.split("-");
                    int year1 = Integer.parseInt(dateParts[0]);
                    int month1 = Integer.parseInt(dateParts[1]);
                    int day1 = Integer.parseInt(dateParts[2]);

                    Date acquisitionDate = new Date(year1, month1, day1);
                    dateParts = registrationDateStr.split("-");
                    int year2 = Integer.parseInt(dateParts[0]);
                    int month2 = Integer.parseInt(dateParts[1]);
                    int day2 = Integer.parseInt(dateParts[2]);

                    Date registrationDate = new Date(year2, month2, day2);
                    if (acquisitionDate.isGreater(Date.currentDate()) || registrationDate.isGreater(Date.currentDate())) {
                        AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering vehicle.", "The dates must be in the past.").show();

                    } else {
                        controller = new RegisterVehicleController();
                        controller.registerVehicle(plateNumber, model, brand, type, tareDouble, grossWeightDouble, currKmsDouble, registrationDate, acquisitionDate, maintenanceFrequencyDouble, kmAtLastMaintenanceDouble);

                        fmui = new FMUI();
                        fmui.showUI(MainMenuUI.getPrimaryStage());
                    }

                }

            } catch (Exception e) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering vehicle.", e.getMessage()).show();
            }
        }
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
