package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * UI for generating team proposals.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class GenerateTeamProposalUI implements Runnable {

    /**
     * Generate team proposal controller
     */
    private final GenerateTeamProposalController controller;

    /**
     * Minimum team size
     */
    private int minimumSize;

    /**
     * Maximum team size
     */
    private int maximumSize;

    /**
     * Skills selected by HRM
     */
    private List<Skill> selectedSkills;

    /**
     * Team proposal members
     */
    List<Collaborator> members;

    /**
     * Scanner
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * Generate Team Proposal UI builder.
     */
    public GenerateTeamProposalUI() {
        this.controller = new GenerateTeamProposalController();
    }

    /**
     * Rewriting the run method so that it is possible to run the UI that allows a team proposal to be generated.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Generate Team Proposal ------------------------");

        minimumSize = promptUserForInteger("Enter minimum team size: ");
        maximumSize = promptUserForInteger("Enter maximum team size: ");

        selectedSkills = displayAndSelectSkills();

        showData();

        System.out.println("\nIs everything correct? [Y/N]");
        String confirmation = scanner.nextLine();

        while(confirmation.equalsIgnoreCase("N")) {
            changeData();
            System.out.println("\nIs everything correct? [Y/N]");
            confirmation = scanner.nextLine();
        }

        List<Collaborator> collaborators = controller.getCollaborators();
        members = controller.generateTeamProposal(minimumSize, maximumSize, selectedSkills, collaborators);


        System.out.println("\nTeam Members:");
        for (Collaborator member : members) {
            System.out.println("- " + member.getName() + " - " + member.getIdDocNumber());
        }

        System.out.println("\nDo you agree with this team proposal? [Y/N]");
        confirmation = scanner.nextLine();

        while (confirmation.equalsIgnoreCase("N")){
            changeMember();
            System.out.println("\nDo you agree with this team proposal? [Y/N]");
            confirmation = scanner.nextLine();
        }

        controller.createTeam(members);
        System.out.println("\nTeam successfully created!");


    }

    /**
     * Presentation of the list of skills to the user and their subsequent selection.
     * @return skills selected by the user.
     */
    private List<Skill> displayAndSelectSkills() {
        List<Skill> skills = controller.listSkills();

        System.out.println("\nAvailable Skills:");
        displaySkillsOptions(skills);

        List<Skill> selectedSkills = new ArrayList<>();
        String answer;

        do {
            System.out.print("\nEnter the name of a skill to select (or type 'done' to finish): ");
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

    /**
     * Presentation of the list of skills.
     * @param skills skill repository.
     */
    private void displaySkillsOptions(List<Skill> skills) {
        for (Skill skill : skills) {
            System.out.println("- " + skill.getName());
        }
        scanner.nextLine();
    }

    /**
     * Presentation of the list of collaborators.
     * @param collaborators collaborators repository.
     */
    private void displayCollaborators(List<Collaborator> collaborators){
        for (Collaborator collaborator : collaborators){
            System.out.println("- " + collaborator.getName() + " - " + collaborator.getIdDocNumber());
        }
    }

    /**
     * Find a skill by its name.
     * @param skills skill repository
     * @param name skill's name
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
     * Find a collaborator by its ID number.
     * @param collaborators collaborator repository
     * @param idNumber ID number's collaborator
     * @return collaborator
     */
    private Collaborator findCollaboratorByIDNumber(List<Collaborator> collaborators, int idNumber) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getIdDocNumber() == idNumber) {
                return collaborator;
            }
        }
        return null;
    }

    /**
     * Prompts the user for an integer input and returns the entered value.
     * @param message message to be displayed to the user
     * @return value entered
     */
    private int promptUserForInteger(String message) {

        System.out.print(message);
        while (!scanner.hasNextInt() || scanner.nextInt() <= 0) {
            System.out.print("Invalid input. " + message);
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Presentation of the list of selected skills.
     */
    private void showSkillsSelected(){

        System.out.println("\nThe skills he selected were the following: ");

        for (Skill skillSelected : selectedSkills){
            System.out.println(skillSelected.getName());
        }

    }

    /**
     * Change an already selected skill.
     */
    private void changeSkillSelected(){

        List<Skill> skills = controller.listSkills();

        System.out.println("\nEnter the name of the skill to change: ");
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
            System.out.print("\nEnter the name of a skill to select (or type 'done' to finish): ");
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

    /**
     * Show all data.
     */
    private void showData(){
        System.out.println("\nHe typed the following: ");
        System.out.println("Minimum team size - " + minimumSize);
        System.out.println("Maximum team size - " + maximumSize);
        showSkillsSelected();
    }

    /**
     * Change the data entered.
     */
    private void changeData(){

            System.out.println("\nWhat do you want to change (enter the corresponding number)?");
            System.out.println("1 - Minimum team size\n2 - Maximum team size\n3 - Skills\nAnother number to cancel");
            int answer = scanner.nextInt();

            if (answer == 1){

                minimumSize = promptUserForInteger("Enter minimum team size: ");
                showData();

            } else if (answer == 2) {

                maximumSize = promptUserForInteger("Enter maximum team size: ");
                showData();

            } else if (answer == 3) {

                changeSkillSelected();
                showData();

            }


    }

    /**
     * Change a member of the proposed team.
     */
    private void changeMember(){

        List<Collaborator> collaborators = controller.getCollaborators();

            System.out.println("\nEnter the ID Number of the member you want to remove (or 0 to cancel): ");
            int ID = scanner.nextInt();
            Collaborator memberToChange = findCollaboratorByIDNumber(collaborators, ID);

            if (memberToChange != null) {

                members.remove(memberToChange);
                System.out.println("'" + ID + "' removed.");

            } else {

                System.out.println("Collaborator does not belong to the team. Try again.");

            }


        displayCollaborators(collaborators);
        int answer;

        do {
            System.out.print("\nEnter the ID Number of the collaborator you want to select (or 0 to cancel): ");
            answer = scanner.nextInt();

            if (answer != 0) {

                Collaborator selectedMember = findCollaboratorByIDNumber(collaborators, answer);

                if (selectedMember != null) {
                    members.add(selectedMember);
                    boolean hasAllSkills = true;

                    List<Skill> membersSkills = getMembersSkills();

                    for (Skill skill : selectedSkills){

                        if (membersSkills.contains(skill)){
                            membersSkills.remove(skill);
                        }else {
                            hasAllSkills = false;
                        }
                    }

                    if (!hasAllSkills){
                        System.out.println("\nThe team members don't have all the necessary skills, do you want to continue? [Y/N]");
                        String answer2 = scanner.nextLine().trim();

                        if (answer2.equalsIgnoreCase("N")){
                            changeMember();
                        }else {
                            System.out.println("'" + answer + "' selected.");
                        }

                    }else {
                        System.out.println("'" + answer + "' selected.");
                    }

                } else {
                    System.out.println("Collaborator not found. Try again.");
                }
            }
        } while (answer != 0);

    }

    /**
     * Obtain the skills of team members
     * @return skill's members
     */
    private List<Skill> getMembersSkills(){

        List<Skill> membersSkills = new ArrayList<>();

        for (Collaborator member : members){
            membersSkills.addAll(member.getSkillSet());
        }

        return membersSkills;
    }
}
