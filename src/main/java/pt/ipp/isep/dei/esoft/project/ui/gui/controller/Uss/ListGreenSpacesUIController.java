package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListGreenSpacesUI;

import java.util.List;

public class ListGreenSpacesUIController {
    private final ListGreenSpacesController listGreenSpacesController = new ListGreenSpacesController();
    private ListGreenSpacesUI listGreenSpacesUI;
    private GSMUI gsmui;


    public void setListGreenSpacesUI(ListGreenSpacesUI listGreenSpacesUI) {
        this.listGreenSpacesUI = listGreenSpacesUI;
    }
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnShow;
    @FXML
    private ComboBox listSortMethod;
    @FXML
    private ListView listGreenSpace;
    /**
     * List of all the tasks
     */
    private ObservableList<String> methods;

    /**
     * Initializes the ui attributes
     */
    @FXML
    public void initialize() {
        try{
            methods = FXCollections.observableArrayList((listGreenSpacesController.getSortMethods()));
            listSortMethod.setItems(methods);

        } catch (Exception e) {
            System.out.println("Error while loading the methods list.");
        }
    }
    /**
     * Handles the cancel button action
     */
    public void handleCancelButtonAction() {
        try {
            gsmui = new GSMUI();
            gsmui.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }
    public void handleShowButtonAction() {
        try {
            listGreenSpace.getItems().clear();
            String sortMethod = (String) listSortMethod.getValue();
            List<GreenSpaceDto> greenSpaces = listGreenSpacesController.getGreenSpaceList(sortMethod);
            for (GreenSpaceDto greenSpace : greenSpaces) {
                String toBeShown = "GreenSpace " + greenSpace.getParkName() + " of the type " + greenSpace.getType().toString() + " and with an area of " + greenSpace.getDimension() + " ha.";
                listGreenSpace.getItems().add(toBeShown);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while handling the show action: " + e.getMessage());
        }
    }
}
