package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() throws InvalidCollaboratorDataException {

        String name = "Ana";
        Date birthDate = new Date(1990, 2, 3);
        Date admissionDate = new Date(2010, 3, 1);
        String address = "Rua1";
        String mobile = "912345669";
        String email = "ana@gmail.com";
        String taxpayerNumber = "123456780";
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        String idDocNumber = "234564321zx7";

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
    void ensureCollaboratorIsCreatedSuccessfullyWithJob() throws InvalidCollaboratorDataException {

        String name = "Ana";
        Date birthDate = new Date(1990, 2, 3);
        Date admissionDate = new Date(2010, 3, 1);
        String address = "Rua1";
        String mobile = "912345669";
        String email = "ana@gmail.com";
        String taxpayerNumber = "123456780";
        Collaborator.IdDocType idDocType = Collaborator.IdDocType.CC;
        String idDocNumber = "234564321zx7";
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
