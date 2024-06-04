package pt.ipp.isep.dei.esoft.project.ui.gui.ui.Uss;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.Mapper.AgendaEntryMapper;
import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss.PostponeEntryUIController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PostponeEntryUI implements Initializable {

    private final Agenda agenda;

    private PostponeEntryUIController postponeEntryUIController;

    public static final String POSTPONE = "Postpone Entry";

    public PostponeEntryUI() {
        this.agenda = Repositories.getInstance().getAgenda();
    }

    public PostponeEntryUI(Agenda agenda) {
        this.agenda = agenda;
    }

    public void showUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/USs/PostponeEntry.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle(POSTPONE);
        stage.setScene(scene);
        postponeEntryUIController = loader.getController();
        postponeEntryUIController.setPostponeEntryUI(new PostponeEntryUI());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
