package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertUI {
    public static Alert createAnAlert(Alert.AlertType alertType, String interpriseName, String description, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(interpriseName);
        alert.setHeaderText(description);
        alert.setContentText(message);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Sim");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Não");
        }

        return alert;
    }
}
