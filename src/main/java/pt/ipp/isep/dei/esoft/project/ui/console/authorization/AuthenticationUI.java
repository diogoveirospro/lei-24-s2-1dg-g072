package pt.ipp.isep.dei.esoft.project.ui.console.authorization;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.IOException;

public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;

    public AuthenticationUI() {
        this.ctrl = new AuthenticationController();
    }

    @Override
    public void run() {
        try {
            doLogin();
        } catch (InvalidCollaboratorDataException | IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            this.logout(); // Ensures logout is called regardless of login success
        }
    }

    private boolean doLogin() throws InvalidCollaboratorDataException, IOException {
        System.out.println("\n\n--- LOGIN UI ---------------------------");
        int maxAttempts = 3;
        while (maxAttempts > 0) {
            String email = Utils.readLineFromConsole("Enter UserId/Email: ");
            String password = Utils.readLineFromConsole("Enter Password: ");
            if (ctrl.loginConsole(email, password)) {
                return true;
            } else {
                maxAttempts--;
                System.out.println("Invalid UserId and/or Password. You have " + maxAttempts + " more attempt(s).");
            }
        }
        return false;
    }

    private void logout() {
        ctrl.logout();
    }
}
