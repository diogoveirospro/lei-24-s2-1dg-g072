package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorTest {

    @Test
    public void testConstructorWithValidArguments() {
        String name = "João Silva";
        Date birthDate = new Date();
        Date admissionDate = new Date();
        String address = "Address";
        int mobile = 123456789;
        String email = "joao.silva@example.com";
        int taxpayerNumber = 123456789;
        String idDocType = "ID";
        int idDocNumber = 987654321;
        String jobName = "Gardener";


        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber, idDocType, idDocNumber, jobName);


        assertEquals(name, collaborator.getName());
        assertEquals(birthDate, collaborator.getBirthDate());
        assertEquals(admissionDate, collaborator.getAdmissionDate());
        assertEquals(address, collaborator.getAddress());
        assertEquals(mobile, collaborator.getMobile());
        assertEquals(email, collaborator.getEmail());
        assertEquals(taxpayerNumber, collaborator.getTaxpayerNumber());
        assertEquals(idDocType, collaborator.getIdDocType());
        assertEquals(idDocNumber, collaborator.getIdDocNumber());
        assertEquals(jobName, collaborator.getJob().getName());
    }

    @Test
    public void testConstructorWithInvalidArguments() {
        String name = "João Silva";
        Date birthDate = new Date();
        Date admissionDate = new Date();
        String address = "Address";
        int mobile = 123456789;
        String email = "joao.silva@example.com";
        int taxpayerNumber = 123456789;
        String idDocType = "ID";
        int idDocNumber = 987654321;
        String invalidJobName = "NonexistentJob";

        assertThrows(IllegalArgumentException.class, () -> new Collaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber, idDocType, idDocNumber, invalidJobName));
    }

    @Test
    public void testAssignSkill() {
        Collaborator collaborator = new Collaborator("João Silva", new Date(), new Date(), "Address", 123456789, "joao.silva@example.com", 123456789, "ID", 987654321, "Gardener");
        Skill skill = new Skill("Java");

        collaborator.assignSkill(skill);

        assertTrue(collaborator.getSkillSet().contains(skill));
    }


}
