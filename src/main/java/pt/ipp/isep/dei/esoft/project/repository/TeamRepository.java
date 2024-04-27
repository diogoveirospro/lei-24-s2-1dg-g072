package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private List<Team> teams;

    public TeamRepository(){
         teams = new ArrayList<>();
    }

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

    public Team createTeam(ArrayList<Collaborator> members){
        return new Team(members);
    }

    public void addTeam(Team team) {
        if (validateTeam(team)) {
            teams.add(team);
        }
    }

    public boolean validateTeam(Team team){
        if (team == null){
            return false;
        }
        return true;

    }
}
