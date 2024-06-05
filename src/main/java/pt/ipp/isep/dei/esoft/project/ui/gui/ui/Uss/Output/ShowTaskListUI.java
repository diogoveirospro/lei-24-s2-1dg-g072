package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.Output;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.Output.ShowListTaskController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.CancelEntryUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.ListTaskUI;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowTaskListUI implements Initializable {


    private ShowListTaskController showListTaskController;

    public static final String LIST = "List of Tasks";
    public ListTaskUI listTaskUI;

    public ListTaskUI getListTaskUI() {
        return listTaskUI;
    }




    public void showUI(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/Output/ShowListTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(LIST);
        stage.setScene(scene);
        showListTaskController = loader.getController();
        showListTaskController.setShowTaskListUI(new ShowTaskListUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
