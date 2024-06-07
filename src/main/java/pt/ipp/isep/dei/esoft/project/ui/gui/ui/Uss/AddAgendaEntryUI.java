package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AddAgendaEntryUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * UI class for adding an agenda entry.
 */
public class AddAgendaEntryUI implements Initializable {

    public static final String ADDAGENDAENTRY = "Add New Entry to the Agenda";
    public AddAgendaEntryUIController addAgendaEntryUIController;

    /**
     * Shows the UI for adding an agenda entry.
     *
     * @param stage The stage to set the scene on.
     * @throws IOException If there is an error loading the FXML file.
     */
    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AddEntryAgenda.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(ADDAGENDAENTRY);
        stage.setScene(scene);
        addAgendaEntryUIController = loader.getController();
        addAgendaEntryUIController.setAddAgendaEntryUI(new AddAgendaEntryUI());
        stage.show();
    }

    /**
     * Initializes the UI components.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}


