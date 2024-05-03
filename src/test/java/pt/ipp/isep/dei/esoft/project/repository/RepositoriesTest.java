package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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