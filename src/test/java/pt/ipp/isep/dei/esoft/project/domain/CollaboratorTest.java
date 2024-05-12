package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        // Arrange
        String name = "John Doe";
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate admissionDate = LocalDate.of(2024, 5, 1);
        String address = "123 Main St, City, Country";
        String mobile = "123456789";
        String email = "john.doe@example.com";
        String taxpayerNumber = "123456789";
        String idDocType = "Citizen Card";
        String idDocNumber = "987654321";

        // Act
        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, mobile, email,
                taxpayerNumber, idDocType, idDocNumber);

        // Assert
        assertNotNull(collaborator);
        assertEquals(name, collaborator.getName());
        assertEquals(birthDate, collaborator.getBirthDate());
        assertEquals(admissionDate, collaborator.getAdmissionDate());
        assertEquals(address, collaborator.getAddress());
        assertEquals(mobile, collaborator.getMobile());
        assertEquals(email, collaborator.getEmail());
        assertEquals(taxpayerNumber, collaborator.getTaxpayerNumber());
        assertEquals(idDocType, collaborator.getIdDocType());
        assertEquals(idDocNumber, collaborator.getIdDocNumber());
    }

    @Test
    void ensureCollaboratorTaxpayerNumberIsValid() {
        // Arrange
        String invalidTaxpayerNumber = "123";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Collaborator("John Doe", LocalDate.of(1990, 5, 15), LocalDate.of(2024, 5, 1),
                        "123 Main St, City, Country", "123456789", "john.doe@example.com",
                        invalidTaxpayerNumber, "Citizen Card", "987654321"));
    }

    @Test
    void ensureCollaboratorIdDocNumberIsValid() {
        // Arrange
        String invalidIdDocNumber = "123";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new Collaborator("John Doe", LocalDate.of(1990, 5, 15), LocalDate.of(2024, 5, 1),
                        "123 Main St, City, Country", "123456789", "john.doe@example.com",
                        "123456789", "Citizen Card", invalidIdDocNumber));
    }
}

