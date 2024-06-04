package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class AuthenticationRepository {
    private final AuthFacade authenticationFacade;

    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        authenticationFacade.doLogout();
    }

    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    public boolean addUserWithRole(Collaborator collaborator, String roleId) {
        return authenticationFacade.addUserWithRole(collaborator.getName(), collaborator.getEmail(), collaborator.getPwd(), roleId);
    }
}