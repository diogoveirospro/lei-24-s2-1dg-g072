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

    /**
     * A constructor of skillRepository that initiates the skill list
     *
     */
    public SkillRepository(){
        skills = new ArrayList<>();
    }

    /**
     * This method adds a skill to the repository
     *
     * @param skill to be added
     */
    public void addSkill(Skill skill){
        if(skill == null){
            throw new IllegalArgumentException("Skill cannot be null");
        }
        if (skills.contains(skill)){
            throw new IllegalArgumentException("This skill already exists "+skill.getName());
        }
        if (!skill.validateSkill(skill.getName())){
            throw new IllegalArgumentException("Skill can't have special characters");
        }
        skills.add(skill);
    }

    /**
     * This method returns a defensive (immutable) copy of the skill list.
     *
     * @return The skill list.
     */
    public List<Skill> listSkills() {
        sortSkillsAlphabetically();
        return List.copyOf(skills);
    }

    /**
     * Sort skills alphabetically
     *
     */
    public void sortSkillsAlphabetically() {
        for (int i = 0; i < skills.size() - 1; i++) {
            for (int j = i + 1; j < skills.size(); j++) {
                String name1 = skills.get(i).getName();
                String name2 = skills.get(j).getName();
                if (name1.compareToIgnoreCase(name2) > 0) {
                    Skill aux = skills.get(i);
                    skills.set(i, skills.get(j));
                    skills.set(j, aux);
                }
            }
        }
    }


}
