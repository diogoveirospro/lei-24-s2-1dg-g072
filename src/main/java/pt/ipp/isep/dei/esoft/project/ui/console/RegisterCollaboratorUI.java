package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
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

        Date birthDate = validateBirthDate(scanner);

        Date admissionDate = validateAdmissionDate(scanner, birthDate);

        System.out.print("Enter address: ");
        String address = validateAddress(scanner);

        System.out.print("Enter contact number: ");
        int mobile = validateMobileNumber(scanner);

        System.out.print("Enter email address: ");
        String email = validateEmail(scanner);

        System.out.print("Enter taxpayer number: ");
        int taxpayerNumber = validateTaxpayerNumber(scanner);

        System.out.print("Enter ID document type: ");
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

                if (name == null || name.isEmpty() || name.isBlank()){
                    throw new InvalidCollaboratorDataException("Invalid input. The name cannot be empty or blank.");

                } else if (name.matches("\\d+") || name.matches(".*[^a-zA-Z0-9 ].*")){
                    throw new InvalidCollaboratorDataException("Invalid input. The name must not contain numbers or special characters.");
                }

                valid = true;

            }catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.println("Enter name: ");
            }
        }
        return name;
    }

    private static Date validateBirthDate(Scanner scanner) {
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

    private static Date validateAdmissionDate(Scanner scanner, Date birthDate) {
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

    private String validateAddress(Scanner scanner){
        String address = "";
        boolean valid = false;

        while (!valid) {

            try {
                address = scanner.nextLine();
                if (address == null || address.isEmpty() || address.isBlank()){
                    throw new InvalidCollaboratorDataException("The address cannot be empty or blank.");
                }
                valid = true;
            } catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.println("Enter address: ");
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
                if (mobile.length() != 9) {
                    throw new InvalidCollaboratorDataException("Invalid input. The number must be exactly 9 digits.");

                }else if (!mobile.startsWith("91") || !mobile.startsWith("92") || !mobile.startsWith("93") || !mobile.startsWith("96")){
                    throw new InvalidCollaboratorDataException("Invalid input. The number must start with 91, 92, 93, or 96.");

                } else if (!mobile.matches("\\d+")) {
                    throw new InvalidCollaboratorDataException("Invalid Input. The mobile number must be a number.");

                }
                valid = true;

            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.print("Enter contact number: ");
            }
        }
        scanner.nextLine();
        return Integer.parseInt(mobile);
    }

    private String validateEmail(Scanner scanner){
        String email = "";
        boolean valid = false;

        while (!valid) {

            try {
                email = scanner.nextLine();

                if (!email.contains("@")){
                    throw new InvalidCollaboratorDataException("Invalid input. Enter a valid e-mail address.");
                } else if (email.isBlank()) {
                    throw new InvalidCollaboratorDataException("Invalid input. The e-mail address cannot be blank.");
                }
                valid = true;
            } catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
                System.out.println("Enter email address: ");
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
                if (taxpayerNumber.length() != 9){
                    throw new InvalidCollaboratorDataException("Invalid input. The tax number must have exactly 9 digits.");
                } else if (!taxpayerNumber.matches("\\d+")) {
                    throw new InvalidCollaboratorDataException("Invalid Input. The tax payer number must be a number.");
                } else if (taxpayerNumber.startsWith("0") || taxpayerNumber.startsWith("4")) {
                    throw new InvalidCollaboratorDataException("Invalid input. The first digit of the tax payer number must be 1, 2, 3, 5, 6, 7, 8 or 9.");
                } else if (!checkDigitCalculation(Integer.parseInt(taxpayerNumber))) {
                    throw new InvalidCollaboratorDataException("Invalid input. Enter a valid tax payer number.");
                }

                valid = true;

            } catch (InvalidCollaboratorDataException e) {
                System.out.println(e.getMessage());
                System.out.print("Enter taxpayer number: ");
            }
        }

        scanner.nextLine();
        return Integer.parseInt(taxpayerNumber);
    }

    private boolean checkDigitCalculation(int taxPayerNumber){
        int digit;
        int soma = 0;

        int controlDigit = taxPayerNumber % 10;
        taxPayerNumber = taxPayerNumber / 10;

        for (int i = 9; i > 1 ; i--) {
            digit = taxPayerNumber % 10;
            taxPayerNumber = taxPayerNumber / 10;

            soma = soma + digit * i;

        }

        int restDivision = soma % 11;

        if (restDivision == 0 || restDivision == 1 && controlDigit == 0){
            return true;
        } else return 11 - restDivision == controlDigit;
    }

    private Collaborator.IdDocType chooseIdDocumentType(Scanner scanner) {

        for (Collaborator.IdDocType type : Collaborator.IdDocType.values()) {
            System.out.println(type.ordinal() + 1 + " - " + type.getDisplayName());
        }

        int choice = scanner.nextInt();
        return Collaborator.IdDocType.values()[choice - 1];
    }

    private String validateIdDocumentNumber(Scanner scanner, Collaborator.IdDocType idDocType){
        String idDocNumber = "";
        boolean valid = false;

        while (!valid){

            try {
                idDocNumber = scanner.nextLine();

                if (idDocType == Collaborator.IdDocType.CC && idDocNumber.length() != 12 && !idDocNumber.matches("[0-9]{9}[A-Z]{2}[0-9]")){
                    
                    throw new InvalidCollaboratorDataException("Invalid input. The CC number must be 12 characters " +
                                "long and must be of the form 012345678ZZ9.");
                    
                } else if (idDocType == Collaborator.IdDocType.BI && idDocNumber.length() != 9 && idDocNumber.matches("[0-9]{9}")) {
                    throw new InvalidCollaboratorDataException("Invalid input. The BI must be 9 characters.");
                }

                valid = true;
            } catch (InvalidCollaboratorDataException e){
                System.out.println(e.getMessage());
            }


        }

        scanner.nextLine();
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
            }

            System.out.print("Enter job name: ");

        }

        return jobName;
    }



}
