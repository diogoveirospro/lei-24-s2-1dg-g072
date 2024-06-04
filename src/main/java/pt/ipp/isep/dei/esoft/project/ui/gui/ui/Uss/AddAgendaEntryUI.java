package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.Mapper.ToDoListMapper;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.dto.ToDoListEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AddAgendaEntryUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAgendaEntryUI implements Initializable {

    @FXML
    private ComboBox<GreenSpaceDto> cbGreenSpace;

    @FXML
    private ComboBox<ToDoListEntryDto> cbTask;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private ComboBox<String> cbStartHour;

    @FXML
    private ComboBox<String> cbEndHour;

    @FXML
    private Button btnAddAgendaEntry;

    @FXML
    private Button btnCancel;


    public static final String ADDAGENDAENTRY = "GSM | Add New Entry to the Agenda";


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AddAgendaEntry.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(ADDAGENDAENTRY);
        stage.setScene(scene);
        AddAgendaEntryUIController addAgendaEntryUIController = loader.getController();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateGreenSpacesComboBox();
        populateToDoListEntriesComboBox();
        populateHoursComboBox();

    }

    private void populateGreenSpacesComboBox() {
        AddAgendaEntryUIController controller = new AddAgendaEntryUIController();
        List<GreenSpaceDto> greenSpaceDtoList = controller.getListGreenSpaces();
        cbGreenSpace.getItems().addAll(greenSpaceDtoList);
    }

    private void populateToDoListEntriesComboBox() {
        GreenSpaceDto greenSpaceDto = cbGreenSpace.getSelectionModel().getSelectedItem();
        AddAgendaEntryUIController controller = new AddAgendaEntryUIController();
        List<ToDoListEntryDto> toDoListEntryDtoList = controller.getToDoListEntries(greenSpaceDto);
        cbTask.getItems().addAll(toDoListEntryDtoList);
    }

    private LocalDate getStartDate() {
        return dpStartDate.getValue();
    }

    private LocalDate getEndDate() {
        return dpEndDate.getValue();
    }

    private void populateHoursComboBox() {
        for (int i = 0; i < 24; i++) {
            cbStartHour.getItems().add(i + ":00");
            cbEndHour.getItems().add(i + ":00");
        }
    }

    @FXML
    public void handleAddAgendaEntry() {

        boolean added = false;
        AddAgendaEntryUIController controller = new AddAgendaEntryUIController();
        AgendaEntry agendaEntry = null;

        try {
            GreenSpaceDto greenSpaceDto = cbGreenSpace.getSelectionModel().getSelectedItem();
            ToDoListEntryDto toDoListEntryDto = cbTask.getSelectionModel().getSelectedItem();

            String startHour = cbStartHour.getSelectionModel().getSelectedItem();
            String endHour = cbEndHour.getSelectionModel().getSelectedItem();

            LocalDate startLocalDate = getStartDate();
            LocalDate endLocalDate = getEndDate();

            AgendaEntry.HourOfDay startHourOfDay = AgendaEntry.HourOfDay.fromString(startHour);
            AgendaEntry.HourOfDay endHourOfDay = AgendaEntry.HourOfDay.fromString(endHour);

            Task task = controller.getTask(toDoListEntryDto);
            GreenSpace greenSpace = (GreenSpace) controller.toDomain(greenSpaceDto);
            Date startDate = new Date(startLocalDate.getYear(), startLocalDate.getMonthValue(), startLocalDate.getDayOfMonth());
            Date endDate = new Date(endLocalDate.getYear(), endLocalDate.getMonthValue(), endLocalDate.getDayOfMonth());

            agendaEntry = controller.createAgendaEntry(task, greenSpace, startDate, startHourOfDay, endDate, endHourOfDay);
            added = controller.addAgendaEntry(agendaEntry);

            GSMUI gsmui = new GSMUI();
            gsmui.showUI(new Stage());
        } catch (InvalidAgendaEntryDataException e) {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Add Agenda Entry", "Error.", e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (added) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to add this agenda entry?");
            alert.setContentText("The data provided will be used to create the new entry.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                try {
                    controller.addAgendaEntry(agendaEntry);
                } catch (InvalidAgendaEntryDataException e) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Add Agenda Entry", "Error.", e.getMessage()).show();
                }
            } else {
                //permitirAlterarDados();
            }
        }

    }

    @FXML
    public void handleCancel() {
        GSMUI gsmui = new GSMUI();
        try {
            gsmui.showUI(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
