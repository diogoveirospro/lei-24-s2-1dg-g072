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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeEntryController;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.PostponeEntryUI;

import java.io.IOException;
import java.time.LocalDate;

public class PostponeEntryUIController {

    /**
     * The Alert UI
     */
    private AlertUI alertUI;

    /**
     * The Postpone Entry Controller
     */
    private final PostponeEntryController postponeEntryController = new PostponeEntryController();

    /**
     * The GSMUI
     */
    private GSMUI gsmui;

    /**
     * The List of Agenda Entries
     */
    ObservableList<String> methods;

    /**
     * Instantiates a new Postpone Entry UI Controller.
     */
    public PostponeEntryUIController() {
        this.alertUI = new AlertUI();
    }

    /**
     * Instantiates a new Postpone Entry UI Controller.
     *
     * @param agenda               the agenda
     * @param greenSpaceRepository the green space repository
     */
    public PostponeEntryUIController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.alertUI = new AlertUI();
    }

    /**
     * Sets the Postpone Entry UI
     * @param postponeEntryUI the Postpone Entry UI
     */
    public void setPostponeEntryUI(PostponeEntryUI postponeEntryUI) {
    }

    /**
     * The Button to cancel an entry
     */
    @FXML
    private Button btnCancel;

    /**
     * The Button to postpone an entry
     */
    @FXML
    private Button btnPostpone;

    /**
     * The ComboBox to select the agenda entries
     */
    @FXML
    private ComboBox <String> cmbAgendaEntries;

    /**
     * The DatePicker to select the new date
     */
    @FXML
    private DatePicker btnDate;

    /**
     * Initializes the controller
     */
    @FXML
    public void initialize() {
        try{
            methods = FXCollections.observableArrayList((postponeEntryController.getAgendaEntryListString()));
            cmbAgendaEntries.setItems(methods);
            btnDate.setDisable(true);
            btnPostpone.setDisable(true);
        } catch (Exception e) {
            System.out.println("Error while loading the status list.");
        }
    }

    /**
     * Handles the cancel button action
     */
    public void handleCancelButtonAction() {
        try {
            gsmui = new GSMUI();
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    /**
     * Handles the postpone button action
     * @param actionEvent the action event
     */
    public void handlePostpone(ActionEvent actionEvent) {
        try {
            String selectedEntryName = cmbAgendaEntries.getValue();
            if (selectedEntryName == null || btnDate.getValue() == null) {
                alertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime","Postpone Entry Error", "Please select an agenda entry and a date to postpone it to.");
                return;
            }
            AgendaEntry selectedEntry = postponeEntryController.getAgendaEntryByTaskName(selectedEntryName);
            LocalDate newDate = btnDate.getValue();
            Date date = new Date(newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
            cmbAgendaEntries.getItems().setAll(selectedEntryName);

            postponeEntryController.postponeAgendaEntry(selectedEntry, date);
            Alert al = AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Musgo Sublime","Postpone Entry", "The entry has been successfully postponed!");
            al.showAndWait();
            closeWindow(actionEvent);
        } catch (Exception e) {
            alertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime","Postpone Entry Error", "An error occurred while postponing entry.");
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
     * Handles the date action
     * @param actionEvent the action event
     */
    public void handleDate(ActionEvent actionEvent) {
        AgendaEntry selectedEntry = postponeEntryController.getAgendaEntryByTaskName(cmbAgendaEntries.getValue());
        LocalDate newDate = btnDate.getValue();

        if (newDate == null){
            return;
        }
        Date date = new Date(newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());

        if (selectedEntry.getStartDate().isGreater(date)){
            btnPostpone.setDisable(true);
            btnDate.setValue(null);
            Alert al = AlertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime","Postpone Entry", "The date must be after the entry date!");
            al.showAndWait();
        } else {
            btnPostpone.setDisable(false);
        }

    }

    /**
     * Handles the ComboBox
     * @param actionEvent the action event
     */
    public void handleComboBox(ActionEvent actionEvent) {
        if (cmbAgendaEntries.getValue() != null){
            btnDate.setDisable(false);
        }
    }
}

