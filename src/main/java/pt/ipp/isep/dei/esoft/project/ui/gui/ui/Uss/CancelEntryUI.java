package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.CancelEntryUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CancelEntryUI implements Initializable {

    /**
     * The Agenda
     */
    private final Agenda agenda;

    /**
     * The Cancel Entry UI Controller
     */
    private CancelEntryUIController cancelEntryUIController;

    /**
     * The Cancel Entry UI
     */
    public static final String CANCEL = "Cancel Entry";

    /**
     * Instantiates a new Cancel entry UI.
     */
    public CancelEntryUI() {
        this.agenda = Repositories.getInstance().getAgenda();
    }

    /**
     * Instantiates a new Cancel entry UI.
     *
     * @param agenda the agenda
     */
    public CancelEntryUI(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * Show UI.
     *
     * @param stage the stage
     * @throws IOException the io exception
     */
    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/CancelEntry.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(CANCEL);
        stage.setScene(scene);
        cancelEntryUIController = loader.getController();
        cancelEntryUIController.setCancelEntryUI(new CancelEntryUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
