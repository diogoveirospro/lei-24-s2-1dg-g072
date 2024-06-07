package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.util.List;
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
        String mobile = validateMobileNumber(scanner);

        System.out.print("Enter email address: ");
        String email = validateEmail(scanner);

        System.out.print("Enter taxpayer number: ");
        String taxpayerNumber = validateTaxpayerNumber(scanner);

        System.out.println("Enter ID document type: ");
        Collaborator.IdDocType idDocType = chooseIdDocumentType(scanner);

        System.out.print("Enter ID document number: ");
        String idDocNumber = validateIdDocumentNumber(scanner, idDocType);

        System.out.println("Enter your password, which must contain seven alphanumeric characters, including three capital letters and two digits: ");
        String password = validatePwd(scanner);

        
        System.out.println("Enter the integer corresponding to the desired job: ");
        String jobName = assignJob(scanner);

        // Registering the collaborator
        try {
            controller.registerCollaborator(name, birthDate, admissionDate, address, mobile, email, taxpayerNumber,
                    idDocType, idDocNumber, jobName,password);
        } catch (InvalidCollaboratorDataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCollaborator successfully registered!");
    }

    /**
     * Validates the collaborator's name input.
     *
     * @param scanner the scanner to read user input
     * @return the validated name
     */
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

    /**
     * Validates the collaborator's birthdate input.
     *
     * @param scanner the scanner to read user input
     * @return the validated birthdate
     */
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

    /**
     * Validates the collaborator's admission date input.
     *
     * @param scanner    the scanner to read user input
     * @param birthDate the birthdate to compare with
     * @return the validated admission date
     */
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

    /**
     * Validates the collaborator's address input.
     *
     * @param scanner the scanner to read user input
     * @return the validated address
     */
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

    /**
     * Validates the collaborator's mobile number input.
     *
     * @param scanner the scanner to read user input
     * @return the validated mobile number
     */
    private String validateMobileNumber(Scanner scanner){
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
        return mobile;
    }

    /**
     * Validates the collaborator's email input.
     *
     * @param scanner the scanner to read user input
     * @return the validated email address
     */
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

    /**
     * Validates the collaborator's taxpayer number input.
     *
     * @param scanner the scanner to read user input
     * @return the validated taxpayer number
     */
    private String validateTaxpayerNumber(Scanner scanner){
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

        return taxpayerNumber;
    }

    /**
     * Prompts the user to choose an ID document type.
     *
     * @param scanner the scanner to read user input
     * @return the chosen ID document type
     */
    private Collaborator.IdDocType chooseIdDocumentType(Scanner scanner) {

        for (Collaborator.IdDocType type : Collaborator.IdDocType.values()) {
            System.out.println(type.ordinal() + 1 + " - " + type.getDisplayName());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        return Collaborator.IdDocType.values()[choice - 1];
    }

    /**
     * Validates the collaborator's ID document number input.
     *
     * @param scanner the scanner to read user input
     * @param idDocType the ID document type
     * @return the validated ID document number
     */
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

    private String validatePwd(Scanner scanner){
        String pwd = "";
        boolean valid = false;

        while (!valid) {

            try {
                pwd = scanner.nextLine();
                valid = ValidatorUtils.isValidPwd(pwd);

            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter your password, which must contain seven alphanumeric characters, including three capital letters and two digits: ");
            }
        }
        return pwd;
    }

    /**
     * Prompts the user to enter a job name and validates it against the job repository.
     *
     * @param scanner the scanner to read user input
     * @return the validated job name
     */
    private String assignJob(Scanner scanner){
        List<Job> jobsList = controller.getJobs();

        int index;
        String jobName = null;
        boolean valid = false;

        showJobs(jobsList);

        while (!valid) {
            try {
                index = scanner.nextInt();
                if (index > 0 && index <= jobsList.size()) {
                    jobName = jobsList.get(index - 1).getName();
                    valid = true;
                } else {
                    throw new InvalidCollaboratorDataException("Job not found please enter the integer corresponding to " +
                            "the Job you want to register with the collaborator.");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.print("Enter the integer corresponding to the desired job: ");
            }


        }
        scanner.nextLine();
        return jobName;
    }

    /**
     * Displays a list of jobs to the console.
     *
     * @param jobsList the list of jobs to be displayed
     */
    private void showJobs(List<Job> jobsList){

        for (int i = 0; i < jobsList.size(); i++) {

            System.out.println(i + 1 + " - " + jobsList.get(i));

        }
    }



}
