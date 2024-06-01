package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.FinishTaskUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListTaskUI;

public class CollaboratorController {
    public CollaboratorUI collaboratorUI;
    public MainMenuUI mainMenuUI;
    public ListTaskUI listTaskUI;
    public FinishTaskUI finishTaskUI;

    @FXML
    public Button btnListTasks;

    @FXML
    public Button btnFinishTask;

    @FXML
    public Button btnLogout;


    public void handleLogoutButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
    public void handleListTasksButtonAction() {
        try {
            listTaskUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the register collaborator action: " + e.getMessage());
        }
    }
    public void handleFinishTaskButtonAction() {
        try {
            finishTaskUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the register collaborator action: " + e.getMessage());
        }
    }
    public void setCollaboratorUI(CollaboratorUI collaboratorUI) {
        this.collaboratorUI = collaboratorUI;
    }
}
