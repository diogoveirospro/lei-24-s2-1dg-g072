package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRepositoryTest {

    @AfterEach
    void tearDown() {
        AuthenticationRepository.logout();
    }

    @Test
    void testAddUser() {
        AuthenticationRepository.addUser("test@example.com", "password", "admin");
        assertTrue(AuthenticationRepository.authenticate("test@example.com", "password"));
        assertEquals("admin", AuthenticationRepository.getAuthenticatedUserRole());
    }

    @Test
    void testAuthenticateSuccess() {
        AuthenticationRepository.addUser("test@example.com", "password", "admin");
        assertTrue(AuthenticationRepository.authenticate("test@example.com", "password"));
        assertEquals("test@example.com", AuthenticationRepository.getAuthenticatedUserEmail());
    }

    @Test
    void testAuthenticateFailure() {
        AuthenticationRepository.addUser("test@example.com", "password", "admin");
        assertFalse(AuthenticationRepository.authenticate("test@example.com", "wrongpassword"));
        assertNull(AuthenticationRepository.getAuthenticatedUserEmail());
    }

    @Test
    void testLogout() {
        AuthenticationRepository.addUser("test@example.com", "password", "admin");
        AuthenticationRepository.authenticate("test@example.com", "password");
        AuthenticationRepository.logout();
        assertNull(AuthenticationRepository.getAuthenticatedUserEmail());
        assertNull(AuthenticationRepository.getAuthenticatedUserRole());
    }

    @Test
    void testGetAuthenticatedUserEmail() {
        AuthenticationRepository.addUser("test@example.com", "password", "admin");
        AuthenticationRepository.authenticate("test@example.com", "password");
        assertEquals("test@example.com", AuthenticationRepository.getAuthenticatedUserEmail());
    }

    @Test
    void testGetAuthenticatedUserRole() {
        AuthenticationRepository.addUser("test@example.com", "password", "admin");
        AuthenticationRepository.authenticate("test@example.com", "password");
        assertEquals("admin", AuthenticationRepository.getAuthenticatedUserRole());
    }

    @Test
    void testGetAuthenticatedUserRoleNoUserAuthenticated() {
        assertNull(AuthenticationRepository.getAuthenticatedUserRole());
    }
}
