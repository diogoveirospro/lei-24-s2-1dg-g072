package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Group 072 - Byte Masters - ISEP
 */
public class AssignSkillUI implements Runnable {

    /**
     * Assign skill controller.
     */
    private AssignSkillController controller;

    /**
     * Skills selected by the HRM.
     */
    private List<Skill> selectedSkills;

    /**
     * Collaborator selected by the HRM.
     */
    private Collaborator selectedCollaborator;

    /**
     * Scanner for input.
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Assign skill UI builder
     */
    public AssignSkillUI() {
        this.controller = new AssignSkillController();
    }

    /**
     * Rewriting the run method so that it is possible to run the UI that allows the HRM to assign skills to a collaborator.
     */
    @Override
    public void run() {

        selectedCollaborator = displayAndSelectCollaborator();

        selectedSkills = displayAndSelectSkills();

        showData();

        System.out.println("Is everything correct? [Y/N]");
        String confirmation = scanner.nextLine();

        while (confirmation.equalsIgnoreCase("N")) {
            changeData();
            System.out.println("Is everything correct? [Y/N]");
            confirmation = scanner.nextLine();
        }

        submitData();

        System.out.println(selectedCollaborator.getIdDocNumber() + " was assigned the following skills:");
        for (Skill selectedSkill : selectedSkills) {
            System.out.println("-" + selectedSkill.getName());
        }
    }

    /**
     * Assigns the selected skills to the selected collaborator.
     */
    private void submitData() {
        for (Skill selectedSkill : selectedSkills) {
            selectedCollaborator.assignSkill(selectedSkill);
        }
    }

    /**
     * Presentation of the list of collaborators to the user and their subsequent selection.
     *
     * @return selected collaborator.
     */
    private Collaborator displayAndSelectCollaborator() {
        List<Collaborator> collaborators = controller.getCollaborators();
        System.out.println("\nAvailable Collaborators:");
        displayCollaborators(collaborators);

        Collaborator collaborator = null;
        int answer;

        do {
            //Only one collaborator must be chosen at the time
            System.out.print("Enter the a number on the list of collaborators to select one: ");
            answer = scanner.nextInt();
            try {
                if (answer >= 1 && answer <= collaborators.size()) {
                    Collaborator collaboratorToAssign = collaborators.get(answer - 1);
                    Collaborator selectedCollaborator = findCollaboratorIDDocNumber(collaborators, Integer.parseInt(collaboratorToAssign.getIdDocNumber()));
                    if (selectedCollaborator != null) {
                        collaborator = selectedCollaborator;
                        System.out.println("'" + selectedCollaborator.getIdDocNumber() + "' selected.");
                    }
                } else {
                    System.out.println("Invalid selection. Please select a number between 1 and " + collaborators.size() + " or 'done' to finish.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }


        } while (collaborator == null);
        return collaborator;
    }


    /**
     * Displays the selected collaborator.
     *
     * @param collaborators collaborator repository.
     */
    private void displayCollaborators(List<Collaborator> collaborators) {
        int index = 1;
        for (Collaborator collaborator : collaborators) {
            System.out.println(index + " - " + collaborator.getName() + ", " + collaborator.getIdDocNumber());
            index++;
        }
    }

    /**
     * Presentation of the list of skills to the user and their subsequent selection.
     *
     * @return skills selected by the user.
     */
    private List<Skill> displayAndSelectSkills() {
        List<Skill> skills = controller.listSkills();

        System.out.println("\nAvailable Skills:");
        displaySkillsOptions(skills);

        List<Skill> selectedSkills = new ArrayList<>();
        String choice;
        System.out.println("Please select a number between 1 and " + skills.size() + ".");
        choice = scanner.nextLine().trim();
        do {

            try {
                if (!choice.equalsIgnoreCase("done")) {

                    if (Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= skills.size()) {
                        String answer = skills.get(Integer.parseInt(choice) - 1).getName();
                        Skill selectedSkill = findSkillByName(skills, answer);

                        if (selectedSkill != null && !selectedSkills.contains(selectedSkill)) {
                            selectedSkills.add(selectedSkill);
                            System.out.println("'" + answer + "' selected.");
                        } else {
                            if (selectedSkills.contains(selectedSkill)){
                                System.out.println("'" + answer + "' already selected.");
                            } else {
                                System.out.println("Skill not found. Try again.");
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            System.out.println("Please select a number between 1 and " + skills.size() + " or 'done' to finish.");
            choice = scanner.nextLine().trim();
        } while (!choice.equalsIgnoreCase("done"));

        return selectedSkills;
    }

    /**
     * Find a skill by its name.
     *
     * @param skills skill repository.
     * @param name   skill's name.
     * @return skill
     */
    private Skill findSkillByName(List<Skill> skills, String name) {
        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(name)) {
                return skill;
            }
        }
        return null;
    }

    /**
     * Find collaborator by name.
     *
     * @param collaborators collaborator repository.
     * @param idDocNumber   collaborator ID Document Number.
     * @return collaborator.
     */
    private Collaborator findCollaboratorIDDocNumber(List<Collaborator> collaborators, int idDocNumber) {
        for (Collaborator collaborator : collaborators) {
            if (Integer.parseInt(collaborator.getIdDocNumber()) == idDocNumber) {
                return collaborator;
            }
        }
        return null;
    }

    /**
     * Presentation of the list of skills
     *
     * @param skills skill repository.
     */
    private void displaySkillsOptions(List<Skill> skills) {
        int index = 1;
        for (Skill skill : skills) {
            System.out.println(index + " - " + skill.getName());
            index++;
        }
        scanner.nextLine();
    }

    /**
     * Show all data.
     */
    private void showData() {
        System.out.println("Chosen collaborator: ");

        System.out.println(selectedCollaborator.getName());

        showSkillsSelected();
    }

    /**
     * Show the selected skills.
     */
    private void showSkillsSelected() {
        int index = 1;
        System.out.println("The selected skills were the following: ");

        for (Skill skillSelected : selectedSkills) {
            System.out.println(index + " - "+ skillSelected.getName());
            index++;
        }

    }

    /**
     * Change the selected data.
     */
    private void changeData() {

        changeSkillSelected();

        showData();
    }

    /**
     * Change the selected skills.
     */
    private void changeSkillSelected() {
        System.out.println("Choose the skills you want removed.");
        showSkillsSelected();
        String choice;
        System.out.println("Please select a number between 1 and " + selectedSkills.size() + " or 'done' to finish.");
        choice = scanner.nextLine().trim();
        do {

            try {
                if (!choice.equalsIgnoreCase("done")) {

                    if (Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= selectedSkills.size()) {
                        String answer = selectedSkills.get(Integer.parseInt(choice) - 1).getName();
                        Skill selectedSkill = findSkillByName(selectedSkills, answer);

                        if (selectedSkill != null) {
                            selectedSkills.remove(selectedSkill);
                            System.out.println("'" + answer + "' removed.");
                        } else System.out.println("Skill not found. Try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            showSkillsSelected();
            System.out.println("Please select a number between 1 and " + selectedSkills.size() + " or 'done' to finish.");
            choice = scanner.nextLine().trim();
        } while (!choice.equalsIgnoreCase("done"));

    }

}
