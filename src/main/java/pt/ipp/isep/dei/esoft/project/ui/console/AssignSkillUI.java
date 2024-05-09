package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class AssignSkillUI implements Runnable {

    private AssignSkillController controller;

    private List<Skill> selectedSkills;

    private Collaborator selectedCollaborator;

    Scanner scanner = new Scanner(System.in);


    public AssignSkillUI() {
        this.controller = new AssignSkillController();
    }


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

        System.out.println(selectedCollaborator.getName() + "was assigned the following skills:");
        for (Skill selectedSkill : selectedSkills) {
            System.out.println("-" + selectedSkill.getName());
        }
    }

    private void submitData() {
        for (Skill selectedSkill : selectedSkills) {
            selectedCollaborator.assignSkill(selectedSkill);
        }
    }

    private Collaborator displayAndSelectCollaborator() {
        List<Collaborator> collaborators = controller.getCollaborators();
        System.out.println("\nAvailable Collaborators:");
        displayCollaborators(collaborators);

        Collaborator collaborator = null;
        String answer;

        do {
            //Only one collaborator must be chosen at the time
            System.out.print("Enter the name of the collaborator to select: ");
            answer = scanner.nextLine().trim();

                Collaborator selectedCollaborator = findCollaboratorName(collaborators, answer);
                if (selectedCollaborator != null) {
                    collaborator = selectedCollaborator;
                    System.out.println("'" + answer + "' selected.");

                } else{
                    System.out.println("Collaborator not found. Try again");
                }

        } while (collaborator == null);
        return collaborator;
    }


    private void displayCollaborators(List<Collaborator> collaborators) {
        for (Collaborator collaborator : collaborators) {
            System.out.println("- " + collaborator.getName());
        }
    }

    private List<Skill> displayAndSelectSkills() {
        List<Skill> skills = controller.listSkills();

        System.out.println("\nAvailable Skills:");
        displaySkillsOptions(skills);

        List<Skill> selectedSkills = new ArrayList<>();
        String answer;

        do {
            System.out.print("Enter the name of a skill to select (or type 'done' to finish): ");
            answer = scanner.nextLine().trim();

            if (!answer.equalsIgnoreCase("done")) {
                Skill selectedSkill = findSkillByName(skills, answer);
                if (selectedSkill != null) {
                    selectedSkills.add(selectedSkill);
                    System.out.println("'" + answer + "' selected.");
                } else {
                    System.out.println("Skill not found. Try again.");
                }
            }
        } while (!answer.equalsIgnoreCase("done"));

        return selectedSkills;
    }

    private Skill findSkillByName(List<Skill> skills, String name) {
        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(name)) {
                return skill;
            }
        }
        return null;
    }

    private Collaborator findCollaboratorName(List<Collaborator> collaborators, String name) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getName().equalsIgnoreCase(name)) {
                return collaborator;
            }
        }
        return null;
    }

    private void displaySkillsOptions(List<Skill> skills) {
        for (Skill skill : skills) {
            System.out.println("- " + skill.getName());
        }
    }

    private void showData() {
        System.out.println("Chosen collaborator: ");

        System.out.println(selectedCollaborator.getName());

        showSkillsSelected();
    }

    private void showSkillsSelected() {

        System.out.println("The selected skills were the following: ");

        for (Skill skillSelected : selectedSkills) {
            System.out.println(skillSelected.getName());
        }

    }

    private void changeData() {

        changeSkillSelected();

        showData();
    }

    private void changeSkillSelected() {

        List<Skill> skills = controller.listSkills();

        System.out.println("Enter the name of the skill to change: ");
        String skillName = scanner.nextLine().trim();
        Skill skillToChange = findSkillByName(skills, skillName);

        if (skillToChange != null) {

            selectedSkills.remove(skillToChange);
            System.out.println("'" + skillName + "' removed.");

        } else {

            System.out.println("Skill not previously selected. Try again.");

        }

        displaySkillsOptions(skills);
        String answer;
        do {
            System.out.print("Enter the name of a skill to select (or type 'done' to finish): ");
            answer = scanner.nextLine().trim();

            if (!answer.equalsIgnoreCase("done")) {
                Skill selectedSkill = findSkillByName(skills, answer);
                if (selectedSkill != null) {
                    selectedSkills.add(selectedSkill);
                    System.out.println("'" + answer + "' selected.");
                } else {
                    System.out.println("Skill not found. Try again.");
                }
            }
        } while (!answer.equalsIgnoreCase("done"));

    }

}
