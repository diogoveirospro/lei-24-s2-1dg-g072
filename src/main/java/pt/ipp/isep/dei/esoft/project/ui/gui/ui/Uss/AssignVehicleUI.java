package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleController;

import java.io.IOException;

public class AssignVehicleUI {

    private AssignVehicleController assignVehicleController;

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AssignVehicleToAgendaEntry.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Assign Vehicle to Agenda Entry");
        stage.setScene(scene);
        assignVehicleController = loader.getController();
        stage.show();
    }
}
