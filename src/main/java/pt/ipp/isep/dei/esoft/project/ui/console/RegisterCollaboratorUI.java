package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.util.Scanner;

/**
 * Console-based user interface for registering a new collaborator.
 */
public class RegisterCollaboratorUI implements Runnable {
    private RegisterCollaboratorController controller;

    public RegisterCollaboratorUI() {
        this.controller = new RegisterCollaboratorController();
    }

    @Override
    public void run() {
        System.out.println("\n--- Register New Collaborator ---\n");

        Scanner scanner = new Scanner(System.in);

        // Requesting basic information from HRM
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        String date1 = scanner.nextLine();

        String[] partsDate1 = date1.split("-");

        if (partsDate1.length != 3) {
            System.out.println("Invalid format. Make sure you enter the date in YYYY-MM-DD format.");
            return;
        }

        Date birthDate = null;
        Date admissionDate = null;

        try{
            birthDate = new Date(Integer.parseInt(partsDate1[0]), Integer.parseInt(partsDate1[1]), Integer.parseInt(partsDate1[2]));
        }catch (NumberFormatException e1) {
            System.out.println("Invalid format. Make sure you only enter numbers for the year, month and day.");


        System.out.print("Enter admission date (YYYY-MM-DD): ");
            String date2 = scanner.nextLine();

            String[] partsDate2 = date2.split("-");

            if (partsDate2.length != 3) {
                System.out.println("Invalid format. Make sure you enter the date in YYYY-MM-DD format.");
                return;
            }

            try{
                admissionDate = new Date(Integer.parseInt(partsDate2[0]), Integer.parseInt(partsDate2[1]), Integer.parseInt(partsDate2[2]));
            }catch (NumberFormatException e2) {
                System.out.println("Invalid format. Make sure you only enter numbers for the year, month and day.");

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter contact number: ");
        int mobile = scanner.nextInt();

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter taxpayer number: ");
        int taxpayerNumber = scanner.nextInt();

        System.out.print("Enter ID document type (e.g., Passport, Driver's License): ");
        String idDocType = scanner.nextLine();

        System.out.print("Enter ID document number: ");
        int idDocNumber = scanner.nextInt();

        // Registering the collaborator
        boolean isRegistered = controller.registerCollaborator(name, birthDate, admissionDate, address, mobile, email,
                taxpayerNumber, idDocType, idDocNumber);

        if (isRegistered) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nFailed to register collaborator. Please check the provided details and try again.");
        }
    }
}
