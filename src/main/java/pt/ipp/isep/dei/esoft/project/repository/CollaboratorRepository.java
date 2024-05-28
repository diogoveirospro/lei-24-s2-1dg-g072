package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CollaboratorRepository class.
 * Manages the collection of Collaborators.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class CollaboratorRepository {

    private final SkillRepository skillRepository;

    /**
     * List containing all collaborators.
     */
    private final List<Collaborator> collaborators;

    /**
     * Repository constructor.
     *
     * @param skillRepository the skill repository to use
     */
    public CollaboratorRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
        this.collaborators = new ArrayList<>();
    }

    /**
     * Get a collaborator from the repository by their ID document number.
     *
     * @param IdDocNumber Collaborator ID document number
     * @return collaborator
     */
    public Collaborator getCollaborator(String IdDocNumber) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getIdDocNumber().equals(IdDocNumber)) {
                return collaborator;
            }
        }
        throw new IllegalArgumentException("The collaborator whose ID number is " + IdDocNumber + " does not exist.");
    }

    /**
     * Add a collaborator to the repository.
     *
     * @param newCollaborator new collaborator.
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
     * @param collaborator collaborator to be checked
     * @return True if the collaborator is not yet in the repository and false otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * This method returns a defensive (immutable) copy of the collaborator list.
     *
     * @return The collaborator list.
     */
    public List<Collaborator> getCollaborators() {
        Collections.sort(collaborators);
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
        return skillRepository.listSkills().contains(skill);
    }

    /**
     * Get a collaborator from the repository by their email.
     *
     * @param email Collaborator email
     * @return collaborator
     */
    public Collaborator getCollaboratorByEmail(String email) {
        return collaborators.stream()
                .filter(collaborator -> collaborator.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Collaborator with email " + email + " not found"));
    }
}
