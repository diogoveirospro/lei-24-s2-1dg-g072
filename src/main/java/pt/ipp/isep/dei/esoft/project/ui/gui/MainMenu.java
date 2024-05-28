package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

        public void start(Stage primaryStage) {
            Button loginButton = new Button("Login");
            Button devTeamButton = new Button("Development Team");

            VBox vBox = new VBox();
            vBox.getChildren().addAll(loginButton, devTeamButton);

            loginButton.setOnAction(event -> {
                Login login = new Login();
                login.start(primaryStage);
            });

            devTeamButton.setOnAction(event -> {
                //DevTeam devTeam = new DevTeam();
                //devTeam.start(primaryStage);
            });

            Scene scene = new Scene(vBox, 300, 200);
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
}
