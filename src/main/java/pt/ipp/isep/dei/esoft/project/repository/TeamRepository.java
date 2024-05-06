package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

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
    public TeamRepository(){
         teams = new ArrayList<>();
    }

    /**
     * This method returns a defensive (immutable) copy of the team list.
     * @return the team list.
     */
    public List<Team> getTeams() {
        return List.copyOf(teams);
    }

    /**
     * Get a team from the repository.
     * @param members team members.
     * @return team made up of the members passed in by parameter.
     */
    public Team getTeam(List<Collaborator> members){
        Team newTeam = new Team(members);
        Team team = null;

        if (teams.contains(newTeam)){
            team = teams.get(teams.indexOf(newTeam));
        }

        if (team == null){
            throw new IllegalArgumentException("The team composed of the members provided does not exist.");
        }

        return team;
    }

    /**
     * Generate a team proposal automatically.
     * @param minimumSize minimum team size.
     * @param maximumSize maximum team size.
     * @param skills skills that members must have to be part of that team.
     * @param collaborators list of all collaborators.
     * @return members of the team proposal.
     */
    public List<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills,
                                                   List<Collaborator> collaborators) {

        List<Collaborator> members = new ArrayList<>();
        List<Collaborator> collaboratorsClone = new ArrayList<>(collaborators);

        for (Skill skill : skills) {
            List<Collaborator> qualifiedCollaborators = new ArrayList<>();

            for (Collaborator collaborator : collaboratorsClone) {
                if (collaborator.analyseCollaborator(skill)) {
                    qualifiedCollaborators.add(collaborator);
                }
            }

            if (qualifiedCollaborators.isEmpty()) {
                throw new IllegalArgumentException("There are no collaborators with the specified skill: " + skill);
            }

            members.add(qualifiedCollaborators.get(0));
            qualifiedCollaborators.get(0).setHasTeam(true);
            collaboratorsClone.remove(qualifiedCollaborators.get(0));

            if (members.size() == minimumSize) {
                break;
            }
        }

        if (members.size() < minimumSize) {
            throw new IllegalArgumentException("There aren't enough collaborators with the specified skills to create a team!");
        }

        while (members.size() < maximumSize && !collaboratorsClone.isEmpty()) {
            Collaborator additionalCollaborator = collaboratorsClone.get(0);
            members.add(additionalCollaborator);
            collaboratorsClone.remove(0);
        }

        return members;
    }

    /**
     * Create a team.
     * @param members team members.
     * @return new team.
     */
    public Team createTeam(List<Collaborator> members){
        return new Team(members);
    }

    /**
     * Add a team to the team repository.
     * @param team team to be added to the repository.
     */
    public void addTeam(Team team) {
        if (validateTeam(team)) {
            teams.add(team);
        }
    }

    /**
     * Validate the team.
     * @param team team to be validated.
     * @return true if the team is valid and false otherwise.
     */
    public boolean validateTeam(Team team){
        return team != null;

    }
}
