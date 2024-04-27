package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

public class SkillRepository {
    private List<Skill> skills= new ArrayList<>();

    public void register (Skill skill){
        if(skill == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        if (skills.contains(skill)){
            throw new IllegalArgumentException("This skill already exists"+skill.getName());
        }
        skills.add(skill);
        System.out.println("Skill:"+ skill.getName());
    }

}
