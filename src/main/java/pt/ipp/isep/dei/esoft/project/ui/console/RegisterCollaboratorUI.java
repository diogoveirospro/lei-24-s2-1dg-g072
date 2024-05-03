package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

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

        System.out.print("Enter birth date (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();

        System.out.print("Enter admission date (YYYY-MM-DD): ");
        String admissionDate = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter taxpayer number: ");
        String taxpayerNumber = scanner.nextLine();

        System.out.print("Enter ID document type (e.g., Passport, Driver's License): ");
        String idDocType = scanner.nextLine();

        System.out.print("Enter ID document number: ");
        String idNumber = scanner.nextLine();

        // Registering the collaborator
        boolean isRegistered = controller.registerCollaborator(name, birthDate, admissionDate, address, contact, email, taxpayerNumber, idDocType, idNumber);

        if (isRegistered) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nFailed to register collaborator. Please check the provided details and try again.");
        }
    }
}
