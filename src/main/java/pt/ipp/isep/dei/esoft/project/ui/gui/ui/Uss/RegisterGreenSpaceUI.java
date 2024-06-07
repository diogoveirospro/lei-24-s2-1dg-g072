package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace.TypeOfGreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterCollaboratorUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.RegisterGreenSpaceUIController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * RegisterGreenSpaceUI class handles the UI operations related to registering a new Green Space.
 * It interacts with repositories to store and retrieve data.
 *
 */
public class RegisterGreenSpaceUI implements Initializable {

    private RegisterGreenSpaceUIController controller;
    private static final String REGISTER_JOB = "Register Green Space";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/RegisterGreenSpace.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(REGISTER_JOB);
        stage.setScene(scene);
        controller = loader.getController();
        controller.setRegisterGreenSpaceUI(new RegisterGreenSpaceUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
