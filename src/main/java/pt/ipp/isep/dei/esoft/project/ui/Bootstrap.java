package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

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
        Collaborator collaborator1 = new Collaborator("LM10", new Date(1987, 6, 24), new Date(2000, 9, 1), "Rosario", 911223446, "messi@gmail.com", "CC", 32214644);
        Collaborator collaborator2 = new Collaborator("Neymar", new Date(1992, 2, 5), new Date(2005, 1, 10), "Mogi das Cruzes", 911223447, "neymar@gmail.com", "CC", 32214645);
        Collaborator collaborator3 = new Collaborator("Mbappe", new Date(1998, 12, 20), new Date(2015, 3, 1), "Paris", 911223448, "mbappe@gmail.com", "CC", 32214646);
        Collaborator collaborator4 = new Collaborator("Messi", new Date(1987, 6, 24), new Date(2000, 9, 1), "Rosario", 911223449, "messi10@gmail.com", "CC", 32214647);
        Collaborator collaborator5 = new Collaborator("Suarez", new Date(1987, 1, 24), new Date(2000, 9, 1), "Salto", 911223450, "suarez@gmail.com", "CC", 32214648);
        Collaborator collaborator6 = new Collaborator("Modric", new Date(1985, 9, 9), new Date(2000, 1, 10), "Zadar", 911223451, "modric@gmail.com", "CC", 32214649);
        Collaborator collaborator7 = new Collaborator("Ramos", new Date(1986, 3, 30), new Date(2000, 9, 1), "Camas", 911223452, "ramos@gmail.com", "CC", 32214650);
        Collaborator collaborator8 = new Collaborator("Kane", new Date(1993, 7, 28), new Date(2005, 1, 1), "Walthamstow", 911223453, "kane@gmail.com", "CC", 32214651);
        Collaborator collaborator9 = new Collaborator("Haaland", new Date(2000, 7, 21), new Date(2015, 1, 1), "Leeds", 911223454, "haaland@gmail.com", "CC", 32214652);
        Collaborator collaborator10 = new Collaborator("Ronaldo", new Date(1994, 7, 18), new Date(2010, 9, 1), "Sao Paulo", 911223455, "ronaldo@gmail.com", "CC", 32214653);
        collaboratorRepository.addCollaborator(collaborator1);
        collaboratorRepository.addCollaborator(collaborator2);
        collaboratorRepository.addCollaborator(collaborator3);
        collaboratorRepository.addCollaborator(collaborator4);
        collaboratorRepository.addCollaborator(collaborator5);
        collaboratorRepository.addCollaborator(collaborator6);
        collaboratorRepository.addCollaborator(collaborator7);
        collaboratorRepository.addCollaborator(collaborator8);
        collaboratorRepository.addCollaborator(collaborator9);
        collaboratorRepository.addCollaborator(collaborator10);
    }

    private void addJob() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        Job job1 = new Job("Landscape Designer");
        Job job2 = new Job("Arborist");
        Job job3 = new Job("Horticulturist");
        Job job4 = new Job("Botanist");
        Job job5 = new Job("Park Ranger");
        Job job6 = new Job("Ecologist");
        Job job7 = new Job("Groundskeeper");
        Job job8 = new Job("Sustainability Consultant");
        Job job9 = new Job("Urban Forester");
        Job job10 = new Job("Green Infrastructure Specialist");
        jobRepository.addJob(job1);
        jobRepository.addJob(job2);
        jobRepository.addJob(job3);
        jobRepository.addJob(job4);
        jobRepository.addJob(job5);
        jobRepository.addJob(job6);
        jobRepository.addJob(job7);
        jobRepository.addJob(job8);
        jobRepository.addJob(job9);
        jobRepository.addJob(job10);

    }

    private void addSkill() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        Skill skill1 = new Skill("Plant Identification");
        Skill skill2 = new Skill("Landscape Design");
        Skill skill3 = new Skill("Tree Care and Maintenance");
        Skill skill4 = new Skill("Soil Management");
        Skill skill5 = new Skill("Ecological Restoration");
        Skill skill6 = new Skill("Water Conservation Techniques");
        Skill skill7 = new Skill("Native Plant Gardening");
        Skill skill8 = new Skill("Pest and Disease Management in Landscapes");
        Skill skill9 = new Skill("Green Infrastructure Planning");
        Skill skill10 = new Skill("Sustainable Land Use Practices");
        skillRepository.addSkill(skill1);
        skillRepository.addSkill(skill2);
        skillRepository.addSkill(skill3);
        skillRepository.addSkill(skill4);
        skillRepository.addSkill(skill5);
        skillRepository.addSkill(skill6);
        skillRepository.addSkill(skill7);
        skillRepository.addSkill(skill8);
        skillRepository.addSkill(skill9);
        skillRepository.addSkill(skill10);

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
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

    }
    private void addVehicleMaintenance(){

    }
}
