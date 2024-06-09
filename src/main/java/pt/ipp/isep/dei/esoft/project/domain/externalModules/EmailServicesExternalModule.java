package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmailServicesExternalModule {
    void sendEmail(String email) throws IOException;
    void writeEmail(Collaborator collaborator) throws IOException;
}
