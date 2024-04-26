package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {

    List<Collaborator> team;

    public Team(ArrayList<Collaborator> members){
        team = members;
    }

}
