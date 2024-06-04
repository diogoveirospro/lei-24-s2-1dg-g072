package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

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
public class AssignSkillUIController {

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
    public AssignSkillUIController(){
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }

    /**
     * AssignSkillController builder
     * @param skillRepository skill repository
     * @param collaboratorRepository collaborator repository
     */
    public AssignSkillUIController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository){
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


}
