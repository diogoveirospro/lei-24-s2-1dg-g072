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
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.PostponeEntryUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PostponeEntryUIController {

    private AlertUI alertUI;

    private final PostponeEntryController postponeEntryController = new PostponeEntryController();

    private GSMUI gsmui;

    ObservableList<String> methods;

    public PostponeEntryUIController() {
        this.alertUI = new AlertUI();
    }

    public PostponeEntryUIController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.alertUI = new AlertUI();
    }

    public void setPostponeEntryUI(PostponeEntryUI postponeEntryUI) {
    }

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPostpone;

    @FXML
    private ComboBox <String> cmbAgendaEntries;

    @FXML
    private DatePicker btnDate;

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


    public void closeWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GSMUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

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

    public void handleComboBox(ActionEvent actionEvent) {
        if (cmbAgendaEntries.getValue() != null){
            btnDate.setDisable(false);
        }
    }
}

