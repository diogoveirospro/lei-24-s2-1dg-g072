package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DevTeamUI implements Initializable {

    public void viewTeamAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        DevTeamUI devTeamUI = new DevTeamUI();
        devTeamUI.viewTeamAction();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
