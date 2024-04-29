package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;

/**
 * The RegisterJobUI class represents a user interface component responsible for
 * interacting with the system to register new jobs. It uses a RegisterJobController
 * to handle the job registration.
 */

public class RegisterJobUI {
    private RegisterJobController controller;

    /**
     * Constructs a new RegisterJobUI object and initializes it with a
     * RegisterJobController instance for handling job registration operations.
     */

    public RegisterJobUI (){
        this.controller = new RegisterJobController();
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
}
