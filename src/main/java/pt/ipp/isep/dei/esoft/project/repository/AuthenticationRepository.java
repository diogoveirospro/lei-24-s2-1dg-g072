package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for managing authentication and user sessions.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class AuthenticationRepository implements Serializable {
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, String> roles = new HashMap<>();
    private static String authenticatedUserEmail;

    private static AuthenticationRepository instance;

    /**
     * Adds a user to the repository.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the role of the user
     */
    public static void addUser(String email, String password, String role) {
        users.put(email, password);
        roles.put(email, role);
    }

    /**
     * Authenticates a user.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return true if authentication is successful, false otherwise
     */
    public static boolean authenticate(String email, String password) {
        if (users.containsKey(email) && users.get(email).equals(password)) {
            authenticatedUserEmail = email;
            return true;
        }
        return false;
    }

    /**
     * Logs out the authenticated user.
     */
    public static void logout() {
        authenticatedUserEmail = null;
    }

    /**
     * Gets the email of the authenticated user.
     *
     * @return the email of the authenticated user, or null if no user is authenticated
     */
    public static String getAuthenticatedUserEmail() {
        return authenticatedUserEmail;
    }

    /**
     * Gets the role of the authenticated user.
     *
     * @return the role of the authenticated user, or null if no user is authenticated
     */
    public static String getAuthenticatedUserRole() {
        return roles.get(authenticatedUserEmail);
    }
}
