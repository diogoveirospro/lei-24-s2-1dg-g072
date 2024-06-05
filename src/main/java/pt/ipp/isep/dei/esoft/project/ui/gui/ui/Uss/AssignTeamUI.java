package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AddAgendaEntryUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AssignTeamUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AssignTeamUI implements Initializable {
    public static final String ASSIGNTEAM = "Assign Team to Agenda Entry";
    public AssignTeamUIController assignTeamUIController;


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AssignTeamToAgendaEntry.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(ASSIGNTEAM);
        stage.setScene(scene);
        assignTeamUIController = loader.getController();
        assignTeamUIController.setAssignTeamUI(new AssignTeamUI());
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
