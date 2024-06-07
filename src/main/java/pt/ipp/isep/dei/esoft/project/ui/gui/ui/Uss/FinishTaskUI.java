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

/**
 * This class serves as the UI for recording the completion of a task.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class FinishTaskUI implements Initializable {

    public FinishTaskUIController finishTaskUIController;
    public MainMenuUI mainMenuUI;
    public static final String FINISH_TASK = "Finish Task";

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/FinishTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(FINISH_TASK);
        stage.setScene(scene);
        finishTaskUIController = loader.getController();
        finishTaskUIController.setFinishTaskUI(this);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public FinishTaskUIController getFinishTaskController() {
        return finishTaskUIController;
    }
}

