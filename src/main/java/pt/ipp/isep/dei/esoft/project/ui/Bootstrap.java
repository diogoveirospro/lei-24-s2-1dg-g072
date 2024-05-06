package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.Date;
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
