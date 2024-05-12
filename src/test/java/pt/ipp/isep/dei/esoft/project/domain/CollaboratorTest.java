package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        // Arrange
        String name = "John Doe";
        Date birthDate = new Date(1990, 5, 15);
        Date admissionDate = new Date(2024, 5, 1);
        String address = "123 Main St, City, Country";
        int mobile = 123456789;
        String email = "john.doe@example.com";
        int taxpayerNumber = 123456789;
        String idDocType = "Citizen Card";
        int idDocNumber = 987654321;

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

}

