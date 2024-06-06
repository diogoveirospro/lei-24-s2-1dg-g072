package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListMaintenanceUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.PostponeEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleMaintenanceUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.RegisterVehicleUI;

public class FMController {
    public FMUI fmui;
    public RegisterVehicleUI registerVehicleUI;
    public ListMaintenanceUI listMaintenanceUI;
    public RegisterVehicleMaintenanceUI registerVehicleMaintenanceUI;
    public MainMenuUI mainMenuUI;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnListMaintenance;

    @FXML
    private Button btnRegisterMaintenance;

    @FXML
    private Button btnRegisterVehicle;


    public void handleRegisterVehicleAction() {
        try {
            registerVehicleUI = new RegisterVehicleUI();
            registerVehicleUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the postpone entry action: " + e.getMessage());
        }
    }

    public void handleRegisterMaintenanceAction() {
        try {
            registerVehicleMaintenanceUI = new RegisterVehicleMaintenanceUI();
            registerVehicleMaintenanceUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the postpone entry action: " + e.getMessage());
        }
    }

    public void handleListMaintenanceAction() {
        try {
            listMaintenanceUI = new ListMaintenanceUI();
            listMaintenanceUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while handling the postpone entry action: " + e.getMessage());
        }
    }


    public void handleLogoutButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the logout action: " + e.getMessage());
        }
    }


    public void setFmui(FMUI fmui) {
        this.fmui = fmui;
    }
}
