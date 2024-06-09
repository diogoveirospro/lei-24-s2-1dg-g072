package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.AlertUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListGreenSpacesUI;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    private ComboBox<String> listSortMethod;
    @FXML
    private ListView<String> listGreenSpace;
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

    /**
     * Handles the action of displaying a confirmation dialog before listing all the green spaces.
     * If the user confirms the action, the method retrieves a list of green spaces based on the selected sorting method
     * and displays them in the ListView. If no sorting method is selected, an error alert is shown.
     */

    public void handleShowButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to list all the green spaces?");
        alert.setContentText("The data provided will be used to list green spaces, managed by you.");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                listGreenSpace.getItems().clear();
                String sortMethod = listSortMethod.getValue();
                if (sortMethod == null) {
                    AlertUI.createAnAlert(Alert.AlertType.ERROR, "Error", "Please select a sort method.","You need to select a sort method to proceed.").show();
                }
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
}
