package pt.ipp.isep.dei.esoft.project.ui.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.MainMenuUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DevTeamController implements Initializable{
    public DevTeamUI devTeamUI;
    private MainMenuUI mainMenuUI;
    @FXML
    public Button goBackButton;

    @FXML
    public TableView<String> tvTeam;

    @FXML
    public TableColumn<String, String> name;

    @FXML
    public TableColumn<String, String> email;


    public void handleGoBackButtonAction() {
        try {
            mainMenuUI = new MainMenuUI();
            mainMenuUI.loadMainMenu();
        } catch (Exception e) {
            System.out.println("An error occurred while handling the go back action: " + e.getMessage());
        }
    }

    public void setDevTeamUI(DevTeamUI devTeamUI) {
        this.devTeamUI = devTeamUI;
    }

    private void populateTable(){
        name.setCellValueFactory(data -> javafx.beans.binding.Bindings.createObjectBinding(() -> data.getValue().split(",")[0]));
        email.setCellValueFactory(data -> javafx.beans.binding.Bindings.createObjectBinding(() -> data.getValue().split(",")[1]));

        ObservableList<String> members = FXCollections.observableArrayList(
                "Diogo Veiros, 1230462@isep.ipp.pt",
                "Diogo Pereira, 1230948@isep.ipp.pt",
                "José Alves, 1230630@isep.ipp.pt",
                "Tiago Sampaio, 1230917@isep.ipp.pt",
                "Miguel Gonçalves, 1221119@isep.ipp.pt"
        );

        tvTeam.setItems(members);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
        tvTeam.setSortPolicy(null);
        tvTeam.setEditable(false);
        tvTeam.setSelectionModel(null);

    }
}
