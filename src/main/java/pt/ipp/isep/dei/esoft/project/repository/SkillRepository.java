package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;


import pt.ipp.isep.dei.esoft.project.domain.Skill;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class SkillRepository {
    private List<Skill> skills;

    SkillRepository(){
        skills = new ArrayList<>();
    }

    public void addSkill(Skill skill){
        if(skill == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        if (skills.contains(skill)){
            throw new IllegalArgumentException("This skill already exists"+skill.getName());
        }
        skills.add(skill);
    }

    /**
     * This method returns a defensive (immutable) copy of the skill list.
     *
     * @return: The skill list.
     */
    public List<Skill> listSkills() {
        return List.copyOf(skills);
    }


}
