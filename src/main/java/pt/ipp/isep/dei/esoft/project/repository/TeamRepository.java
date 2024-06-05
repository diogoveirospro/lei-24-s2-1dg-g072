package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaEntryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * It represents a list of all the teams that have already been formed.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class TeamRepository {
    /**
     * List representing all TeamRepository teams.
     */
    private List<Team> teams;

    /**
     * Team Repository builder.
     */
    public TeamRepository() {
        teams = new ArrayList<>();
    }

    /**
     * This method returns a defensive (immutable) copy of the team list.
     *
     * @return the team list.
     */
    public List<Team> getTeams() {
        return List.copyOf(teams);
    }

    /**
     * Get a team from the repository.
     *
     * @param members team members.
     * @return team made up of the members passed in by parameter.
     */
    public Team getTeam(List<Collaborator> members) {
        Team newTeam = new Team(members);
        Team team = null;

        if (teams.contains(newTeam)) {
            team = teams.get(teams.indexOf(newTeam));
        }

        if (team == null) {
            throw new IllegalArgumentException("The team composed of the members provided does not exist.");
        }

        return team;
    }

    /**
     * Generate a team proposal automatically.
     *
     * @param minimumSize   minimum team size.
     * @param maximumSize   maximum team size.
     * @param skills        skills that members must have to be part of that team.
     * @param collaborators list of all collaborators
     * @return members of the team proposal.
     */
    public static Team generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills, List<Collaborator> collaborators) {
        return new Team(minimumSize, maximumSize, collaborators, skills);
    }

    /**
     * Add a team to the team repository.
     *
     * @param team team to be added to the repository.
     */
    public boolean addTeam(Team team) {
        if (validateTeam(team)) {
            teams.add(team);
            return true;
        }
        return false;
    }

    /**
     * Validate the team.
     *
     * @param team team to be validated.
     * @return true if the team is valid and false otherwise.
     */
    public boolean validateTeam(Team team) {
        return team != null && !team.getTeam().isEmpty();

    }

    /**
     * Get the teams with the collaborator.
     *
     * @param collaborator collaborator being searched in all teams
     * @return
     */
    public List<Team> getTeamsByCollaborator(Collaborator collaborator) {
        List<Team> teamList = new ArrayList<>();
        for (Team team : teams) {
            boolean inTeam = team.validateCollaboratorTeam(collaborator, team);
            if (inTeam) {
                teamList.add(team);
            }
        }
        if (teamList.isEmpty()) {
            throw new IllegalArgumentException("Collaborator not found in any team.");
        } else {
            return teamList;
        }
    }

    /**
     * Retrieves a list of teams that are valid to be assigned to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry to which the teams are being validated for assignment
     * @return a list of valid teams
     */
    public List<Team> getValidTeams(AgendaEntry agendaEntry) {
        List<Team> validTeams = new ArrayList<>();
        for (Team team : teams) {
            if (team.validateTeamToBeAssignedToAnAgendaEntry(agendaEntry)) {
                validTeams.add(team);
            }
        }
        return validTeams;
    }

}
