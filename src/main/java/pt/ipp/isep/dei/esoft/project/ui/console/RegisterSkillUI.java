package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;

/**
 * The RegisterSkillUI class provides a user interface to interact with the system
 * for registering new skills. It facilitates the input of new skill names and
 * communicates with the RegisterSkillController to process the registration.
 */

public class RegisterSkillUI implements Runnable{
    private final RegisterSkillController controller;

    /**
     * Creates an instance of RegisterSkillUI with a specified RegisterSkillController.
     * This constructor allows the UI to delegate skill registration operations to the controller.
     */

    public RegisterSkillUI() {
        this.controller = new RegisterSkillController();
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

    }
}
