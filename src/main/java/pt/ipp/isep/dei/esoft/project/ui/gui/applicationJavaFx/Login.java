package pt.ipp.isep.dei.esoft.project.ui.gui.applicationJavaFx;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login {

    public void start(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        loginButton.setOnAction(event -> {
            String username = userField.getText();
            String password = passField.getText();
            if (validateLogin(username, password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        });

        Scene scene = new Scene(gridPane, 300, 200);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateLogin(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
}
