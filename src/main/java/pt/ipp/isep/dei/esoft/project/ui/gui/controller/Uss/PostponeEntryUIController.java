package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeEntryController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.PostponeEntryUI;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PostponeEntryUIController {

    private final Agenda agenda;

    private GreenSpaceRepository greenSpaceRepository;

    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

    private final List<AgendaEntry> agendaEntries = getAgendaEntryList();

    private final PostponeEntryController postponeEntryController = new PostponeEntryController();

    private PostponeEntryUI postponeEntryUI;

    private GSMUI gsmui;

    public PostponeEntryUIController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    public PostponeEntryUIController(Agenda agenda, GreenSpaceRepository greenSpaceRepository) {
        this.agenda = agenda;
        this.greenSpaceRepository = greenSpaceRepository;
    }

    public List<GreenSpaceDto> getListGreenSpaces() {
        Collaborator GSM = getCollaboratorFromSession();
        List<GreenSpace> listGreenSpaces = greenSpaceRepository.getListGreenSpacesManagedByGsm(GSM);

        GreenSpaceMapper greenSpaceMapper = new GreenSpaceMapper();

        return greenSpaceMapper.greenSpaceListToDto(listGreenSpaces);
    }

    private Collaborator getCollaboratorFromSession() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();

        return collaboratorRepository.getCollaboratorByEmail(email);
    }

    public List<AgendaEntry> getAgendaEntryList(){
        return Objects.requireNonNull(agenda).getEntryList();
    }

    public List<AgendaEntryDto> entryListToDto(){
        AgendaEntryMapper agendaEntryMapper = new AgendaEntryMapper();
        return agendaEntryMapper.toDtoList(agendaEntries);
    }

    public void setPostponeEntryUI(PostponeEntryUI postponeEntryUI) {
        this.postponeEntryUI = postponeEntryUI;
    }

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPostpone;

    @FXML
    private ComboBox cmbAgendaEntries;

    @FXML
    private DatePicker date;

    @FXML
    public void initialize() {
        try{

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

    public void postpone(ActionEvent actionEvent) {
        try {
            if (cmbAgendaEntries.getSelectionModel().isEmpty() || date.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select an agenda entry and a date to postpone it to.");
                return;
            }
            LocalDate newDate = date.getValue();
            Date date = new Date(newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
            AgendaEntry agendaEntry = (AgendaEntry) cmbAgendaEntries.getSelectionModel().getSelectedItem();
            postponeEntryController.postponeAgendaEntry(agendaEntry, date);
        } catch (Exception e) {
            System.out.println("An error occurred while handling the postpone action: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}

