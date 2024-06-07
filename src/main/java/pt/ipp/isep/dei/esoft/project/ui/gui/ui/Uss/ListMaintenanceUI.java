package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.ListMaintenanceUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class serves as the controller for the functionality of listing all the vehicles
 * that need maintenance.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListMaintenanceUI implements Initializable {
    public ListMaintenanceUIController listMaintenanceUIController;
    public MainMenuUI mainMenuUI;
    public static final String LOGIN = "List Maintenance";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/ListMaintenance.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(LOGIN);
        stage.setScene(scene);
        listMaintenanceUIController = loader.getController();
        listMaintenanceUIController.setListMaintenanceUI(new ListMaintenanceUI());
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ListMaintenanceUIController getListMaintenanceUIController() {
        return listMaintenanceUIController;
    }
}
