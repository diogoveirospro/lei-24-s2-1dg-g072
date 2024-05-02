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
        Collaborator collaborator1 = new Collaborator("LM10", "24 de junho de 1987", "1 de setembro de 2000", "Rosario", 911223446, "messi@gmail.com", "CC", 32214644);
        Collaborator collaborator2 = new Collaborator("Neymar", "5 de fevereiro de 1992", "10 de janeiro de 2005", "Mogi das Cruzes", 911223447, "neymar@gmail.com", "CC", 32214645);
        Collaborator collaborator3 = new Collaborator("Mbappe", "20 de dezembro de 1998", "1 de março de 2015", "Paris", 911223448, "mbappe@gmail.com", "CC", 32214646);
        Collaborator collaborator4 = new Collaborator("Messi", "24 de junho de 1987", "1 de setembro de 2000", "Rosario", 911223449, "messi10@gmail.com", "CC", 32214647);
        Collaborator collaborator5 = new Collaborator("Suarez", "24 de janeiro de 1987", "1 de setembro de 2000", "Salto", 911223450, "suarez@gmail.com", "CC", 32214648);
        Collaborator collaborator6 = new Collaborator("Modric", "9 de setembro de 1985", "10 de janeiro de 2000", "Zadar", 911223451, "modric@gmail.com", "CC", 32214649);
        Collaborator collaborator7 = new Collaborator("Ramos", "30 de março de 1986", "1 de setembro de 2000", "Camas", 911223452, "ramos@gmail.com", "CC", 32214650);
        Collaborator collaborator8 = new Collaborator("Kane", "28 de julho de 1993", "1 de janeiro de 2005", "Walthamstow", 911223453, "kane@gmail.com", "CC", 32214651);
        Collaborator collaborator9 = new Collaborator("Haaland", "21 de julho de 2000", "1 de janeiro de 2015", "Leeds", 911223454, "haaland@gmail.com", "CC", 32214652);
        Collaborator collaborator10 = new Collaborator("Ronaldo", "18 de julho de 1994", "1 de setembro de 2010", "Sao Paulo", 911223455, "ronaldo@gmail.com", "CC", 32214653);
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
        /*
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        Date date1 = new Date(2014,10,15);
        Date date2 = new Date(2015,1,15);

        Vehicle vehicle1 = new Vehicle("AA-01-BB", "John Deere", "Gator", "Electric", 1200.0, 20.0, 25000.0, date1, date2, 5000.0, 20000.0);
        Vehicle vehicle2 = new Vehicle("CC-02-DD", "Husqvarna", "Rider", "Gasoline", 1100.0, 25.0, 28000.0, date1, date2, 4500.0, 25000.0);
        Vehicle vehicle3 = new Vehicle("EE-03-FF", "Kubota", "Tractor", "Diesel", 1500.0, 30.0, 30000.0, date2, date1, 6000.0, 28000.0);
        Vehicle vehicle4 = new Vehicle("GG-04-HH", "Toyota", "Pickup", "Hybrid", 1400.0, 22.0, 27000.0, date2, date1, 5500.0, 22000.0);
        Vehicle vehicle5 = new Vehicle("II-05-JJ", "Honda", "ATV", "Gasoline", 1000.0, 18.0, 20000.0, date2, date1, 4000.0, 18000.0);
        Vehicle vehicle6 = new Vehicle("KK-06-LL", "Ford", "Truck", "Diesel", 1800.0, 35.0, 35000.0, date2, date1, 7000.0, 32000.0);
        Vehicle vehicle7 = new Vehicle("MM-07-NN", "John Deere", "Mower", "Electric", 900.0, 15.0, 18000.0, date2, date1, 3500.0, 15000.0);
        Vehicle vehicle8 = new Vehicle("OO-08-PP", "Kawasaki", "UTV", "Gasoline", 1300.0, 25.0, 26000.0, date1, date2, 4800.0, 24000.0);
        Vehicle vehicle9 = new Vehicle("QQ-09-RR", "Yamaha", "Trimmer", "Electric", 800.0, 12.0, 15000.0, date1, date2, 3000.0, 13000.0);
        Vehicle vehicle10 = new Vehicle("SS-10-TT", "Chevrolet", "Van", "Gasoline", 1600.0, 28.0, 32000.0, date1, date2, 6500.0, 30000.0);
        vehicleRepository.addVehicle(vehicle1);
        vehicleRepository.addVehicle(vehicle2);
        vehicleRepository.addVehicle(vehicle3);
        vehicleRepository.addVehicle(vehicle4);
        vehicleRepository.addVehicle(vehicle5);
        vehicleRepository.addVehicle(vehicle6);
        vehicleRepository.addVehicle(vehicle7);
        vehicleRepository.addVehicle(vehicle8);
        vehicleRepository.addVehicle(vehicle9);
        vehicleRepository.addVehicle(vehicle10);
        */
    }
    private void addVehicleMaintenance(){

    }
}
