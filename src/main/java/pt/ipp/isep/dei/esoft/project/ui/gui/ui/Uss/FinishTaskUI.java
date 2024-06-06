package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.FinishTaskUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinishTaskUI implements Initializable {

    public FinishTaskUIController finishTaskUIController;
    public MainMenuUI mainMenuUI;
    public static final String TITLE = "Finish Task";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/FinishTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        finishTaskUIController = loader.getController();
        finishTaskUIController.setFinishTaskUI(new FinishTaskUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization if required
    }

    public FinishTaskUIController getFinishTaskController() {
        return finishTaskUIController;
    }
}
