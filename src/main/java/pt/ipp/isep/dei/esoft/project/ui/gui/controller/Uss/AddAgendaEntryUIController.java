package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.AddAgendaEntryController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.dto.ToDoListEntryDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.AddAgendaEntryUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing agenda entries.
 * This controller handles the creation and addition of agenda entries, as well as retrieving green spaces and to-do list entries.
 */
public class AddAgendaEntryUIController {

    public Button btnAddAgendaEntry;
    public Button btnCancel;
    @FXML
    private ComboBox<String> cbGreenSpace;

    @FXML
    private ComboBox<String> cbTask;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private ComboBox<String> cbStartHour;

    @FXML
    private ComboBox<String> cbEndHour;

    AddAgendaEntryController controller = new AddAgendaEntryController();
    AddAgendaEntryUI addAgendaEntryUI = new AddAgendaEntryUI();

    /**
     * Populates the combo box with green space names.
     */
    public void populateGreenSpacesComboBox() {
        List<GreenSpaceDto> greenSpaceDtoList = controller.getListGreenSpaces();
        cbGreenSpace.getItems().addAll(greenSpaceDtoList.stream().map(GreenSpaceDto::getParkName).collect(Collectors.toList()));
    }

    /**
     * Populates the combo box with to-do list entries based on the selected green space.
     */
    public void populateToDoListEntriesComboBox() {
        cbTask.getItems().clear();
        String selectedGreenSpaceName = cbGreenSpace.getSelectionModel().getSelectedItem();
        if (selectedGreenSpaceName != null) {
            GreenSpaceDto selectedGreenSpaceDto = controller.getGreenSpaceByName(selectedGreenSpaceName);
            if (selectedGreenSpaceDto != null) {
                GreenSpace selectedGreenSpace = (GreenSpace) controller.toDomain(selectedGreenSpaceDto);

                try {
                    List<ToDoListEntryDto> toDoListEntryDtoList = controller.getToDoListEntries(selectedGreenSpace);
                    cbTask.getItems().addAll(toDoListEntryDtoList.stream()
                            .map(entry -> String.format("%s - Duration: %s - Urgency: %s", entry.getTaskName(), entry.getDuration(), entry.getUrgency()))
                            .collect(Collectors.toList()));
                } catch (NullPointerException e) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Add Agenda Entry", "Error.", "No tasks found for the selected green space.").show();
                }


            } else {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Add Agenda Entry", "Error.", "Green space not found.").show();
            }
        } else {
            AlertUI.createAnAlert(Alert.AlertType.ERROR, "Add Agenda Entry", "Error.", "Please select a green space first.").show();
        }
    }


    /**
     * Gets the start date from the date picker.
     *
     * @return The selected start date.
     */
    private LocalDate getStartDate() {
        return dpStartDate.getValue();
    }

    /**
     * Gets the end date from the date picker.
     *
     * @return The selected end date.
     */
    private LocalDate getEndDate() {
        return dpEndDate.getValue();
    }

    /**
     * Populates the combo boxes for start and end hours.
     */
    public void populateHoursComboBox() {
        for (int i = 0; i < 24; i++) {
            cbStartHour.getItems().add(i + ":00");
            cbEndHour.getItems().add(i + ":00");
        }
    }

    /**
     * Handles the action of adding a new agenda entry.
     */
    @FXML
    public void handleAddAgendaEntry() {

        boolean added = false;
        AgendaEntry agendaEntry = null;

        try {
            GreenSpaceDto greenSpaceDto = controller.getGreenSpaceByName(cbGreenSpace.getSelectionModel().getSelectedItem());
            ToDoListEntryDto toDoListEntryDto = controller.getToDoListEntry(cbTask.getSelectionModel().getSelectedItem(), greenSpaceDto);

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
        } catch (InvalidEntryDataException e) {
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
                } catch (InvalidEntryDataException e) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Add Agenda Entry", "Error.", e.getMessage()).show();
                }
            }
        }
    }

    /**
     * Handles the action of canceling and returning to the main UI.
     */
    @FXML
    public void handleCancel() {
        GSMUI gsmui = new GSMUI();
        try {
            gsmui.showUI(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAddAgendaEntryUI (AddAgendaEntryUI addAgendaEntryUI) {
        this.addAgendaEntryUI = addAgendaEntryUI;
    }

    @FXML
    public void initialize() {
        populateGreenSpacesComboBox();
        populateHoursComboBox();

        cbGreenSpace.setOnAction(event -> populateToDoListEntriesComboBox());
    }
}
