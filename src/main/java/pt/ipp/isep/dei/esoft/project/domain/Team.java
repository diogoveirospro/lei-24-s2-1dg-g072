package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team composed of collaborators.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Team {

    /**
     * List of collaborators representing the team.
     */
    List<Collaborator> team;

    public Team (List<Collaborator> members){
        this.team = members;
    }

    /**
     * Team builder
     * @param minimumSize minimum team size
     * @param maximumSize maximum team size
     * @param collaborators all collaborators
     * @param skills a list of skills still required in the team.
     */
    public Team(int minimumSize, int maximumSize, List<Collaborator> collaborators, List<Skill> skills){
        team = new ArrayList<>();
        List<Collaborator> collaboratorsClone = new ArrayList<>(collaborators);
        List<Skill> remainingSkills = new ArrayList<>(skills);

        selectMembersForSkills(maximumSize, collaboratorsClone, remainingSkills);
        if (this.team.size() < minimumSize) {
            throw new IllegalArgumentException("There aren't enough collaborators with the specified skills to create a team!");
        }
    }

    /**
     * Selects team members based on required skills until the team is filled to the maximum size or all skills are addressed.
     *
     * @param maximumSize        maximum team size.
     * @param collaboratorsClone a clone of the list of available collaborators.
     * @param skills    a list of skills still required in the team.
     * @throws IllegalArgumentException if there are no collaborators with the required skills.
     */
    private void selectMembersForSkills(int maximumSize, List<Collaborator> collaboratorsClone, List<Skill> skills) {
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
            if (this.team.size() < maximumSize) {
                this.team.add(collaborator);
                collaborator.setHasTeam(true);
                collaboratorsClone.remove(collaborator);
            } else {
                break;
            }
        }
    }

    /**
     * Returns a list of collaborators that possess the given skill.
     * @param maximumSize maximum team size.
     * @param skillsRemaining all skills to be matched
     * @param collaboratorsClone the list of available collaborators
     * @param skillsAlreadyAdded skills for which there are already collaborators
     * @return a list of qualified collaborators
     */
    private List<Collaborator> getQualifiedCollaborators(int maximumSize,List<Skill> skillsRemaining, List<Collaborator> collaboratorsClone, List<Skill> skillsAlreadyAdded) {
        List<Collaborator> qualifiedCollaborators = new ArrayList<>();
        List<Skill> skillsAdded = new ArrayList<>();
        for (Collaborator collaborator : collaboratorsClone) {
            if (qualifiedCollaborators.size() < maximumSize) {
                for (Skill skill : skillsRemaining) {
                    if (collaborator.addTeam(skill) && (!skillsAdded.contains(skill))) {
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
     * Get the team.
     * @return team
     */
    public List<Collaborator> getTeam() {
        return team;
    }

    /**
     * Change the team.
     * @param team new team
     */
    public void setTeam(List<Collaborator> team) {
        this.team = team;
    }

    /**
     * Rewriting the toString method for the team class.
     * @return String representing a team.
     */
    @Override
    public String toString() {

        StringBuilder team = new StringBuilder("The team members are: \n");

        for (Collaborator member : this.team){
            team.append(member.getName()).append(" - ").append(member.getIdDocNumber());
            team.append("\n");
        }

        return team.toString();
    }

    /**
     * Rewriting the equals method for the team class.
     * @param obj Another team or object.
     * @return true if the teams are equal and false if they are not.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Team anotherTeam = (Team) obj;

        for (Collaborator member : team){
            if (!anotherTeam.getTeam().contains(member)){
                return false;
            }
        }
        return true;
    }
}
