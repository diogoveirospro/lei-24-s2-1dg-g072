package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class AssignSkillController {

    private final SkillRepository skillRepository;

    private final CollaboratorRepository collaboratorRepository;



    public AssignSkillController(){
        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }

    public AssignSkillController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository){
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Skill> listSkills(){
        return skillRepository.listSkills();
    }

    public List<Collaborator> getCollaborators(){
        return collaboratorRepository.getCollaborators();
    }


}
