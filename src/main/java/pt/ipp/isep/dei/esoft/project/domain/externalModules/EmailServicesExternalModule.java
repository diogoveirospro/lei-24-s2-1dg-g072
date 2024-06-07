package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.io.FileNotFoundException;

public interface EmailServicesExternalModule {
    void sendEmail(String email) throws FileNotFoundException;
    void writeEmail(Collaborator collaborator) throws FileNotFoundException;
}
