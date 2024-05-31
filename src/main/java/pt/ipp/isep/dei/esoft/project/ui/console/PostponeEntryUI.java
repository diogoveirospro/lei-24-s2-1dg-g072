package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PostponeEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;
import java.util.Scanner;

public class PostponeEntryUI implements Runnable{

    private PostponeEntryController controller;

    private Entry selectedEntry;
    private Scanner sc = new Scanner(System.in);

    public PostponeEntryUI(){
        this.controller = new PostponeEntryController();
    }

    @Override
    public void run() {
        selectedEntry = displayAndSelectEntry();
    }

    private Entry displayAndSelectEntry() {
        List<Entry> entryList = controller.getAgendaEntryList();
        System.out.println("Select the entry you want to postpone:");
        displayEntryList(entryList);


    }

    private void displayEntryList(List<Entry> entryList) {
        int i = 0;
        for (Entry entry : entryList) {
            System.out.println(i + " - " + entry.toString());
            i++;
        }
    }
}
