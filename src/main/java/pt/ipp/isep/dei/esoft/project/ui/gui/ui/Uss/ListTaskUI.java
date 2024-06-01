package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.LoginController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.ListTaskController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LoginUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class serves as the ui for the functionality of listing all the tasks
 * that the collaborator has.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ListTaskUI implements Initializable {


    public ListTaskController listTaskController;
    public MainMenuUI mainMenuUI;
    public static final String LOGIN = "Login";


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/ListTasks.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(LOGIN);
        stage.setScene(scene);
        listTaskController = loader.getController();
        listTaskController.setListTaskUI(new ListTaskUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ListTaskController getListTaskController() {
        return listTaskController;
    }
}
