package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelEntryController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.CancelEntryUI;

import java.io.IOException;

public class CancelEntryUIController {

    /**
     * The Alert UI
     */
    private final AlertUI alertUI;

    /**
     * The Cancel Entry Controller
     */
    private final CancelEntryController cancelEntryController = new CancelEntryController();

    ObservableList<String> methods;

    /**
     * Instantiates a new Cancel entry UI Controller.
     */
    public CancelEntryUIController() {
        this.alertUI = new AlertUI();
    }

    /**
     * Instantiates a new Cancel entry UI Controller.
     *
     * @param agenda               the agenda
     * @param greenSpaceRepository the green space repository
     */
    public CancelEntryUIController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.alertUI = new AlertUI();
    }

    /**
     * Sets the Cancel Entry UI
     *
     * @param cancelEntryUI the Cancel Entry UI
     */
    public void setCancelEntryUI(CancelEntryUI cancelEntryUI) {
    }

    /**
     The Button to cancel an entry
     */
    @FXML
    private Button btnCancelEntry;

    /**
     * The Button to cancel
     */
    @FXML
    private Button btnCancel;

    /**
     * The ComboBox to select the agenda entries
     */
    @FXML
    private ComboBox<String> cmbAgendaEntries;

    /**
     * Initializes the controller
     */
    @FXML
    public void initialize() {
        try {
            methods = FXCollections.observableArrayList((cancelEntryController.getAgendaEntryListString()));
            cmbAgendaEntries.setItems(methods);
            btnCancelEntry.setDisable(true);
        } catch (Exception e) {
            System.out.println("Error while loading the status list.");
        }
    }

    /**
     * Handles the cancel button action
     */
    public void handleCancelButtonAction() {
        try {
            GSMUI gsmui = new GSMUI();
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    /**
     * Handles the cancel entry action
     *
     * @param actionEvent the action event
     */
    public void handleCancelEntry(ActionEvent actionEvent) {
        try {
            String selectedEntryName = cmbAgendaEntries.getValue();
            if (selectedEntryName == null) {
                alertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime", "Cancel Entry Error", "Please select an agenda entry to cancel.");
                return;
            }
            AgendaEntry selectedEntry = cancelEntryController.getAgendaEntryByTaskName(selectedEntryName);
            cmbAgendaEntries.getItems().setAll(selectedEntryName);

            cancelEntryController.cancelAgendaEntry(selectedEntry);
            Alert al = AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Musgo Sublime", "Cancel Entry", "The entry has been successfully canceled!");
            al.showAndWait();
            closeWindow(actionEvent);
        } catch (Exception e) {
            alertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime", "Cancel Entry Error", "An error occurred while canceling entry.");
        }
    }

    /**
     * Closes the window and returns to the GSM UI
     * @param event the event
     * @throws IOException the io exception
     */
    public void closeWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GSMUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    /**
     * Handles the ComboBox
     * @param actionEvent the action event
     */
    public void handleComboBox(ActionEvent actionEvent) {
        if (cmbAgendaEntries.getValue() != null) {
            btnCancelEntry.setDisable(false);
        }
    }
}
