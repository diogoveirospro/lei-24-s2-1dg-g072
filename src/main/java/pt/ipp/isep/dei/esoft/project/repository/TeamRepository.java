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
     * Get the teams from the TeamRepository.
     * @return team repository
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Get a team from the repository.
     * @param members team members.
     * @return team made up of the members passed in by parameter.
     */
    public Team getTeam(ArrayList<Collaborator> members){
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
    public ArrayList<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, ArrayList<Skill> skills,
                                     ArrayList<Collaborator> collaborators){

        ArrayList<Collaborator> members = new ArrayList<>();
        ArrayList<Collaborator> collaboratorsClone = new ArrayList<>(collaborators);


        for (Skill skill : skills){

            for (Collaborator collaborator : collaboratorsClone){
                if (collaborator.analyseCollaborator(skill)){
                    members.add(collaborator);
                    collaboratorsClone.remove(collaborator);
                    if (members.size() == maximumSize){
                        return members;
                    }
                }
                break;
            }

            break;
        }

        if (members.size() < minimumSize){
            throw new IllegalArgumentException("There aren't enough collaborators with the specified skills to create a team!");
        }

        return members;
    }

    /**
     * Create a team.
     * @param members team members.
     * @return new team.
     */
    public Team createTeam(ArrayList<Collaborator> members){
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
        if (team == null){
            return false;
        }
        return true;

    }
}
