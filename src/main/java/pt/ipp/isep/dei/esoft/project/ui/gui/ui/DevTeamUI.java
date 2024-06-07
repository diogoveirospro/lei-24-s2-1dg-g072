package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.DevTeamController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DevTeamUI implements Initializable {

    public DevTeamController devTeamController;
    public MainMenuUI mainMenuUI;
    public static final String DEVTEAM = "DevTeam";


    public void viewTeamAction(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(DEVTEAM);
        stage.setScene(scene);
        devTeamController = loader.getController();
        devTeamController.setDevTeamUI(new DevTeamUI());
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
