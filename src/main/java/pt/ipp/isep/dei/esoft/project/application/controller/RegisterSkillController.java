package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

/**
 * The RegisterSkillController class is responsible for managing the registration
 * of new skills within the system. It utilizes a SkillRepository to persist skills.
 *
 * @author Group 072 - Byte Masters - ISEP
 */

public class RegisterSkillController {
    private SkillRepository skillRepository;

    /**
     * Empty RegisterJobController builder.
     */
    public RegisterSkillController(){
        this.skillRepository = Repositories.getInstance().getSkillRepository();
    }


    /**
     * Constructs a RegisterSkillController with a specified SkillRepository.
     *
     * @param skillRepository The repository used for skill management and persistence.
     */

    public RegisterSkillController(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    /**
     * Registers a new skill with the specified name in the system. This involves
     * creating a Skill object and adding it to the repository.
     *
     * @param name The name of the skill to addSkill. It must not be null or empty.
     */

    public void registerSkill (String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Cannot be null or empty");
        }
        Skill skill = new Skill(name);
        try {
            skillRepository.addSkill(skill);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
