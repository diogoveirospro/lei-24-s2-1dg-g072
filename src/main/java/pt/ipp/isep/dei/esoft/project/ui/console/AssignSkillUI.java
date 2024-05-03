package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;

public class AssignSkillUI implements Runnable {

    private AssignSkillController controller;

    public AssignSkillUI(){
        this.controller = new AssignSkillController();
    }


    @Override
    public void run() {

    }
}
