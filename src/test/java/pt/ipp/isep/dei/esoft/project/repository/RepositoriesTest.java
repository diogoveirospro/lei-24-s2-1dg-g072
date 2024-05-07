package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The RepositoriesTest class contains unit tests for repository classes within the application.
 * It ensures that the repository classes behave as expected by testing various methods
 * for adding, retrieving, and managing data related to domain entities.
 * This class tests the functionality of CollaboratorRepository, SkillRepository, and other repositories.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetJobRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getJobRepository());
    }

    @Test
    void testGetSkillRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getSkillRepository());
    }

    @Test
    void testGetCollaboratorRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getCollaboratorRepository());
    }

    @Test
    void testGetTeamRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTeamRepository());
    }

    @Test
    void testGetVehicleRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getVehicleRepository());
    }

    @Test
    void testGetMaintenanceRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getMaintenanceRepository());
    }

    @Test
    void testGetAuthenticationRepository(){
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAuthenticationRepository());
    }

}