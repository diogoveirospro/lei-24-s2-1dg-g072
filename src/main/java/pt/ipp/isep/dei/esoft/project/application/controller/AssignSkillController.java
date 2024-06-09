package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */

public class AssignSkillController {

    /**
     * Skill Repository.
     */

    private final SkillRepository skillRepository;

    /**
     * Collaborator repository.
     */

    private final CollaboratorRepository collaboratorRepository;


    /**
     * Empty AssignSkillController builder.
     */

    public AssignSkillController(){
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }

    /**
     * AssignSkillController builder
     * @param skillRepository skill repository
     * @param collaboratorRepository collaborator repository
     */

    public AssignSkillController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository){
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * List the skills on the skill repository.
     * @return list of skills.
     */

    public List<Skill> listSkills(){
        return skillRepository.listSkills();
    }

    /**
     * List the collaborators.
     * @return list of collaborators.
     */

    public List<Collaborator> getCollaborators(){
        return collaboratorRepository.getCollaborators();
    }


    /**
     * Retrieves a skill by its name.
     *
     * @param skill the name of the skill to retrieve
     * @return the skill with the specified name
     */

    public Skill getSkill(String skill) {
        return skillRepository.getSkill(skill);
    }

    /**
     * Retrieves a collaborator by their ID number.
     *
     * @param collaboratorIDNumber the ID number of the collaborator to retrieve
     * @return the collaborator with the specified ID number
     */

    public Collaborator getCollaborator(String collaboratorIDNumber) {
        return collaboratorRepository.getCollaborator(collaboratorIDNumber);
    }

    /**
     * Assigns a skill to a collaborator and saves the updated collaborator information to the repository.
     *
     * @param skill the skill to assign to the collaborator
     * @param collaborator the collaborator to whom the skill will be assigned
     */

    public void assignSkill(Skill skill, Collaborator collaborator) {
        collaborator.assignSkill(skill);
        collaboratorRepository.saveCollaboratorRepositoryToFile();
    }

}
