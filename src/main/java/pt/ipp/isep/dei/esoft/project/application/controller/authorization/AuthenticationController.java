package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.GsmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HrmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VfmUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.*;

import java.io.IOException;

public class AuthenticationController {

    /**
     * Logs in a user.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return true if login is successful, false otherwise
     * @throws InvalidCollaboratorDataException if the user's role is invalid
     */
    public boolean login(String email, String password) throws InvalidCollaboratorDataException, IOException {
        if (AuthenticationRepository.authenticate(email, password)) {
            String role = AuthenticationRepository.getAuthenticatedUserRole();
            switch (role) {
                case "HRM":
                    new HRMUI().showUI(MainMenuUI.getPrimaryStage());
                    break;
                case "GSM":
                    new GSMUI().showUI(MainMenuUI.getPrimaryStage());
                    break;
                case "COLLABORATOR":
                    new CollaboratorUI().showUI(MainMenuUI.getPrimaryStage());
                    break;
                case "VFM":
                    new FMUI().showUI(MainMenuUI.getPrimaryStage());
                    break;
                default:
                    throw new InvalidCollaboratorDataException("Invalid role.");
            }
            return true;
        }
        return false;
    }

    public boolean loginConsole(String email, String password) throws InvalidCollaboratorDataException, IOException {
        if (AuthenticationRepository.authenticate(email, password)) {
            String role = AuthenticationRepository.getAuthenticatedUserRole();
            switch (role) {
                case "HRM":
                    new HrmUI().run();
                    break;
                case "VFM":
                    new VfmUI().run();
                    break;
                default:
                    throw new InvalidCollaboratorDataException("Invalid role.");
            }
            return true;
        }
        return false;
    }

    /**
     * Logs out the authenticated user.
     */
    public void logout() {
        AuthenticationRepository.logout();
    }

    /**
     * Gets the email of the authenticated user.
     *
     * @return the email of the authenticated user
     */
    public String getAuthenticatedUserEmail() {
        return AuthenticationRepository.getAuthenticatedUserEmail();
    }

    /**
     * Gets the role of the authenticated user.
     *
     * @return the role of the authenticated user
     */
    public String getAuthenticatedUserRole() {
        return AuthenticationRepository.getAuthenticatedUserRole();
    }
}
