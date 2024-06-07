package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.AddAgendaEntryUIController;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.GenerateTeamProposalUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenerateTeamProposalUI implements Initializable {

    public static final String GENERATETEAMPROPOSAL = "Generate Team Proposal";
    public GenerateTeamProposalUIController generateTeamProposalUIController;


    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/GenerateTeamProposal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(GENERATETEAMPROPOSAL);
        stage.setScene(scene);
        generateTeamProposalUIController = loader.getController();
        generateTeamProposalUIController.setGenerateTeamProposal (new GenerateTeamProposalUI());
        stage.show();
    }

    /**
     * Initializes the UI components.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
