package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Manages the application session.
 *
 * @autor Group 072 - Byte Masters - ISEP
 */
public class ApplicationSession {
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config/config.properties";
    private static final String COMPANY_DESIGNATION = "Company.Designation";

    private Properties properties;
    private static ApplicationSession singleton = null;

    private ApplicationSession() {
        properties = loadProperties();
    }

    /**
     * Gets the current session.
     *
     * @return the current user session
     */
    public UserSession getCurrentSession() {
        String authenticatedUserEmail = AuthenticationRepository.getAuthenticatedUserEmail();
        String authenticatedUserRole = AuthenticationRepository.getAuthenticatedUserRole();

        if (authenticatedUserEmail == null) {
            return null; // No user is authenticated
        }

        return new UserSession(authenticatedUserEmail, authenticatedUserRole);
    }

    /**
     * Loads the properties from the configuration file.
     *
     * @return the properties
     */
    private Properties loadProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(COMPANY_DESIGNATION, "MusgoSublime");

        // Read configured values
        try (InputStream in = new FileInputStream(CONFIGURATION_FILENAME)) {
            props.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }

    /**
     * Gets the singleton instance of the application session.
     *
     * @return the application session instance
     */
    public static ApplicationSession getInstance() {
        if (singleton == null) {
            synchronized (ApplicationSession.class) {
                if (singleton == null) {
                    singleton = new ApplicationSession();
                }
            }
        }
        return singleton;
    }

    /**
     * Gets the company designation from the properties.
     *
     * @return the company designation
     */
    public String getCompanyDesignation() {
        return properties.getProperty(COMPANY_DESIGNATION);
    }
}
