package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.beans.property.SimpleStringProperty;
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
    private ListMaintenanceController listMaintenanceController = new ListMaintenanceController();
    private FMUI fmUI;

    public void setListMaintenanceUI(ListMaintenanceUI listMaintenanceUI) {
        this.listMaintenanceUI = listMaintenanceUI;
    }

    /**
     * Last maintenance column
     */
    @FXML
    private TableColumn<VehicleDto,String> lastMaintenance;
    /**
     * TableView for the maintenance list
     */
    @FXML
    private TableView<VehicleDto> maintenanceTable;
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
    private TableColumn<VehicleDto,String> model;

    /**
     * Next maintenance column
     */
    @FXML
    private TableColumn<VehicleDto,String> nextMaintenance;

    /**
     * Plate number column
     */
    @FXML
    private TableColumn<VehicleDto,String> plateNumber;

    /**
     * Brand column
     */
    @FXML
    private TableColumn<VehicleDto,String> brand;

    /**
     * Current kms column
     */
    @FXML
    private TableColumn<VehicleDto,String> currentKms;

    /**
     * Frequency column
     */
    @FXML
    private TableColumn<VehicleDto,String> frequency;

    ObservableList<VehicleDto> vehicles;


    /**
     * Handle the action of showing the list
     */
    public void handleShowListAction() {
        List<VehicleDto> lstVehicles = this.listMaintenanceController.getVehicleList();
        try {
            maintenanceTable.getItems().clear();


            ObservableList<VehicleDto> vehicleData = FXCollections.observableArrayList(lstVehicles);

            lastMaintenance.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKmAtLastMaintenance().toString()));
            model.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
            nextMaintenance.setCellValueFactory(cellData -> {
                Double nextMaintenance = cellData.getValue().getKmAtLastMaintenance() + cellData.getValue().getServiceFrequency();
                if (cellData.getValue().getCurrentKms() > nextMaintenance) {
                    nextMaintenance = cellData.getValue().getCurrentKms();
                }
                return new SimpleStringProperty(nextMaintenance.toString());
            });
            plateNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlateNumber()));
            brand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
            currentKms.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurrentKms().toString()));
            frequency.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServiceFrequency().toString()));

            maintenanceTable.setItems(vehicleData);
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