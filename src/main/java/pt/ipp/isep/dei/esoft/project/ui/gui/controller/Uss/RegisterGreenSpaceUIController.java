package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace.TypeOfGreenSpace;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterGreenSpaceUI;

import java.util.Optional;

/**
 * Controller class for managing the Register Green Space UI.
 */
public class RegisterGreenSpaceUIController {

    private RegisterGreenSpaceUI registerGreenSpaceUI;

    @FXML
    private TextField parkNameField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField dimensionField;

    @FXML
    private TextField addressField;

    @FXML
    private Button registerButton;

    public RegisterGreenSpaceUIController() {
        this.registerGreenSpaceUI = new RegisterGreenSpaceUI();
    }

    @FXML
    public void registerGreenSpace() {
        String parkName = parkNameField.getText();
        String type = typeField.getText();
        double dimension = Double.parseDouble(dimensionField.getText());
        String address = addressField.getText();

        try {
            Optional<GreenSpace> newGreenSpace = registerGreenSpaceUI.registerGreenSpace(parkName, type, dimension, address);
            if (newGreenSpace.isPresent()) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Green Space registered successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to register Green Space.");
            }
        } catch (InvalidGreenSpaceDataException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid dimension. Please enter a valid number.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
