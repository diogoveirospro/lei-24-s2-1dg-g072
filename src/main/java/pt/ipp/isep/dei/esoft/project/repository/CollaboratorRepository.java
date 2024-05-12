package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class CollaboratorRepository {

    private SkillRepository skillRepository = new SkillRepository();
    private List<Skill> skills = skillRepository.listSkills();

    /**
     * List containing all collaborators.
     */
    private final List
            <Collaborator> collaborators;

    /**
     * Repository builder.
     */
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    /**
     * Get a collaborator from the repository by their name.
     *
     * @param name: Collaborator name
     * @return: collaborator
     */
    public Collaborator getCollaborator(String name) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getName().equals(name)) {
                return collaborator;
            }
        }
        throw new IllegalArgumentException("Collaborator " + name + " does not exist.");
    }

    /**
     * Add a collaborator to the repository.
     *
     * @param newCollaborator: new collaborator.
     */
    public void addCollaborator(Collaborator newCollaborator) {
        if (!validateCollaborator(newCollaborator)) {
            throw new IllegalArgumentException("Invalid collaborator to add");
        }
        collaborators.add(newCollaborator);
    }

    /**
     * Private method to check if a collaborator is already in the repository.
     *
     * @param collaborator: collaborator to be checked
     * @return: True if the collaborator is not yet in the repository and false otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * This method returns a defensive (immutable) copy of the collaborator list.
     *
     * @return: The collaborator list.
     */
    public List<Collaborator> getCollaborators() {
        return List.copyOf(collaborators);
    }

    /**
     * Assigns a skill to a specific collaborator.
     *
     * @param collaborator the collaborator to assign the skill to
     * @param skill        the skill to be assigned
     */
    public void assignSkill(Collaborator collaborator, Skill skill) {
        if (!collaborators.contains(collaborator)) {
            throw new IllegalArgumentException("Collaborator not found in repository");
        }
        if (!validateSkill(skill)) {
            throw new IllegalArgumentException("Invalid skill");
        }
        collaborator.assignSkill(skill);
    }

    /**
     * Checks if a skill is valid (e.g., exists in a list of available skills).
     *
     * @param skill the skill to validate
     * @return true if the skill is valid, false otherwise
     */
    private boolean validateSkill(Skill skill) {

        return skills.contains(skill);
    }


}
