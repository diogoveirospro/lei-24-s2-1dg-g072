/*package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelEntryController;
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
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.CancelEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.PostponeEntryUI;

import java.time.LocalDate;
import java.util.List;

public class CancelEntryUIController {
    private final Agenda agenda;

    private GreenSpaceRepository greenSpaceRepository;

    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

    private List<AgendaEntry> agendaEntries;

    private AlertUI alertUI;

    private final CancelEntryController cancelEntryController = new CancelEntryController();

    private CancelEntryUI cancelEntryUI;

    private GSMUI gsmui;

    ObservableList<String> methods;

    public CancelEntryUIController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        this.alertUI = new AlertUI();
    }

    public CancelEntryUIController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.agenda = agenda;
        this.greenSpaceRepository = greenSpaceRepository;
        this.alertUI = new AlertUI();
    }

    public CancelEntryUIController(Agenda agenda) {
        this.agenda = agenda;
    }

    public void setCancelEntryUI(CancelEntryUI cancelEntryUI) {
        this.cancelEntryUI = cancelEntryUI;
    }

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPostpone;

    @FXML
    private ComboBox<String> cmbAgendaEntries;

    @FXML
    private DatePicker date;

    @FXML
    public void initialize() {
        try{
            //methods = FXCollections.observableArrayList((cancelEntryController.getAgendaEntryListString()));
            cmbAgendaEntries.setItems(methods);
        } catch (Exception e) {
            System.out.println("Error while loading the status list.");
        }
    }

    /**
     * Handles the cancel button action
     */
 /*   public void handleCancelButtonAction() {
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
            if (selectedEntryName == null || date.getValue() == null) {
                alertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime","Postpone Entry Error", "Please select an agenda entry and a date to postpone it to.");
                return;
            }
            AgendaEntry selectedEntry = cancelEntryController.getAgendaEntryByTaskName(selectedEntryName);
            LocalDate newDate = date.getValue();
            Date date = new Date(newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
            cmbAgendaEntries.getItems().setAll(selectedEntryName);

            cancelEntryController.postponeAgendaEntry(selectedEntry, date);
            closeWindow(actionEvent);
        } catch (Exception e) {
            alertUI.createAnAlert(Alert.AlertType.ERROR, "Musgo Sublime","Postpone Entry Error", "An error occurred while postponing entry.");        }
    }

    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();}
}*/
