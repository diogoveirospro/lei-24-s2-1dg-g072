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
     * @param collaborators list of all collaborators.
     * @return members of the team proposal.
     * @throws IllegalArgumentException if there aren't enough collaborators with the specified skills to create a team.
     */
    public List<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills, List<Collaborator> collaborators) {
        List<Collaborator> members = new ArrayList<>();
        List<Collaborator> collaboratorsClone = new ArrayList<>(collaborators);
        List<Skill> remainingSkills = new ArrayList<>(skills);

        selectMembersForSkills(maximumSize, members, collaboratorsClone, remainingSkills);
        if (members.size() < minimumSize) {
            throw new IllegalArgumentException("There aren't enough collaborators with the specified skills to create a team!");
        }
        return members;
    }

    /**
     * Selects team members based on required skills until the team is filled to the maximum size or all skills are addressed.
     *
     * @param maximumSize        maximum team size.
     * @param members            the current list of team members
     * @param collaboratorsClone a clone of the list of available collaborators.
     * @param skills    a list of skills still required in the team.
     * @throws IllegalArgumentException if there are no collaborators with the required skills.
     */
    private void selectMembersForSkills(int maximumSize, List<Collaborator> members, List<Collaborator> collaboratorsClone, List<Skill> skills) {
        List<Skill> skillsPresent = new ArrayList<>();
        List<Collaborator> qualifiedCollaborators = getQualifiedCollaborators(maximumSize, skills, collaboratorsClone, skillsPresent);

        boolean allSkillsPresent = true;

        for (Skill skill : skills) {
            boolean skillFound = false;

            for (Skill presentSkill : skillsPresent) {
                if (skill.equals(presentSkill)) {
                    skillFound = true;
                    break;
                }
            }

            if (!skillFound) {
                allSkillsPresent = false;
                break;
            }
        }

        if (!allSkillsPresent) {
            throw new IllegalArgumentException("There are no collaborators with the required skills: " + skills);
        }

        for (Collaborator collaborator : qualifiedCollaborators) {
            if (members.size() < maximumSize) {
                members.add(collaborator);
                collaborator.setHasTeam(true);
                collaboratorsClone.remove(collaborator);
            } else {
                break;
            }
        }
    }

    /**
     * Returns a list of collaborators that possess the given skill.
     *
     * @param skillsRemaining    all skills to be matched
     * @param collaboratorsClone the list of available collaborators
     * @return a list of qualified collaborators
     */
    private List<Collaborator> getQualifiedCollaborators(int maximumSize,List<Skill> skillsRemaining, List<Collaborator> collaboratorsClone, List<Skill> skillsAlreadyAdded) {
        List<Collaborator> qualifiedCollaborators = new ArrayList<>();
        List<Skill> skillsAdded = new ArrayList<>();
        for (Collaborator collaborator : collaboratorsClone) {
            if (qualifiedCollaborators.size() < maximumSize) {
                for (Skill skill : skillsRemaining) {
                    if (collaborator.analyseCollaborator(skill) && (!skillsAdded.contains(skill))) {
                        skillsAdded.add(skill);
                    }
                }
                qualifiedCollaborators.add(collaborator);
                skillsAlreadyAdded.addAll(skillsAdded);
                skillsAdded = new ArrayList<>();
            }
        }
        return qualifiedCollaborators;
    }

    /**
     * Create a team.
     *
     * @param members team members.
     * @return new team.
     */
    public Team createTeam(List<Collaborator> members) {
        return new Team(members);
    }

    /**
     * Add a team to the team repository.
     *
     * @param team team to be added to the repository.
     */
    public void addTeam(Team team) {
        if (validateTeam(team)) {
            teams.add(team);
        }
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
}
