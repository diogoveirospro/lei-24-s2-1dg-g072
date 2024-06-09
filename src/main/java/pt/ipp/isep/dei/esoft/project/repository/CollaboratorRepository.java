package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository class for managing Collaborators.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class CollaboratorRepository extends SerializableRepository<List<Collaborator>> implements Serializable {

    private final SkillRepository skillRepository;

    /**
     * List containing all collaborators.
     */
    private final List<Collaborator> collaborators;

    /**
     * Constructs a new CollaboratorRepository with the specified skill repository.
     *
     * @param skillRepository the skill repository to use
     */
    public CollaboratorRepository(SkillRepository skillRepository) {
        super("collaboratorRepository.ser");
        List<Collaborator> collaborators1;
        this.skillRepository = skillRepository;
        collaborators1 = super.load();
        if (collaborators1 == null) {
            this.collaborators = new ArrayList<>();
        } else {
            this.collaborators = collaborators1;
        }
    }

    public CollaboratorRepository(SkillRepository skillRepository, String filename) {
        super(filename);
        List<Collaborator> collaborators1;
        this.skillRepository = skillRepository;
        collaborators1 = super.load();
        if (collaborators1 == null) {
            this.collaborators = new ArrayList<>();
        } else {
            this.collaborators = collaborators1;
        }
    }

    /**
     * Retrieves a collaborator from the repository by their ID document number.
     *
     * @param IdDocNumber the ID document number of the collaborator
     * @return the collaborator
     * @throws IllegalArgumentException if the collaborator is not found
     */
    public Collaborator getCollaborator(String IdDocNumber) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getIdDocNumber().equals(IdDocNumber)) {
                return collaborator;
            }
        }
        throw new IllegalArgumentException("The collaborator with ID number " + IdDocNumber + " does not exist.");
    }

    /**
     * Adds a collaborator to the repository.
     *
     * @param newCollaborator the collaborator to add
     * @throws IllegalArgumentException if the collaborator is invalid
     */
    public void addCollaborator(Collaborator newCollaborator) {
        if (!validateCollaborator(newCollaborator)) {
            throw new IllegalArgumentException("Invalid collaborator to add");
        }
        collaborators.add(newCollaborator);
        saveCollaboratorRepositoryToFile();
    }

    /**
     * Private method to validate if a collaborator is not already in the repository.
     *
     * @param collaborator the collaborator to validate
     * @return true if the collaborator is not in the repository, false otherwise
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * Retrieves an immutable copy of the collaborator list.
     *
     * @return the collaborator list
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
     * @throws IllegalArgumentException if the collaborator is not found in the repository or the skill is invalid
     */
    public void assignSkill(Collaborator collaborator, Skill skill) {
        if (!collaborators.contains(collaborator)) {
            throw new IllegalArgumentException("Collaborator not found in repository");
        }
        if (!validateSkill(skill)) {
            throw new IllegalArgumentException("Invalid skill");
        }
        collaborator.assignSkill(skill);
        saveCollaboratorRepositoryToFile();
    }

    /**
     * Checks if a skill is valid (e.g., exists in the list of available skills).
     *
     * @param skill the skill to validate
     * @return true if the skill is valid, false otherwise
     */
    private boolean validateSkill(Skill skill) {
        return skillRepository.listSkills().contains(skill);
    }

    /**
     * Retrieves a collaborator from the repository by their email.
     *
     * @param email the email of the collaborator
     * @return the collaborator
     * @throws IllegalArgumentException if the collaborator with the specified email is not found
     */
    public Collaborator getCollaboratorByEmail(String email) {
        return collaborators.stream()
                .filter(collaborator -> collaborator.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Collaborator with email " + email + " not found"));
    }

    /**
     * Checks if a collaborator exists in the repository.
     *
     * @param collaborator the collaborator to check
     * @return true if the collaborator exists, false otherwise
     */
    public boolean exist(Collaborator collaborator) {
        return collaborators.contains(collaborator);
    }

    public void saveCollaboratorRepositoryToFile() {
        save(collaborators);
    }

    public void clear() {
        collaborators.clear();
        super.clear();
    }
}
