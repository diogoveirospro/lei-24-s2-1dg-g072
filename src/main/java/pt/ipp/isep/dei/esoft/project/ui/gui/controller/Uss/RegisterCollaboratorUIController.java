package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterCollaboratorUI;

public class RegisterCollaboratorUIController {
    private HRMUI hrmui;
    private RegisterCollaboratorUI registerCollaboratorUI;
    private RegisterCollaboratorController registerCollaboratorController;
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
    private ComboBox jobComboBox;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField taxNumberField;

    @FXML
    private Button btnCancel;


    public void handleRegisterAction() {
        try {
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || pswField.getText().isEmpty() || idTypeField.getText().isEmpty() || idNumberField.getText().isEmpty() || adressField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || taxNumberField.getText().isEmpty() || jobComboBox.getValue().toString().isEmpty() || birthDatePicker.getValue().toString().isEmpty() || admissionDatePicker.getValue().toString().isEmpty()) {
                throw new InvalidCollaboratorDataException("All fields must be filled.");
            }
            String name = nameField.getText();
            String email = emailField.getText();
            String psw = pswField.getText();
            String idType = idTypeField.getText();
            String idNumber = idNumberField.getText();
            String address = adressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String taxNumber = taxNumberField.getText();
            String job = jobComboBox.getValue().toString();
            String birthDateStr = birthDatePicker.getValue().toString();
            String admissionDateStr = admissionDatePicker.getValue().toString();
            Collaborator.IdDocType idDocType = Collaborator.IdDocType.valueOf(idType);

            String[] dateParts = birthDateStr.split("-");
            int year1 = Integer.parseInt(dateParts[0]);
            int month1 = Integer.parseInt(dateParts[1]);
            int day1 = Integer.parseInt(dateParts[2]);

            Date birthDate = new Date(year1, month1, day1);
            dateParts = admissionDateStr.split("-");
            int year2 = Integer.parseInt(dateParts[0]);
            int month2 = Integer.parseInt(dateParts[1]);
            int day2 = Integer.parseInt(dateParts[2]);

            Date admissionDate = new Date(year2, month2, day2);
            Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, phoneNumber, email, taxNumber, idDocType, idNumber, job, psw);
            if (birthDate.compareTo(admissionDate) > 0) {
                throw new InvalidCollaboratorDataException("The birth date must be before the admission date.");
            } else {
                registerCollaboratorController.registerCollaborator(name, birthDate, admissionDate, address, phoneNumber, email, taxNumber, idDocType, idNumber, job, psw);
            }

        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("The birth date must be before the admission date.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("All fields must be filled.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", "All fields must be filled.");
            } else if (e.getMessage().equalsIgnoreCase("Invalid email.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid taxpayer number.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid mobile number.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid ID document number.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid name.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid password.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid birthdate.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else if (e.getMessage().equalsIgnoreCase("Invalid admission date.")) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage());
            } else {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", "An error occurred while registering the collaborator.");
            }
        }
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
