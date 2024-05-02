package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateTeamProposalUI implements Runnable {

    private final GenerateTeamProposalController controller;
    private List<Skill> selectedSkills;
    List<Collaborator> members;
    private int minimumSize;
    private int maximumSize;
    Scanner scanner = new Scanner(System.in);

    public GenerateTeamProposalUI() {
        this.controller = new GenerateTeamProposalController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Generate Team Proposal ------------------------");

        minimumSize = promptUserForInteger("Enter minimum team size: ");
        maximumSize = promptUserForInteger("Enter maximum team size: ");

        selectedSkills = displayAndSelectSkills();

        showData();

        System.out.println("Is everything correct? [Y/N]");
        String confirmation = scanner.nextLine();

        while(confirmation.equalsIgnoreCase("N")) {
            changeData();
            System.out.println("Is everything correct? [Y/N]");
            confirmation = scanner.nextLine();
        }

        List<Collaborator> collaborators = controller.getCollaborators();
        members = controller.generateTeamProposal(minimumSize, maximumSize, selectedSkills, collaborators);


        System.out.println("\nTeam Members:");
        for (Collaborator member : members) {
            System.out.println("- " + member.getName() + " - " + member.getIdDocumentNumber());
        }

        System.out.println("Do you agree with this team proposal? [Y/N]");
        confirmation = scanner.nextLine();

        while (confirmation.equalsIgnoreCase("N")){
            changeMember();
            System.out.println("Do you agree with this team proposal? [Y/N]");
            confirmation = scanner.nextLine();
        }

        controller.createTeam(members);
        System.out.println("Team successfully created!");


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

    private void displaySkillsOptions(List<Skill> skills) {
        for (Skill skill : skills) {
            System.out.println("- " + skill.getName());
        }
    }

    private void displayCollaborators(List<Collaborator> collaborators){
        for (Collaborator collaborator : collaborators){
            System.out.println("- " + collaborator.getName() + " - " + collaborator.getIdDocumentNumber());
        }
    }

    private Skill findSkillByName(List<Skill> skills, String name) {
        for (Skill skill : skills) {
            if (skill.getName().equalsIgnoreCase(name)) {
                return skill;
            }
        }
        return null;
    }

    private Collaborator findCollaboratorByIDNumber(List<Collaborator> collaborators, int idNumber) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getIdDocumentNumber() == idNumber) {
                return collaborator;
            }
        }
        return null;
    }

    private int promptUserForInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + message);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void showSkillsSelected(){

        System.out.println("The skills he selected were the following: ");

        for (Skill skillSelected : selectedSkills){
            System.out.println(skillSelected.getName());
        }

    }

    private void changeSkillSelected(){

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

    private void showData(){
        System.out.println("He typed the following: ");
        System.out.println("Minimum team size - " + minimumSize);
        System.out.println("Maximum team size - " + maximumSize);
        showSkillsSelected();
    }

    private void changeData(){

            System.out.println("What do you want to change (enter the corresponding number)?");
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

    private void changeMember(){

        List<Collaborator> collaborators = controller.getCollaborators();

            System.out.println("Enter the ID Number of the member you want to remove (or 0 to cancel): ");
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
            System.out.print("Enter the ID Number of the collaborator you want to select (or 0 to cancel): ");
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
                        System.out.println("The team members don't have all the necessary skills, do you want to continue? [Y/N]");
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

    private List<Skill> getMembersSkills(){

        List<Skill> membersSkills = new ArrayList<>();

        for (Collaborator member : members){
            membersSkills.addAll(member.getSkills());
        }

        return membersSkills;
    }
}
