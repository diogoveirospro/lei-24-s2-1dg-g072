package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AddToDoListEntryUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AssignTeamUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddToDoListEntryUI implements Initializable {
    public static final String TO_DO_LIST = "Add Entry to To Do List";
    public AddToDoListEntryUIController addToDoListEntryUIController;


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/AddEntryToDoList.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(TO_DO_LIST);
        stage.setScene(scene);
        addToDoListEntryUIController = loader.getController();
        addToDoListEntryUIController.setAddToDoListEntryUI(new AddToDoListEntryUI());
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
