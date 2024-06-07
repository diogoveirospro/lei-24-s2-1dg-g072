package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterCollaboratorUI;

public class RegisterCollaboratorUIController {
    private HRMUI hrmui;
    private RegisterCollaboratorUI registerCollaboratorUI;
    @FXML
    private TextField phoneNumberField;

    @FXML
    private DatePicker admissionDatePicker;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField idNumberField;

    @FXML
    private TextField adressField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idTypeField;

    @FXML
    private TextField pswField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<?> jobComboBox;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField taxNumberField;

    @FXML
    private Button btnCancel;


    public void handleRegisterAction() {
        // TODO: implement here
    }

    public void handleCancelAction() {
        try {
            hrmui = new HRMUI();
            hrmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setRegisterCollaboratorUI(RegisterCollaboratorUI registerCollaboratorUI) {
        this.registerCollaboratorUI = registerCollaboratorUI;
    }
}
