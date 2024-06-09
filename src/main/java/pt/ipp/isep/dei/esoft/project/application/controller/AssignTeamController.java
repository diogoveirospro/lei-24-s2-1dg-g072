package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.EmailServicesExternalModule;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * The AssignTeamController class represents the controller for assigning teams to agenda entries.
 */

public class AssignTeamController {
    private final Agenda agenda;
    private final TeamRepository teamRepository;

    /**
     * Constructs a new AssignTeamController with the necessary repositories.
     */

    public AssignTeamController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
    }

    /**
     * Retrieves the list of agenda entries.
     *
     * @return the list of agenda entries
     */

    public List<AgendaEntry> getAgendaEntries() {
        return this.agenda.getEntryList();
    }

    /**
     * Retrieves the agenda entry with the specified name.
     *
     * @param name the name of the agenda entry
     * @return the agenda entry with the specified name
     */

    public AgendaEntry getAgendaEntry(String name) {
        return this.agenda.getAgendaEntry(name);
    }

    /**
     * Retrieves the list of valid teams for assignment to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry to be assigned to
     * @return the list of valid teams
     */

    public List<Team> getValidTeams (AgendaEntry agendaEntry) {
        return this.teamRepository.getValidTeams(agendaEntry);
    }

    /**
     * Assigns the specified team to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry to be assigned to
     * @param team the team to be assigned
     * @return true if the team is successfully assigned to the agenda entry, otherwise false
     */

    public boolean assignTeamToAgendaEntry (AgendaEntry agendaEntry, Team team){
        return agenda.assignTeamToAgendaEntry(agendaEntry, team);
    }

    /**
     * Retrieves the list of available email services.
     *
     * @return the list of available email services
     * @throws IOException if an I/O error occurs while reading the config.properties file
     */

    public List<String> showEmailServices () throws IOException {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/config/config.properties")) {
            props.load(in);
        }
        String emailServices = props.getProperty("email.services");
        return Arrays.asList(emailServices.split("\\s*,\\s*"));
    }

    /**
     * Sends an email to the members of the specified team using the specified email service.
     *
     * @param team the team whose members will receive the email
     * @param emailService the email service to be used for sending the email
     * @return true if the email is successfully sent, otherwise false
     */

    public boolean sendEmailToTeamMembers (Team team, String emailService){
        List<Collaborator> members = team.getTeam();
        try {
            Class<?> emailServiceClass = Class.forName("pt.ipp.isep.dei.esoft.project.domain.externalModules." + emailService + "EmailService");
            Constructor<?> constructor = emailServiceClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            EmailServicesExternalModule emailModule = (EmailServicesExternalModule) constructor.newInstance();
            for (Collaborator member : members) {
                emailModule.sendEmail(member.getEmail());
            }
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException
                 | InvocationTargetException | FileNotFoundException e) {
            return false;
        }
    }
    /**
     * Retrieves a list of agenda entries that do not have an assigned team.
     *
     * @return a list of agenda entries without an assigned team
     */

    public List<AgendaEntry> getAgendaEntriesWithoutTeam () {
        return this.agenda.getAgendaEntriesWithoutTeam();
    }

}


