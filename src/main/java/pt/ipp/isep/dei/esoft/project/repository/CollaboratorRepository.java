package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class CollaboratorRepository {

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
}
