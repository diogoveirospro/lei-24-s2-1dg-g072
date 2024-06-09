package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * GmailEmailService class represents an implementation of the EmailServicesExternalModule interface
 * that sends emails using the Gmail email service.
 */
public class GmailEmailService implements EmailServicesExternalModule {

    /**
     * Sends an email to the specified email address using the Gmail email service.
     *
     * @param email the email address of the recipient
     */
    @Override
    public void sendEmail(String email) throws IOException {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        Collaborator collaborator = collaboratorRepository.getCollaboratorByEmail(email);
        writeEmail(collaborator);
    }

    /**
     * Writes an email to the specified collaborator using the Gmail email service.
     *
     * @param collaborator the collaborator to send the email to
     */
    @Override
    public void writeEmail(Collaborator collaborator) throws IOException {
        File emailBox = new File("src/main/java/pt/ipp/isep/dei/esoft/project/EmailBox/Gmail.txt");
        try (FileWriter fw = new FileWriter(emailBox, true); PrintWriter writer = new PrintWriter(fw)) {

            writer.println("From: gsm@gmail.com");
            writer.println("To: " + collaborator.getEmail());
            writer.println("Subject: New Task Assigned");
            writer.println();
            writer.println("Dear " + collaborator.getName() + ",");
            writer.println("Musgo Sublime's green space management team informs the collaborator with ID Number "
                    + collaborator.getIdDocNumber() + " that their team has been assigned to a new agenda entry.");
            writer.println("Please check the agenda for more details.");
            writer.println();
            writer.println("Best regards, GSM Team");
            writer.println("This is an automated email. Please do not reply.");
            writer.println("-------------------------------------------------");

        } catch (IOException e) {
            throw new IOException("An error occurred while writing to the file", e);
        }
    }
}
