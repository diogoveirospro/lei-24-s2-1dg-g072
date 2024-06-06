package pt.ipp.isep.dei.esoft.project.application.session;

/**
 * Represents a user session.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class UserSession {
    private String userEmail;
    private String userRole;

    public UserSession(String userEmail, String userRole) {
        this.userEmail = userEmail;
        this.userRole = userRole;
    }

    /**
     * Gets the user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Gets the user role.
     *
     * @return the user role
     */
    public String getUserRole() {
        return userRole;
    }
}
