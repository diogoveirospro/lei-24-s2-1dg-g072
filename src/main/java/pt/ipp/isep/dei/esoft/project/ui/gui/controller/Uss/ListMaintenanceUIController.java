package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pt.ipp.isep.dei.esoft.project.application.controller.ListMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDto;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;

import pt.ipp.isep.dei.esoft.project.ui.gui.ui.FMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListMaintenanceUI;

import java.util.List;

public class ListMaintenanceUIController {
    private ListMaintenanceUI listMaintenanceUI;
    private ListMaintenanceController listMaintenanceController;
    private FMUI fmUI;

    public void setListMaintenanceUI(ListMaintenanceUI listMaintenanceUI) {
        this.listMaintenanceUI = listMaintenanceUI;
    }

    /**
     * Last maintenance column
     */
    @FXML
    private TableColumn lastMaintenance;
    /**
     * TableView for the maintenance list
     */
    @FXML
    private TableView maintenanceTable;
    /**
     * Model column
     */
    @FXML
    private Button btnShowList;

    /**
     * Back button
     */
    @FXML
    private Button btnBack;
    /**
     * Model column
     */

    @FXML
    private TableColumn model;

    /**
     * Next maintenance column
     */
    @FXML
    private TableColumn nextMaintenance;

    /**
     * Plate number column
     */
    @FXML
    private TableColumn plateNumber;

    /**
     * Brand column
     */
    @FXML
    private TableColumn brand;

    /**
     * Current kms column
     */
    @FXML
    private TableColumn currentKms;

    /**
     * Frequency column
     */
    @FXML
    private TableColumn frequency;

    ObservableList<VehicleDto> vehicles;


    /**
     * Handle the action of showing the list
     */
    public void handleShowListAction() {
        List<VehicleDto> lstVehicles = this.listMaintenanceController.getVehicleList();
        vehicles = FXCollections.observableArrayList(lstVehicles);
        try {
            maintenanceTable.setItems(vehicles);
        } catch (Exception e) {
            System.out.println("An error occurred while handling the show list action: " + e.getMessage());
        }
    }
    /**
     * Handle the action of going back
     */
    public void handleBackButtonAction(){
        try {
            fmUI = new FMUI();
            fmUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

}