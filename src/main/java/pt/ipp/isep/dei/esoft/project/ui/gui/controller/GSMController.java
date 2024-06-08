package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.*;

import java.util.List;

public class GSMController {
    public GSMUI gsmui;
    public ListGreenSpacesUI listGreenSpacesUI;
    public PostponeEntryUI postponeEntryUI;
    public RegisterGreenSpaceUI registerGreenSpaceUI;
    public CancelEntryUI cancelEntryUI;
    public AssignTeamUI assignTeamUI;
    public AssignVehicleUI assignVehicleUI;
    public AddAgendaEntryUI addAgendaEntryUI;
    public AddToDoListEntryUI addToDoListEntryUI;
    public MainMenuUI mainMenuUI;
    @FXML
    public Button LogOutButton;
    @FXML
    public Button RegisterGreenSpaceButton;
    @FXML
    public Button ListGreenSpacesButton;
    @FXML
    public Button PostponeEntryButton;
    @FXML
    public Button CancelEntryButton;
    @FXML
    public Button AssignTeamButton;
    @FXML
    public Button AssignVehicleButton;
    @FXML
    public Button AddAgendaEntryButton;
    @FXML
    public Button AddToDoListEntryButton;


    public void setGsmui(GSMUI gsmui) {
        this.gsmui = gsmui;
    }
    public void handleLogoutButtonAction() {
        try {

            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
    public void handleRegisterGreenSpaceButtonAction() {
        try {
            registerGreenSpaceUI = new RegisterGreenSpaceUI();
            registerGreenSpaceUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the register green space action: " + e.getMessage());
        }
    }
    public void handleListGreenSpacesButtonAction() {
        try {
            listGreenSpacesUI = new ListGreenSpacesUI();
            listGreenSpacesUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the list green spaces action: " + e.getMessage());
        }
    }
    public void handlePostponeEntryButtonAction() {
        try {
            postponeEntryUI = new PostponeEntryUI();
            postponeEntryUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the postpone entry action: " + e.getMessage());
        }
    }
    public void handleCancelEntryButtonAction() {
        try {
            cancelEntryUI = new CancelEntryUI();
            cancelEntryUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the cancel entry action: " + e.getMessage());
        }
    }
    public void handleAssignTeamButtonAction() {
        try {
            assignTeamUI = new AssignTeamUI();
            assignTeamUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the assign team action: " + e.getMessage());
        }
    }
    public void handleAssignVehicleButtonAction() {
        try {
            assignVehicleUI = new AssignVehicleUI();
            assignVehicleUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the assign vehicle action: " + e.getMessage());
        }
    }
    public void handleAddAgendaEntryButtonAction() {
        try {
            addAgendaEntryUI = new AddAgendaEntryUI();
            addAgendaEntryUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the add agenda entry action: " + e.getMessage());
        }
    }
    public void handleAddToDoListEntryButtonAction() {
        try {
            addToDoListEntryUI = new AddToDoListEntryUI();
            addToDoListEntryUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the add to do list entry action: " + e.getMessage());
        }
    }
}
