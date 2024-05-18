package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {

        String name = "John Doe";
        Date birthDate = new Date(1990, 5, 15);
        Date admissionDate = new Date(2024, 5, 1);
        String address = "123 Main St, City, Country";
        int mobile = 123456789;
        String email = "john.doe@example.com";
        int taxpayerNumber = 123456789;
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        String idDocNumber = "987654321";

        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, mobile, email,
                taxpayerNumber, idDocType, idDocNumber);

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
    void ensureCollaboratorIsCreatedSuccessfullyWithJob() {

        String name = "John Doe";
        Date birthDate = new Date(1990, 5, 15);
        Date admissionDate = new Date(2024, 5, 1);
        String address = "Rua1";
        int mobile = 123456789;
        String email = "john.doe@example.com";
        int taxpayerNumber = 123456789;
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        String idDocNumber = "987654321";
        String jobName = "Landscape Designer";

        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.addJob(new Job("Landscape Designer"));

        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, mobile, email,
                taxpayerNumber, idDocType, idDocNumber, jobName);

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
        assertEquals(new Job("Landscape Designer"), collaborator.getJob());
    }
}
