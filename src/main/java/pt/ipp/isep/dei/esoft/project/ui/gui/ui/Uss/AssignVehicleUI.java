package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AssignVehicleUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AssignVehicleUI implements Initializable {

    private AssignVehicleUIController assignVehicleUIController;


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AssignVehicleUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Assign Vehicle to Agenda Entry");
        stage.setScene(scene);
        assignVehicleUIController = loader.getController();
        assignVehicleUIController.setAssignVehicleUI(new AssignVehicleUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
