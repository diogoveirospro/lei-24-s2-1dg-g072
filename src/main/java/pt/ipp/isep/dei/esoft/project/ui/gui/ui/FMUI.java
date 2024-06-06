package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.FMController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FMUI implements Initializable {
    public MainMenuUI mainMenuUI;
    public FMController fmController;
    public final String FM = "FM";
    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VFMUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(FM);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setScene(scene);
        fmController = loader.getController();
        fmController.setFmui(new FMUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public FMController getFmController() {
        return fmController;
    }
}
