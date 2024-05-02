package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class Bootstrap {
    public void run() {
        addSkill();
        addJob();
        addCollaborator();
        addVehicle();
        addVehicleMaintenance();
        addUsers();
    }

    private void addCollaborator() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        Collaborator newCollaborator = new Collaborator("CR7","5 de fevereiro de 1985", "10 de janeiro de 2000", "Madeira", 911223445,"sui@gmail.com","CC", 32214643);
        collaboratorRepository.addCollaborator(newCollaborator);
    }

    private void addJob() {
    }

    private void addSkill() {

    }


    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

    }

    private void addVehicle() {

    }
    private void addVehicleMaintenance(){

    }
}
