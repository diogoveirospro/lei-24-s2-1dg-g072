package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.LoginController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.ListGreenSpacesUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LoginUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListGreenSpacesUI implements Initializable {

    public CollaboratorUI collaboratorUI;
    public ListGreenSpacesUIController listGreenSpacesUIController;
    public static final String LISTGS = "List Green Spaces";


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/ListGreenSpaces.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(LISTGS);
        stage.setScene(scene);
        listGreenSpacesUIController = loader.getController();
        listGreenSpacesUIController.setListGreenSpacesUI(new ListGreenSpacesUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ListGreenSpacesUIController getListGreenSpacesUIController() {
        return listGreenSpacesUIController;
    }
}
