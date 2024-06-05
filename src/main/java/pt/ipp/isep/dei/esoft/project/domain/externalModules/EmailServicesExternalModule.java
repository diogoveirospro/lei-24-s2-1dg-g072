package pt.ipp.isep.dei.esoft.project.domain.externalModules;

import java.io.FileNotFoundException;

public interface EmailServicesExternalModule {
    void sendEmail(String email) throws FileNotFoundException;
}
