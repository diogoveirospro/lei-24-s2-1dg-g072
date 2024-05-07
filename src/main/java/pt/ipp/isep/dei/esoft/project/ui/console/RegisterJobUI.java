package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;

import java.util.Scanner;

/**
 * The RegisterJobUI class represents a user interface component responsible for
 * interacting with the system to addSkill new jobs. It uses a RegisterJobController
 * to handle the job registration.
 *
 * @author Group 072 - Byte Masters - ISEP
 */

public class RegisterJobUI implements Runnable {
    private final RegisterJobController controller;
    private final Scanner scanner;

    /**
     * Constructs a new RegisterJobUI object and initializes it with a
     * RegisterJobController instance for handling job registration operations.
     */

    public RegisterJobUI (){
        this.controller = new RegisterJobController();
        this.scanner = new Scanner (System.in);
    }

    /**
     * Simulates a user action of registering a new job with the specified name.
     * This method delegates the registration operation to the underlying
     * RegisterJobController.
     *
     * @param jobName the name of the job to be registered
     */

    public void simulateUserAction (String jobName){
        controller.registerJob(jobName);
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Job Registration -----------------");

        System.out.print("Enter the name of the job to be registered: ");

        String jobName = scanner.nextLine();
        controller.registerJob(jobName);

        System.out.println("\nThe Job " + jobName + " has been successfully registered.");

    }
}
