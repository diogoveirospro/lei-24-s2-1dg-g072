package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;

public class RegisterJobUI {
    private RegisterJobController controller;

    public RegisterJobUI (){
        this.controller = new RegisterJobController();
    }

    public void simulateUserAction (String jobName){
        controller.registerJob(jobName);
    }
}
