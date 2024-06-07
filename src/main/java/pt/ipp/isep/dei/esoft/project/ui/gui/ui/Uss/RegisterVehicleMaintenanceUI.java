package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterVehicleMaintenanceUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterVehicleMaintenanceUI implements Initializable {
    private RegisterVehicleMaintenanceUIController controller;
    private final String REGISTER_MAINTENANCE = "Register Vehicle Maintenance";
    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/RegisterMaintenance.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(REGISTER_MAINTENANCE);
        stage.setScene(scene);
        controller = loader.getController();
        controller.setRegisterVehicleMaintenanceUI(new RegisterVehicleMaintenanceUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
