package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterCollaboratorUI;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class RegisterCollaboratorUIController {
    private HRMUI hrmui;
    private RegisterCollaboratorUI registerCollaboratorUI;
    private RegisterCollaboratorController registerCollaboratorController = new RegisterCollaboratorController();
    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
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
    private ComboBox<String> idTypeCB;

    @FXML
    private TextField pswField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> jobComboBox;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField taxNumberField;

    @FXML
    private Button btnCancel;

    public void initialize() {
        List<Job> jobs = registerCollaboratorController.getJobs();
        for (Job job : jobs) {
            jobComboBox.getItems().add(job.getName());
        }

        idTypeCB.getItems().add(Collaborator.IdDocType.BI.getDisplayName());
        idTypeCB.getItems().add(Collaborator.IdDocType.CC.getDisplayName());
        idTypeCB.getItems().add(Collaborator.IdDocType.PASSPORT.getDisplayName());
        idTypeCB.getItems().add(Collaborator.IdDocType.NISS.getDisplayName());

    }

    public void handleRegisterAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to Register this collaborator?");
        alert.setContentText("The data provided will be used to register a collaborator.");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if (jobComboBox.getValue() == null || idTypeCB.getValue() == null || birthDatePicker.getValue() == null || admissionDatePicker.getValue() == null) {
                    throw new InvalidCollaboratorDataException("All fields must be filled.");
                }
                if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || pswField.getText().isEmpty() || idTypeCB.getValue().isEmpty() || idNumberField.getText().isEmpty() || adressField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || taxNumberField.getText().isEmpty() || jobComboBox.getValue().isEmpty() || birthDatePicker.getValue().toString().isEmpty() || admissionDatePicker.getValue().toString().isEmpty()) {
                    throw new InvalidCollaboratorDataException("All fields must be filled.");
                }
                String name = nameField.getText();
                String email = emailField.getText();
                String psw = pswField.getText();
                String idType = idTypeCB.getValue();
                String idNumber = idNumberField.getText();
                String address = adressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String taxNumber = taxNumberField.getText();
                String job = jobComboBox.getValue().toString();
                String birthDateStr = birthDatePicker.getValue().toString();
                String admissionDateStr = admissionDatePicker.getValue().toString();
                Collaborator.IdDocType idDocType = Collaborator.IdDocType.fromDisplayName(idType);
                ValidatorUtils.isValidName(name);
                ValidatorUtils.isValidEmail(email);
                ValidatorUtils.isValidAddress(address);
                ValidatorUtils.isValidTaxpayerNumber(taxNumber);
                ValidatorUtils.isValidMobileNumber(phoneNumber);
                ValidatorUtils.isValidDocumentNumber(idDocType, idNumber);
                ValidatorUtils.isValidPwd(psw);
                ValidatorUtils.validateBirthDate(birthDateStr);
                ValidatorUtils.isValidAddress(address);
                Job newJob = new Job(job);
                newJob.validateJobName(job);

                String[] dateParts = birthDateStr.split("-");
                int year1 = Integer.parseInt(dateParts[0]);
                int month1 = Integer.parseInt(dateParts[1]);
                int day1 = Integer.parseInt(dateParts[2]);
                Date birthDate = new Date(year1, month1, day1);
                ValidatorUtils.validateAdmissionDate(admissionDateStr, birthDate);


                dateParts = admissionDateStr.split("-");
                int year2 = Integer.parseInt(dateParts[0]);
                int month2 = Integer.parseInt(dateParts[1]);
                int day2 = Integer.parseInt(dateParts[2]);

                Date admissionDate = new Date(year2, month2, day2);


                registerCollaboratorController.registerCollaborator(name, birthDate, admissionDate, address, phoneNumber, email, taxNumber, idDocType, idNumber, job, psw);
                AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success", "Collaborator registered successfully.", "The collaborator was registered successfully.").show();
                hrmui = new HRMUI();
                hrmui.showUI(MainMenuUI.getPrimaryStage());


            } catch (InvalidCollaboratorDataException | IOException | IllegalArgumentException e) {
                AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error Registering Collaborator", e.getMessage()).show();
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
