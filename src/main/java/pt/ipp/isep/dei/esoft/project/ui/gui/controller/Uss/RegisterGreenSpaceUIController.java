package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace.TypeOfGreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterGreenSpaceUI;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for managing the Register Green Space UI.
 */
public class RegisterGreenSpaceUIController {
    private GSMUI gsmui;
    private final RegisterGreenSpaceController controller = new RegisterGreenSpaceController();
    private RegisterGreenSpaceUI registerGreenSpaceUI;

    @FXML
    private TextField parkNameField;

    @FXML
    private ComboBox<String> typeField;

    @FXML
    private TextField dimensionField;

    @FXML
    private TextField addressField;

    @FXML
    private Button registerButton;

    public RegisterGreenSpaceUIController() {
        this.registerGreenSpaceUI = new RegisterGreenSpaceUI();
    }

    public void registerGreenSpace() throws InvalidGreenSpaceDataException {

        try {
            if (parkNameField.getText().isEmpty() || typeField.getValue() == null || dimensionField.getText().isEmpty() || addressField.getText().isEmpty()) {
                throw new InvalidGreenSpaceDataException("Please fill in all fields.");
            }
            String parkName = parkNameField.getText();
            String type = typeField.getValue();
            String dimensionStr = dimensionField.getText();
            String address = addressField.getText();
            ValidatorUtils.isValidName(parkName);
            ValidatorUtils.isValidAddress(address);
            ValidatorUtils.isValidNumber(dimensionStr);
            double dimension = Double.parseDouble(dimensionStr);
            GreenSpace.TypeOfGreenSpace typeOfGreenSpace = TypeOfGreenSpace.getTypeOfGreenSpace(type);
            Boolean isPresent = controller.registerGreenSpace(parkName, dimension, address, typeOfGreenSpace);
            if (isPresent) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Green Space registered successfully.");
                gsmui = new GSMUI();
                gsmui.showUI(MainMenuUI.getPrimaryStage());
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to register Green Space.");
            }
        } catch (Exception e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering Green Space.", e.getMessage()).show();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setRegisterGreenSpaceUI(RegisterGreenSpaceUI registerGreenSpaceUI) {
        this.registerGreenSpaceUI = registerGreenSpaceUI;
    }
    public void handleCancelButtonAction() {
        try {
            gsmui = new GSMUI();
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
}
