package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamProposalController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleMaintenanceController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;
import java.util.Scanner;

public class GenerateTeamProposalUI implements Runnable {

    private final GenerateTeamProposalController controller;
    private List<Skill> skills;

    public GenerateTeamProposalUI(){
        this.controller = new GenerateTeamProposalController();
    }

    public GenerateTeamProposalController getController() {
        return controller;
    }



    @Override
    public void run() {

        System.out.println("\n\n--- Generate Team Proposal ------------------------");

        skills = displayAndSelectSkills();

        //submitData();
    }
/*
    private void submitData() {
        List<Team> maintenances = getController().registerVehicleMaintenance(vehicleList);

        if (!maintenances.isEmpty()) {
            System.out.println("\nVehicle maintenance successfully registered for the following vehicles:");
            for (Maintenance maintenance : maintenances) {
                System.out.println("- Vehicle: " + maintenance.getVehicleList().getPlateNumber());
            }
        } else {
            System.out.println("\nNo vehicle maintenance was registered!");
        }
    }
*/
    private List<Skill> displayAndSelectSkills() {

        List<Skill> skills = controller.listSkills();
        int listSize = skills.size();

        Scanner input = new Scanner(System.in);
        int answer = -1;
        System.out.print("If you want to stop selecting skills, press any number smaller than 0 or higher than" + listSize + ".");

        while (answer != 1) {
            displaySkillsOptions(skills);
            System.out.print("Select a skill: ");

            answer = input.nextInt();
            if (answer < 1 || answer > listSize) {
                skills.add(skills.get(answer - 1));
            }
        }
        return skills;
    }

    private void displaySkillsOptions(List<Skill> skills) {
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }
}
