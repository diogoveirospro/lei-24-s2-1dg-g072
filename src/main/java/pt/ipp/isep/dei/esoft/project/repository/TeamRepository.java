package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
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

    public Team generateTeamProposal(int minimumSize, int maximumSize, ArrayList<Collaborator> members){


    }
}
