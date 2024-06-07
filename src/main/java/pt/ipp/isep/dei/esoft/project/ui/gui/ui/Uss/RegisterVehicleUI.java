package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterVehicleUIController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterVehicleUI implements Initializable {
    private RegisterVehicleUIController controller;
    private static final String REGISTERVEHICLE = "Register Vehicle";


    public void showUI(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/RegisterVehicle.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(REGISTERVEHICLE);
        stage.setScene(scene);
        controller = loader.getController();
        controller.setRegisterVehicleUI(new RegisterVehicleUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
