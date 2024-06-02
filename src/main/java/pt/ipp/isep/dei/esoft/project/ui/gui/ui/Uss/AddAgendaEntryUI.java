package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    }
}
