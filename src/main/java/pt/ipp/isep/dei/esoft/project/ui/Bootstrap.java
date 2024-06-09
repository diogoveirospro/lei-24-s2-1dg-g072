package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidEntryDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidGreenSpaceDataException;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidTaskDataException;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.util.List;

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
    private final TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
    private final GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    private final ToDoList toDoList = Repositories.getInstance().getToDoList();

    public void run() throws InvalidCollaboratorDataException, InvalidTaskDataException, InvalidEntryDataException, InvalidGreenSpaceDataException {
        if (collaboratorRepository.getCollaborators().isEmpty()) {
            addCollaborator();
        }

        if (jobRepository.getJobs().isEmpty()) {
            addJob();
        }

        if (skillRepository.listSkills().isEmpty()) {
            addSkill();
        }

        if (vehicleRepository.getVehicleList().isEmpty()) {
            addVehicle();
        }

        if (teamRepository.getTeams().isEmpty()) {
            addTeam();
        }

        if (greenSpaceRepository.getGreenSpaceList().isEmpty()) {
            addGreenSpaces();
        }

        if (toDoList.getToDoListEntries().isEmpty()) {
            addToDoListEntry();
        }

        if (agenda.getEntryList().isEmpty()) {
            addAgendaEntry();
        }

        addUsers();
    }

    private void addToDoListEntry() throws InvalidTaskDataException, InvalidEntryDataException {
        Task task3 = new Task("Task Three", "3");
        Task task4 = new Task("Task Four", "4");
        Task task5 = new Task("Task Five", "5");
        Task task6 = new Task("Task Six", "6");
        Task task7 = new Task("Task Seven", "7");
        Task task8 = new Task("Task Eight", "8");
        Task task9 = new Task("Task Nine", "9");
        Task task10 = new Task("Task Ten", "10");
        Task task11 = new Task("Task Eleven", "11");
        Task task12 = new Task("Task Twelve", "12");

        GreenSpace greenSpace1 = greenSpaceRepository.getGreenSpaceByParkName("Cidade");
        GreenSpace greenSpace2 = greenSpaceRepository.getGreenSpaceByParkName("São Roque");

        ToDoListEntry toDoListEntry1 = new ToDoListEntry(task3, greenSpace1, ToDoListEntry.DegreeOfUrgency.LOW);
        ToDoListEntry toDoListEntry2 = new ToDoListEntry(task4, greenSpace1, ToDoListEntry.DegreeOfUrgency.MEDIUM);
        ToDoListEntry toDoListEntry3 = new ToDoListEntry(task5, greenSpace1, ToDoListEntry.DegreeOfUrgency.HIGH);
        ToDoListEntry toDoListEntry4 = new ToDoListEntry(task6, greenSpace1, ToDoListEntry.DegreeOfUrgency.LOW);
        ToDoListEntry toDoListEntry5 = new ToDoListEntry(task7, greenSpace1, ToDoListEntry.DegreeOfUrgency.MEDIUM);
        ToDoListEntry toDoListEntry6 = new ToDoListEntry(task8, greenSpace2, ToDoListEntry.DegreeOfUrgency.HIGH);
        ToDoListEntry toDoListEntry7 = new ToDoListEntry(task9, greenSpace2, ToDoListEntry.DegreeOfUrgency.LOW);
        ToDoListEntry toDoListEntry8 = new ToDoListEntry(task10, greenSpace2, ToDoListEntry.DegreeOfUrgency.MEDIUM);
        ToDoListEntry toDoListEntry9 = new ToDoListEntry(task11, greenSpace2, ToDoListEntry.DegreeOfUrgency.HIGH);
        ToDoListEntry toDoListEntry10 = new ToDoListEntry(task12, greenSpace2, ToDoListEntry.DegreeOfUrgency.LOW);

        toDoList.addEntry(toDoListEntry1);
        toDoList.addEntry(toDoListEntry2);
        toDoList.addEntry(toDoListEntry3);
        toDoList.addEntry(toDoListEntry4);
        toDoList.addEntry(toDoListEntry5);
        toDoList.addEntry(toDoListEntry6);
        toDoList.addEntry(toDoListEntry7);
        toDoList.addEntry(toDoListEntry8);
        toDoList.addEntry(toDoListEntry9);
        toDoList.addEntry(toDoListEntry10);

        greenSpace1.getToDoList().addEntry(toDoListEntry1);
        greenSpace1.getToDoList().addEntry(toDoListEntry2);
        greenSpace1.getToDoList().addEntry(toDoListEntry3);
        greenSpace1.getToDoList().addEntry(toDoListEntry4);
        greenSpace1.getToDoList().addEntry(toDoListEntry5);
        greenSpace2.getToDoList().addEntry(toDoListEntry6);
        greenSpace2.getToDoList().addEntry(toDoListEntry7);
        greenSpace2.getToDoList().addEntry(toDoListEntry8);
        greenSpace2.getToDoList().addEntry(toDoListEntry9);
        greenSpace2.getToDoList().addEntry(toDoListEntry10);
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


    private void addUsers() throws InvalidCollaboratorDataException {

        Collaborator hrm1 = new Collaborator("HRM One", new Date(1999, 2, 3), new Date(2020,
                3, 1), "Rua5", "912632669", "hrm1@hrm.com", "212826077",
                Collaborator.IdDocType.CC, "123454678VV3", "ABC1234");

        Collaborator hrm2 = new Collaborator("HRM Two", new Date(2000, 2, 3), new Date(2020,
                3, 1), "Rua6", "912453649", "hrm2@hrm.com", "253272653",
                Collaborator.IdDocType.CC, "123454678DD3", "ABC1234");

        Collaborator gsm1 = new Collaborator("GSM One", new Date(1988, 2, 3), new Date(2020,
                3, 1), "Rua7", "912645629", "gsm1@gsm.com", "232139687",
                Collaborator.IdDocType.CC, "122472678AA3", "ABC1234");

        Collaborator gsm2 = new Collaborator("GSM Two", new Date(1983, 2, 3), new Date(2020,
                3, 1), "Rua8", "912235629", "gsm2@gsm.com", "290148510",
                Collaborator.IdDocType.CC, "122472678BB3", "ABC1234");

        Collaborator vfm1 = new Collaborator("VFM One", new Date(1989, 2, 3), new Date(2020,
                3, 1), "Rua9", "914657324", "vfm1@vfm.com", "269999450",
                Collaborator.IdDocType.CC, "123453678ZZ3", "ABC1234");

        Collaborator vfm2 = new Collaborator("VFM Two", new Date(1970, 2, 3), new Date(2020,
                3, 1), "Rua10", "914651224", "vfm2@vfm.com", "209072156",
                Collaborator.IdDocType.CC, "123453678CC3", "ABC1234");

        Collaborator qam = new Collaborator("QAM", new Date(1999, 2, 3), new Date(2024,
                3, 1), "Rua11", "913456734", "qam@qam.com", "241319692",
                Collaborator.IdDocType.CC, "123485678XX3", "ABC1234");

        collaboratorRepository.addCollaborator(hrm1);
        collaboratorRepository.addCollaborator(hrm2);
        collaboratorRepository.addCollaborator(gsm1);
        collaboratorRepository.addCollaborator(gsm2);
        collaboratorRepository.addCollaborator(vfm1);
        collaboratorRepository.addCollaborator(vfm2);
        collaboratorRepository.addCollaborator(qam);


        for (Collaborator c : collaboratorRepository.getCollaborators()) {
            String email = c.getEmail();
            String role;

            if (email.contains("@hrm.com")) {
                role = "HRM";
            } else if (email.contains("@gsm.com")) {
                role = "GSM";
            } else if (email.contains("@vfm.com")) {
                role = "VFM";
            } else if (email.contains("@qam.com")) {
                role = "QAM";
            } else {
                role = "COLLABORATOR";
            }

            AuthenticationRepository.addUser(email, c.getPwd(), role);
        }
    }


    private void addVehicle() {
        Vehicle vehicle1 = new Vehicle("GG-69-EZ", "BMW", "i4", "hibrid", 3500.0, 4500.0, 1000.0, new Date(2024, 1, 10), new Date(2024, 1, 26), 10000.0, 0.0);
        Vehicle vehicle2 = new Vehicle("69-WP-42", "Toyota", "Avensis", "Diesel", 3000.0, 4000.0, 42000.0, new Date(2018, 12, 10), new Date(2019, 1, 10), 20000.0, 30000.0);
        Vehicle vehicle3 = new Vehicle("04-20-VC", "ferrari", "diablo", "Petrol", 3000.0, 4000.0, 100000.0, new Date(2000, 12, 10), new Date(2003, 10, 11), 10000.0, 75432.3);
        vehicleRepository.addVehicle(vehicle1);
        vehicleRepository.addVehicle(vehicle2);
        vehicleRepository.addVehicle(vehicle3);
    }


    private void addAgendaEntry() throws InvalidEntryDataException, InvalidTaskDataException, InvalidGreenSpaceDataException {
        GreenSpaceRepository greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        TaskRepository taskRepository = Repositories.getInstance().getTaskRepository();
        Task task1 = new Task("Task One", "14");
        Task task2 = new Task("Task Two", "2");
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);


        Entry entry1 = new Entry(taskRepository.findTaskById("Task One"), greenSpaceRepository.getGreenSpaceByParkName("Cidade"));
        AgendaEntry agendaEntry1 = new AgendaEntry(entry1.getTask(), greenSpaceRepository.getGreenSpaceByParkName("Cidade"), new Date(2024, 6, 6), AgendaEntry.WorkingDayHours.H09, new Date(2024, 6, 20), AgendaEntry.WorkingDayHours.H12);
        agenda.addAgendaEntry(agendaEntry1);

        Entry entry2 = new Entry(taskRepository.findTaskById("Task Two"), greenSpaceRepository.getGreenSpaceByParkName("São Roque"));
        AgendaEntry agendaEntry2 = new AgendaEntry(entry2.getTask(), greenSpaceRepository.getGreenSpaceByParkName("São Roque"), new Date(2024, 6, 7), AgendaEntry.WorkingDayHours.H10, new Date(2024, 6, 8), AgendaEntry.WorkingDayHours.H14);
        agenda.addAgendaEntry(agendaEntry2);
    }

    private void addTeam() throws InvalidCollaboratorDataException {

        Collaborator c1 = new Collaborator("Ana", new Date(1990, 2, 3),
                new Date(2010, 3, 1), "Rua1", "912345669", "ana@collaborator.com",
                "123456789", Collaborator.IdDocType.CC, "234564321zx7", "ABC1234");

        Collaborator c2 = new Collaborator("João", new Date(1980, 2, 3), new Date(2010,
                3, 1), "Rua2", "912345669", "joao@collaborator.com", "234567899",
                Collaborator.IdDocType.BI, "232566381", "ABC1234");

        Collaborator c3 = new Collaborator("André", new Date(1970, 2, 3), new Date(2010,
                3, 1), "Rua3", "912345669", "andre@collaborator.com", "345678907",
                Collaborator.IdDocType.NISS, "23456432125", "ABC1234");

        Collaborator c4 = new Collaborator("Manuel", new Date(1999, 2, 3), new Date(2020,
                3, 1), "Rua4", "912345669", "manuel@collaborator.com", "456789014",
                Collaborator.IdDocType.PASSPORT, "H234564", "ABC1234");


        Team team1 = new Team(List.of(c1, c2));
        Team team2 = new Team(List.of(c3, c4));

        teamRepository.addTeam(team1);
        teamRepository.addTeam(team2);
    }

    private void addGreenSpaces() throws InvalidGreenSpaceDataException {
        GreenSpace greenSpace1 = new GreenSpace(GreenSpace.TypeOfGreenSpace.LPARK, "Cidade", 83,
                "Estrada Interior da Circunvalação, 4100-083 Porto", collaboratorRepository.getCollaborator("122472678AA3"));

        GreenSpace greenSpace2 = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "São Roque", 4,
                "R. São Roque da Lameira 2040, 4350-307 Porto", collaboratorRepository.getCollaborator("122472678BB3"));

        GreenSpace greenSpace3 = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Cordoaria", 2,
                "Campo dos Mártires da Pátria, 4050-367 Porto", collaboratorRepository.getCollaborator("122472678BB3"));

        GreenSpace greenSpace4 = new GreenSpace(GreenSpace.TypeOfGreenSpace.MPARK, "Palácio de Cristal", 8,
                "R. Dom Manuel II, 4050-346 Porto", collaboratorRepository.getCollaborator("122472678AA3"));

        GreenSpace greenSpace5 = new GreenSpace(GreenSpace.TypeOfGreenSpace.LPARK, "Parque da Cidade", 83,
                "Estrada da Circunvalação, 4100-183 Porto", collaboratorRepository.getCollaborator("122472678BB3"));

        GreenSpace greenSpace6 = new GreenSpace(GreenSpace.TypeOfGreenSpace.GARDEN, "Jardim do Morro", 1,
                "Avenida da República, 4430-148 Vila Nova de Gaia", collaboratorRepository.getCollaborator("122472678AA3"));


        greenSpaceRepository.addGreenSpace(greenSpace1);
        greenSpaceRepository.addGreenSpace(greenSpace2);
        greenSpaceRepository.addGreenSpace(greenSpace3);
        greenSpaceRepository.addGreenSpace(greenSpace4);
        greenSpaceRepository.addGreenSpace(greenSpace5);
        greenSpaceRepository.addGreenSpace(greenSpace6);
    }
}
