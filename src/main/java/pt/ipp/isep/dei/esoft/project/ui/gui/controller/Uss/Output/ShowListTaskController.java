package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.Output;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss.Output.ShowTaskListUI;

public class ShowListTaskController {
    private CollaboratorUI collaboratorUI;
    private ShowTaskListUI showTaskListUI;
    /**
     * Button to cancel
     */
    @FXML
    private Button btnCancel;

    /**
     * List of all the tasks
     */
    @FXML
    private ListView listTasks;

    public void handleCancelButtonAction() {
        try {
            collaboratorUI = new CollaboratorUI();
            collaboratorUI.showUI(MainMenuUI.getPrimaryStage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the cancel action: " + e.getMessage());
        }
    }

    public void setShowTaskListUI(ShowTaskListUI showTaskListUI) {
        this.showTaskListUI = showTaskListUI;
    }
}
