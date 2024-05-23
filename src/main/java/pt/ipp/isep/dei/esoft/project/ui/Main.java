package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

/**
 *
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Main {

    public static void main(String[] args) throws InvalidCollaboratorDataException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
