package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.FileNotFoundException;
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
     * @throws FileNotFoundException if the file to write the email content is not found
     */
    @Override
    public void sendEmail(String email) throws FileNotFoundException {
        try {
            CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
            Collaborator collaborator = collaboratorRepository.getCollaboratorByEmail(email);

            File emailBox = new File("src/main/java/pt/ipp/isep/dei/esoft/project/EmailBox/Gmail.txt");

            PrintWriter writer = new PrintWriter(emailBox);
            writer.println("We inform employee " + collaborator.getEmail() + "  with ID " + collaborator.getIdDocNumber()
                    + " that he has a new task assigned to his team.");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}

