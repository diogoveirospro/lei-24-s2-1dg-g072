package pt.ipp.isep.dei.esoft.project.ui.gui.controller.Uss;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;

/**
 * GenerateTeamController is a class responsible for making the requests related to team generation, requested by the UI.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class GenerateTeamProposalUIController {

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
    public GenerateTeamProposalUIController(){

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
    public GenerateTeamProposalUIController(SkillRepository skillRepository, CollaboratorRepository collaboratorRepository,
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
    public Team generateTeamProposal(int minimumSize, int maximumSize, List<Skill> skills,
                                                   List<Collaborator> collaborators){
        return TeamRepository.generateTeamProposal(minimumSize, maximumSize, skills, collaborators);
    }

    /**
     * Add the team to the team repository.
     * @param team team to add.
     * @return true if added false otherwise
     */
    public boolean addTeam(Team team){
        return teamRepository.addTeam(team);
    }
}
