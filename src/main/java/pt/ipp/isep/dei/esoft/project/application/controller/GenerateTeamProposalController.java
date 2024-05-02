package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * GenerateTeamController is a class responsible for making the requests related to team generation, requested by the UI.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class GenerateTeamProposalController {

    /**
     * Skill Repository.
     */
    private final SkillRepository skillRepository;

    /**
     * Collaborator Repository
     */
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Team Repository
     */
    private final TeamRepository teamRepository;

    /**
     * Empty GenerateTeamController builder.
     */
    public GenerateTeamProposalController(){

        this.skillRepository = Repositories.getInstance().getSkillRepository();
        this.collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        this.teamRepository = Repositories.getInstance().getTeamRepository();
    }

    /**
     * GenerateTeamController builder.
     * @param skillRepository skill repository
     * @param collaboratorRepository collaborator repository
     * @param teamRepository team repository
     */
    public GenerateTeamProposalController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository,
                                          TeamRepository teamRepository){

        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.teamRepository = teamRepository;

    }

    /**
     * List all skills
     * @return skills
     */
    public List<Skill> listSkills(){
        return skillRepository.listSkills();
    }

    /**
     * Get all collaborators.
     * @return collaborators
     */
    public List<Collaborator> getCollaborators(){
        return collaboratorRepository.getCollaborators();
    }

    /**
     * Generate a team proposal automatically.
     * @param minimumSize minimum team size.
     * @param maximumSize maximum team size.
     * @param skills skills list.
     * @param collaborators collaborators list.
     * @return team proposal
     */
    public List<Collaborator> generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills,
                                                   List<Collaborator> collaborators){
        return teamRepository.generateTeamProposal(minimumSize, maximumSize, skills, collaborators);
    }

    /**
     * Create a team
     * @param members team members.
     * @return team
     */
    public Team createTeam(List<Collaborator> members){
        return teamRepository.createTeam(members);
    }
}
