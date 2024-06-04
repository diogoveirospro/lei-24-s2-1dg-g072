package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidAgendaEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.Date;

/**
 * This class serves as a way to add objects to the system so
 * the program doesn't start without anything.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Bootstrap {
    private final SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
    private final CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    private final VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    private final JobRepository jobRepository = Repositories.getInstance().getJobRepository();
    private final Agenda agenda = Repositories.getInstance().getAgenda();

    public void run() throws InvalidCollaboratorDataException, InvalidTaskDataException, InvalidAgendaEntryDataException, InvalidGreenSpaceDataException {
        addSkill();
        addJob();
        addCollaborator();
        addVehicle();
        addVehicleMaintenance();
        addUsers();
        addAgendaEntry();
    }

    private void addCollaborator() throws InvalidCollaboratorDataException {
        Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3),
                new Date(2010, 3, 1), "Rua1", "912345669", "ana@collaborator.com",
                "123456789", Collaborator.IdDocType.CC, "234564321zx7", "ABC1234");


        c1.assignSkill(new Skill("Sustainable Land Use Practices"));
        c1.assignSkill(new Skill("Ecological Restoration"));
        c1.assignSkill(new Skill("Landscape Design"));

        Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", "912345669", "joao@collaborator.com", "234567899",
                Collaborator.IdDocType.BI, "232566381", "ABC1234");


        c2.assignSkill(new Skill("Plant Identification"));
        c2.assignSkill(new Skill("Tree Care and Maintenance"));
        c2.assignSkill(new Skill("Native Plant Gardening"));

        Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010,
                3, 1), "Rua3", "912345669", "andre@collaborator.com", "345678907",
                Collaborator.IdDocType.NISS, "23456432125", "ABC1234");


        c3.assignSkill(new Skill("Plant Identification"));
        c3.assignSkill(new Skill("Native Plant Gardening"));
        c3.assignSkill(new Skill("Landscape Design"));

        Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2020,
                3, 1), "Rua4", "912345669", "manuel@collaborator.com", "456789014",
                Collaborator.IdDocType.PASSPORT, "H234564", "ABC1234");


        c3.assignSkill(new Skill("Plant Identification"));
        c3.assignSkill(new Skill("Tree Care and Maintenance"));
        c3.assignSkill(new Skill("Pest and Disease Management in Landscapes"));

        Collaborator hrm = new Collaborator("HRM", new Date(1999, 2, 3), new Date(2020,
                3, 1), "Rua5", "912632669", "hrm@hrm.com", "212826077",
                Collaborator.IdDocType.CC, "123454678cc3", "ABC1234");

        Collaborator gsm = new Collaborator("GSM", new Date(1988, 2, 3), new Date(2020,
                3, 1), "Rua6", "912645629", "gsm@gsm.com", "232139687",
                Collaborator.IdDocType.CC, "122472678cc3", "ABC1234");

        Collaborator vfm = new Collaborator("VFM", new Date(1989, 2, 3), new Date(2020,
                3, 1), "Rua7", "914657324", "vfm@vfm.com", "269999450",
                Collaborator.IdDocType.CC, "123453678zz3", "ABC1234");

        Collaborator qam = new Collaborator("QAM", new Date(1999, 2, 3), new Date(2024,
                3, 1), "Rua8", "913456734", "qam@qam.com", "241319692",
                Collaborator.IdDocType.CC, "123485678xx3", "ABC1234");


        collaboratorRepository.addCollaborator(c1);
        collaboratorRepository.addCollaborator(c2);
        collaboratorRepository.addCollaborator(c3);
        collaboratorRepository.addCollaborator(c4);
        collaboratorRepository.addCollaborator(hrm);
        collaboratorRepository.addCollaborator(gsm);
        collaboratorRepository.addCollaborator(vfm);
        collaboratorRepository.addCollaborator(qam);
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


    private void addUsers() throws InvalidCollaboratorDataException {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COL, AuthenticationController.ROLE_COL);

        for (Collaborator c : collaboratorRepository.getCollaborators()) {

            if (c.getEmail().contains("@hrm.com")){
                authenticationRepository.addUserWithRole(c, AuthenticationController.ROLE_HRM);
            } else if (c.getEmail().contains("@gsm.com")) {
                authenticationRepository.addUserWithRole(c, AuthenticationController.ROLE_GSM);
            } else if (c.getEmail().contains("@vfm.com")) {
                authenticationRepository.addUserWithRole(c, AuthenticationController.ROLE_VFM);
            } else if (c.getEmail().contains("@qam.com")) {
                authenticationRepository.addUserWithRole(c, AuthenticationController.ROLE_QAM);
            } else {
                authenticationRepository.addUserWithRole(c, AuthenticationController.ROLE_COL);
            }
        }

    }

    private void addVehicle() {
        Vehicle vehicle1 = new Vehicle("GG-69-EZ","BMW","i4","hibrid",3500.0,4500.0,1000.0,new Date(2024,1,10), new  Date(2024,1,26),10000.0,0.0);
        Vehicle vehicle2 = new Vehicle("69-WP-42","Toyota","Avensis","Diesel",3000.0,4000.0,42000.0,new Date(2018,12,10), new  Date(2019,1,10),20000.0,30000.0);
        Vehicle vehicle3 = new Vehicle("04-20-VC","ferrari","diablo","Petrol",3000.0,4000.0,100000.0,new Date(2000,12,10), new  Date(2003,10,11),10000.0,75432.3);
        vehicleRepository.addVehicle(vehicle1);
        vehicleRepository.addVehicle(vehicle2);
        vehicleRepository.addVehicle(vehicle3);
    }
    private void addVehicleMaintenance(){

    }

    private void addAgendaEntry() throws InvalidAgendaEntryDataException, InvalidTaskDataException, InvalidGreenSpaceDataException {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        TaskRepository taskRepository = Repositories.getInstance().getTaskRepository();
        Task task = new Task("Task", "14");
        taskRepository.addTask(task);
        GreenSpace greenSpace = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "GreenSpace description", 1000.0, "Address", collaboratorRepository.getCollaborator("H234564"));
        greenSpaceRepository.addGreenSpace(greenSpace);
        Entry entry = new Entry(taskRepository.findTaskById("Task"), greenSpaceRepository.getGreenSpaceByParkName("GreenSpace description"));
        AgendaEntry entry1 = new AgendaEntry(entry.getTask(), greenSpaceRepository.getGreenSpaceByParkName("GreenSpace description"), new Date(2021, 1, 1), AgendaEntry.HourOfDay.H01,new Date(2021, 1, 1), AgendaEntry.HourOfDay.H06);
        agenda.addAgendaEntry(entry1);
    }
}
