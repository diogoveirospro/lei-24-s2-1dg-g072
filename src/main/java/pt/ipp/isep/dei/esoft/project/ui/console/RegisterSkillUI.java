package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;

import java.util.Scanner;

/**
 * The RegisterSkillUI class provides a user interface to interact with the system
 * for registering new skills. It facilitates the input of new skill names and
 * communicates with the RegisterSkillController to process the registration.
 *
 * @author Group 072 - Byte Masters - ISEP
 */

public class RegisterSkillUI implements Runnable{
    private final RegisterSkillController controller;
    private Scanner scanner;

    /**
     * Creates an instance of RegisterSkillUI with a specified RegisterSkillController.
     * This constructor allows the UI to delegate skill registration operations to the controller.
     */

    public RegisterSkillUI() {
        this.controller = new RegisterSkillController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initiates the registration of a skill with the provided name. This method
     * calls the registerSkill method of the associated RegisterSkillController.
     *
     * @param name the name of the skill to be registered
     */

    public void registerSkill(String name) {
        controller.registerSkill(name);
    }

    @Override
    public void run() {
        System.out.println("\n\n---- Skill Registration---------------");

        while(true){
            System.out.print("Enter the name of the skill to register (or exit to leave)");
            String skillName = scanner.nextLine();

            if ("exit".equalsIgnoreCase(skillName)){
                break;
            }
            if (skillName.isEmpty()){
                System.out.println("Skill can not be empty. Please try again");
                continue;
            }

            registerSkill(skillName);
            System.out.println(skillName + "has been successfully registered");

            System.out.println("\nDo you want to register another skill? [Y|N]:");
            String answer = scanner.nextLine();
            if("N".equalsIgnoreCase(answer)){
                break;
            }
        }
        System.out.println("Skill registration is over");
    }
}
