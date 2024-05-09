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
     * @throws IllegalArgumentException if there aren't enough collaborators with the specified skills to create a team.
     * @return members of the team proposal.
     */
    public List<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills, List<Collaborator> collaborators) {
        List<Collaborator> members = new ArrayList<>();
        List<Collaborator> collaboratorsClone = new ArrayList<>(collaborators);
        List<Skill> remainingSkills = new ArrayList<>(skills);

        selectMembersForSkills(maximumSize, members, collaboratorsClone, remainingSkills);

        if (members.size() < minimumSize) {
            throw new IllegalArgumentException("There aren't enough collaborators with the specified skills to create a team!");
        }

        fillUpToMaximumSize(maximumSize, members, collaboratorsClone);

        return members;
    }

    /**
     * Selects team members based on required skills until the team is filled to the maximum size or all skills are addressed.
     * @param maximumSize maximum team size.
     * @param members the current list of team members
     * @param collaboratorsClone a clone of the list of available collaborators.
     * @param remainingSkills a list of skills still required in the team.
     */
    private void selectMembersForSkills(int maximumSize, List<Collaborator> members, List<Collaborator> collaboratorsClone, List<Skill> remainingSkills) {
        while (!remainingSkills.isEmpty() && members.size() < maximumSize && !collaboratorsClone.isEmpty()) {
            Skill skill = remainingSkills.get(0);
            List<Collaborator> qualifiedCollaborators = getQualifiedCollaborators(skill, collaboratorsClone);

            if (qualifiedCollaborators.isEmpty()) {
                throw new IllegalArgumentException("There are no collaborators with the specified skill: " + skill);
            }

            addCollaboratorToTeam(members, qualifiedCollaborators, collaboratorsClone);
            remainingSkills.remove(skill);
        }
    }

    /**
     * Returns a list of collaborators that possess the given skill.
     * @param skill the skill to be matched
     * @param collaboratorsClone the list of available collaborators
     * @return a list of qualified collaborators
     */
    private List<Collaborator> getQualifiedCollaborators(Skill skill, List<Collaborator> collaboratorsClone) {
        List<Collaborator> qualifiedCollaborators = new ArrayList<>();
        for (Collaborator collaborator : collaboratorsClone) {
            if (collaborator.analyseCollaborator(skill)) {
                qualifiedCollaborators.add(collaborator);
            }
        }
        return qualifiedCollaborators;
    }

    /**
     * Adds a collaborator to the team.
     * @param members the list of selected collaborators
     * @param qualifiedCollaborators the list of qualified collaborators
     * @param collaboratorsClone the list of available collaborators
     */
    private void addCollaboratorToTeam(List<Collaborator> members, List<Collaborator> qualifiedCollaborators, List<Collaborator> collaboratorsClone) {
        Collaborator selectedCollaborator = qualifiedCollaborators.get(0);
        members.add(selectedCollaborator);
        selectedCollaborator.setHasTeam(true);
        collaboratorsClone.remove(selectedCollaborator);
    }

    /**
     * Fills the team with additional collaborators until the maximum size is reached.
     * @param maximumSize the maximum size of the team
     * @param members the list of selected collaborators
     * @param collaboratorsClone the list of available collaborators
     */
    private void fillUpToMaximumSize(int maximumSize, List<Collaborator> members, List<Collaborator> collaboratorsClone) {
        while (members.size() < maximumSize && !collaboratorsClone.isEmpty()) {
            Collaborator additionalCollaborator = collaboratorsClone.get(0);
            members.add(additionalCollaborator);
            additionalCollaborator.setHasTeam(true);
            collaboratorsClone.remove(0);
        }
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
        return team != null && !team.getTeam().isEmpty();

    }
}
