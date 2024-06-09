package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class SkillRepository extends SerializableRepository<List<Skill>> implements Serializable {
    private List<Skill> skills;

    /**
     * A constructor of skillRepository that initiates the skill list
     *
     */
    public SkillRepository(){
        super("skillRepository.ser");
        skills = super.load();
        if (skills == null) {
            skills = new ArrayList<>();
        }
    }
    public SkillRepository(String filename){
        super(filename);
        skills = super.load();
        if (skills == null) {
            skills = new ArrayList<>();
        }
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
            throw new IllegalArgumentException("This skill already exists " + skill.getName());
        }
        if (skill.validateSkill(skill.getName())){
            throw new IllegalArgumentException("Skill can't have special characters");
        }
        skills.add(skill);
        saveSkillRepositoryToFile();
    }

    /**
     * This method returns a defensive (immutable) copy of the skill list.
     *
     * @return The skill list.
     */
    public List<Skill> listSkills() {
        Collections.sort(skills);
        return List.copyOf(skills);
    }

    public void saveSkillRepositoryToFile() {
        save(skills);
    }


    public Skill getSkill(String skill) {
        for (Skill s : skills) {
            if (s.getName().equals(skill)) {
                return s;
            }
        }
        return null;
    }
    public void clear() {
        skills.clear();
        super.clear();
    }
}
