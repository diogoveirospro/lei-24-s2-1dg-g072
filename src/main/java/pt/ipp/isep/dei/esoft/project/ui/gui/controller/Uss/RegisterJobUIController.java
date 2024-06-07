package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterJobUI;

import java.io.IOException;
import java.util.Optional;

public class RegisterJobUIController {
    private RegisterJobController registerJobController = new RegisterJobController();
    private HRMUI hrmui;
    private RegisterJobUI registerJobUI;
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtJob;


    public void handleRegisterAction() {
        String job = txtJob.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to Register this job?");
        alert.setContentText("The data provided will be used to register a job.");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Job newJob = new Job(job);
                newJob.validateJobName(job);
                if (job.isEmpty()) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Please write a job.", "You need to write a job to proceed.").show();
                } else{
                    registerJobController.registerJob(job);
                    AlertUI.createAnAlert(Alert.AlertType.INFORMATION, "Success", "Job registered successfully.", "The job was registered successfully.").show();
                    hrmui = new HRMUI();
                    hrmui.showUI(MainMenuUI.getPrimaryStage());
                }
            } catch (IllegalArgumentException | IOException e) {
                if (e.getMessage().equalsIgnoreCase("Job already exists!")) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering job. Job already exists.", e.getMessage()).show();
                } else if (e.getMessage().equalsIgnoreCase("Job cannot be null")) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering job. Job cannot be null.", e.getMessage()).show();
                } else if (e.getMessage().equalsIgnoreCase("Job can't have special characters")) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering job. Job needs to only have letters.", e.getMessage()).show();
                } else if (e.getMessage().equalsIgnoreCase("Job cannot be null")) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering job. Job cannot be null.", e.getMessage()).show();
                } else {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Error registering job.", e.getMessage()).show();
                }
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

    public void setRegisterJobUI(RegisterJobUI registerJobUI) {
        this.registerJobUI = registerJobUI;
    }
}
