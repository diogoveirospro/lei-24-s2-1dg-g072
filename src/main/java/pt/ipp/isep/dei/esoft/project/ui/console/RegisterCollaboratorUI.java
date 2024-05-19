package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;
import pt.ipp.isep.dei.esoft.project.exceptions.InvalidCollaboratorDataException;
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
        String name = validateName(scanner);

        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        Date birthDate = validateBirthDate(scanner);

        System.out.print("Enter admission date (YYYY-MM-DD): ");
        Date admissionDate = validateAdmissionDate(scanner, birthDate);

        System.out.print("Enter address: ");
        String address = validateAddress(scanner);

        System.out.print("Enter contact number: ");
        int mobile = validateMobileNumber(scanner);

        System.out.print("Enter email address: ");
        String email = validateEmail(scanner);

        System.out.print("Enter taxpayer number: ");
        int taxpayerNumber = validateTaxpayerNumber(scanner);

        System.out.println("Enter ID document type: ");
        Collaborator.IdDocType idDocType = chooseIdDocumentType(scanner);

        System.out.print("Enter ID document number: ");
        String idDocNumber = validateIdDocumentNumber(scanner, idDocType);

        System.out.print("Enter job name: ");
        String jobName = assignJob(scanner);

        // Registering the collaborator
        controller.registerCollaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber,
                idDocType, idDocNumber, jobName);

        System.out.println("\nCollaborator successfully registered!");
    }

    private String validateName(Scanner scanner) {
        String name = "";
        boolean valid = false;

        while (!valid){

            try {
                name = scanner.nextLine();
                valid = ValidatorUtils.isValidName(name);

            }catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter name: ");
            }
        }
        return name;
    }

    private static Date validateBirthDate(Scanner scanner) {
        boolean valid = false;
        Date birthDate = null;

        while (!valid) {

            try {
                String dateStr = scanner.nextLine();
                birthDate = ValidatorUtils.validateBirthDate(dateStr);
                valid = true;
            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.print("Enter birthdate (YYYY-MM-DD): ");
            }
        }

        return birthDate;
    }

    private static Date validateAdmissionDate(Scanner scanner, Date birthDate) {
        boolean valid = false;
        Date admissionDate = null;

        while (!valid) {

            try {
                String dateStr = scanner.nextLine();
                admissionDate = ValidatorUtils.validateAdmissionDate(dateStr, birthDate);
                valid = true;
            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.print("Enter admission date (YYYY-MM-DD): ");
            }
        }
        return admissionDate;
    }

    private String validateAddress(Scanner scanner){
        String address = "";
        boolean valid = false;

        while (!valid) {

            try {
                address = scanner.nextLine();
                valid = ValidatorUtils.isValidAddress(address);
            } catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter address: ");
            }
        }
        return address;
    }

    private int validateMobileNumber(Scanner scanner){
        String mobile = "";
        boolean valid = false;

        while (!valid) {

            try {
                mobile = scanner.nextLine();
                valid = ValidatorUtils.isValidMobileNumber(mobile);

            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.print("Enter contact number: ");
            }
        }
        return Integer.parseInt(mobile);
    }

    private String validateEmail(Scanner scanner){
        String email = "";
        boolean valid = false;

        while (!valid) {

            try {
                email = scanner.nextLine();
                valid = ValidatorUtils.isValidEmail(email);

            } catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter email address: ");
            }
        }
        return email;
    }

    private int validateTaxpayerNumber(Scanner scanner){
        String taxpayerNumber = "";
        boolean valid = false;

        while (!valid) {

            try {
                taxpayerNumber = scanner.nextLine();
                valid = ValidatorUtils.isValidTaxpayerNumber(taxpayerNumber);

            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.print("Enter taxpayer number: ");
            }
        }

        return Integer.parseInt(taxpayerNumber);
    }

    private Collaborator.IdDocType chooseIdDocumentType(Scanner scanner) {

        for (Collaborator.IdDocType type : Collaborator.IdDocType.values()) {
            System.out.println(type.ordinal() + 1 + " - " + type.getDisplayName());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        return Collaborator.IdDocType.values()[choice - 1];
    }

    private String validateIdDocumentNumber(Scanner scanner, Collaborator.IdDocType idDocType){
        String idDocNumber = "";
        boolean valid = false;

        while (!valid){

            try {
                idDocNumber = scanner.nextLine();
                valid = ValidatorUtils.isValidDocumentNumber(idDocType, idDocNumber);

            } catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter ID document number: ");
            }

        }

        return idDocNumber;
    }

    private String assignJob(Scanner scanner){
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        String jobName = null;
        boolean valid = false;

        while (!valid) {
            jobName = scanner.nextLine();
            if (jobRepository.exists(jobName)) {
                valid = true;
            } else {
                System.out.println("Job does not exist in the repository. Please enter a valid job name.");
                System.out.print("Enter job name: ");
            }

        }

        return jobName;
    }



}
