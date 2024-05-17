package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Scanner;

/**
 * Console-based user interface for registering a new collaborator.
 * This class provides methods to interact with the user and register a new collaborator.
 * It delegates the registration operation to the RegisterCollaboratorController.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterCollaboratorUI implements Runnable {
    private final RegisterCollaboratorController controller;

    /**
     * Constructs a new RegisterCollaboratorUI object and initializes it with a RegisterCollaboratorController instance.
     */
    public RegisterCollaboratorUI() {
        this.controller = new RegisterCollaboratorController();
    }

    /**
     * Runs the collaborator registration process.
     * Prompts the user for collaborator information and registers the collaborator.
     * Prints a success message after successful registration.
     */
    @Override
    public void run() {
        System.out.println("\n--- Register New Collaborator ---\n");

        Scanner scanner = new Scanner(System.in);

        // Requesting basic information from HRM
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Date birthDate = promptForBirthDate(scanner);

        Date admissionDate = promptForAdmissionDate(scanner, birthDate);

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter contact number: ");

        int mobile = validateMobileNumber(scanner);

        System.out.print("Enter email address: ");

        String email = validateEmail(scanner);

        System.out.print("Enter taxpayer number: ");

        int taxpayerNumber = validateTaxpayerNumber(scanner);

        System.out.print("Enter ID document type (e.g., Passport, Driver's License, CC): ");
        String idDocType = chooseIdDocumentType(scanner);

        System.out.print("Enter ID document number: ");
        int idDocNumber = 0;

        try {
            idDocNumber = scanner.nextInt();

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine();
            System.out.print("Enter ID document number: ");
        }

        scanner.nextLine();

        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        String jobName = null;
        valid = false;

        while (!valid) {
            System.out.print("Enter job name: ");
            jobName = scanner.nextLine();

            if (jobRepository.exists(jobName)) {
                valid = true;
            } else {
                System.out.println("Job does not exist in the repository. Please enter a valid job name.");
            }
        }

        // Registering the collaborator
        controller.registerCollaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber,
                idDocType, idDocNumber, jobName);

        System.out.println("\nCollaborator successfully registered!");
    }

    private String chooseIdDocumentType(Scanner scanner) {

        for (Collaborator.IdDocType type : Collaborator.IdDocType.values()) {
            System.out.println(type.ordinal() + 1 + " - " + type.getDisplayName());
        }

        int choice = scanner.nextInt();
        Collaborator.IdDocType idDocumentType = Collaborator.IdDocType.values()[choice - 1];
        return idDocumentType.toString();
    }

    private int validateMobileNumber(Scanner scanner){
        int mobile = 0;
        boolean valid = false;

        while (!valid) {

            try {
                mobile = scanner.nextInt();
                if (mobile > 99999999) {
                    valid = true;
                } else {
                    System.out.println("Invalid input. The number must be exactly 9 digits.");
                    System.out.print("Enter contact number: ");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                System.out.print("Enter contact number: ");
            }
        }
        scanner.nextLine();
        return mobile;
    }

    private String validateEmail(Scanner scanner){
        String email = null;
        boolean valid = false;

        while (!valid) {

            email = scanner.nextLine();

            if (email.contains("@")) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid email address.");
                System.out.print("Enter email address: ");
            }
        }
        return email;
    }

    private int validateTaxpayerNumber(Scanner scanner){
        int taxpayerNumber = 0;
        boolean valid = false;

        while (!valid) {

            try {
                taxpayerNumber = scanner.nextInt();
                if (taxpayerNumber > 99999999) {
                    valid = true;
                } else {
                    System.out.println("Invalid input. The taxpayer number must be exactly 9 digits.");
                    System.out.print("Enter taxpayer number: ");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                System.out.print("Enter taxpayer number: ");
            }
        }

        scanner.nextLine();
        return taxpayerNumber;
    }

    /**
     * Prompts the user to enter a date and parses it into a Date object.
     *
     * @param scanner the Scanner object to read input from the user
     * @return the Date object representing the parsed date
     */
    private static Date promptForBirthDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter birthdate (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();

            String[] parts = dateStr.split("-");
            if (parts.length != 3) {
                System.out.println("Invalid format. Please enter the date in YYYY-MM-DD format.");
                continue;
            }

            try {
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);

                Date birthDate = new Date(year, month, day);
                Date currentDate = Date.currentDate();

                if (currentDate.difference(birthDate) < 18){
                    System.out.println("Invalid date. Enter again!");
                    continue;
                }
                return birthDate;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date. Please make sure you enter valid year, month, and day numbers.");
            }
        }
    }

    private static Date promptForAdmissionDate(Scanner scanner, Date birthDate) {
        while (true) {
            System.out.print("Enter admission date (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();

            String[] parts = dateStr.split("-");
            if (parts.length != 3) {
                System.out.println("Invalid format. Please enter the date in YYYY-MM-DD format.");
                continue;
            }

            try {
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);

                Date admissionDate = new Date(year, month, day);

                if (admissionDate.difference(birthDate) < 18){
                    System.out.println("Invalid date. Enter again!");
                    continue;
                }
                return admissionDate;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date. Please make sure you enter valid year, month, and day numbers.");
            }
        }

    }
}
