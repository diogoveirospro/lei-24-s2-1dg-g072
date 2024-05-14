package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.Date;

/**
 * This class serves as a way to add objects to the system so
 * the program doesn't start without anything.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Bootstrap {
    private SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
    private CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    private VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    private JobRepository jobRepository = Repositories.getInstance().getJobRepository();

    public void run() {
        addSkill();
        addJob();
        addCollaborator();
        addVehicle();
        addVehicleMaintenance();
        addUsers();
    }

    private void addCollaborator() {
        Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3), new Date(2010,
                3, 1), "Rua1", 912345669, "ana@gmail.com", 1644122,
                "CC", 234564321);

        c1.assignSkill(new Skill("Sustainable Land Use Practices"));
        c1.assignSkill(new Skill("Ecological Restoration"));
        c1.assignSkill(new Skill("Landscape Design"));

        Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", 912345669, "joao@gmail.com", 12345678,
                "CC", 234564321);

        c2.assignSkill(new Skill("Plant Identification"));
        c2.assignSkill(new Skill("Tree Care and Maintenance"));
        c2.assignSkill(new Skill("Native Plant Gardening"));

        Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010,
                3, 1), "Rua3", 912345669, "andre@gmail.com", 12345678,
                "CC", 234564321);

        c3.assignSkill(new Skill("Plant Identification"));
        c3.assignSkill(new Skill("Native Plant Gardening"));
        c3.assignSkill(new Skill("Landscape Design"));

        Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2015,
                3, 1), "Rua4", 912345669, "manuel@gmail.com", 12345678,
                "CC", 234564321);

        c3.assignSkill(new Skill("Plant Identification"));
        c3.assignSkill(new Skill("Tree Care and Maintenance"));
        c3.assignSkill(new Skill("Pest and Disease Management in Landscapes"));


        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);
        collaboratorRepository.addCollaborator(c3);
        collaboratorRepository.addCollaborator(c4);
    }

    private void addJob() {

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


    }
    private void addVehicleMaintenance(){

    }
}
