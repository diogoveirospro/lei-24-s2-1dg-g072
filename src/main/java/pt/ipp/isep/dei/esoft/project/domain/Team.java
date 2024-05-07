package pt.ipp.isep.dei.esoft.project.domain;

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

    /**
     * Team builder.
     * @param members ArrayList of collaborators with the members of the team.
     */
    public Team(List<Collaborator> members){
        team = members;
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
            team.append(member);
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
