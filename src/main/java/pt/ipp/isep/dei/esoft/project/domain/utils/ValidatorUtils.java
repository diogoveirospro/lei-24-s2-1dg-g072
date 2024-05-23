package pt.ipp.isep.dei.esoft.project.domain.utils;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidVehicleDataException;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;

/**
 * Utility class for validating collaborator data.
 * Contains static methods for validating various attributes of a collaborator.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class ValidatorUtils {

    /**
     * Validates a collaborator's name.
     *
     * @param name the name to validate
     * @return true if the name is valid
     * @throws InvalidCollaboratorDataException if the name is null, blank, or contains invalid characters
     */
    public static boolean isValidName(String name) throws InvalidCollaboratorDataException {
        if (name == null || name.isBlank()) {
            throw new InvalidCollaboratorDataException("Invalid input. The name cannot be empty or blank.");
        } else if (!name.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new InvalidCollaboratorDataException("Invalid input. The name must not contain numbers or special characters, and must be composed of letters and spaces only.");
        } else {
            return true;
        }
    }

    /**
     * Validates a collaborator's birth date.
     *
     * @param dateStr the birthdate string in YYYY-MM-DD format
     * @return the validated Date object
     * @throws InvalidCollaboratorDataException if the date format is invalid or the collaborator is under 18 years old
     */
    public static Date validateBirthDate(String dateStr) throws InvalidCollaboratorDataException {
        String[] parts = dateStr.split("-");

        if (parts.length != 3) {
            throw new InvalidCollaboratorDataException("Invalid format. Please enter the date in YYYY-MM-DD format.");
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Date birthDate = new Date(year, month, day);
        Date currentDate = Date.currentDate();

        if (currentDate.difference(birthDate) < 18 * 365.25) {
            throw new InvalidCollaboratorDataException("Invalid date. Enter again!");

        }

        return birthDate;
    }

    /**
     * Validates a collaborator's admission date.
     *
     * @param dateStr the admission date string in YYYY-MM-DD format
     * @param birthDate the birthdate of the collaborator
     * @return the validated Date object
     * @throws InvalidCollaboratorDataException if the date format is invalid or the admission date is before the collaborator's 18th birthday
     */
    public static Date validateAdmissionDate(String dateStr, Date birthDate) throws InvalidCollaboratorDataException {
        String[] parts = dateStr.split("-");

        if (parts.length != 3) {
            throw new InvalidCollaboratorDataException("Invalid format. Please enter the date in YYYY-MM-DD format.");
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Date admissionDate = new Date(year, month, day);

        if (admissionDate.difference(birthDate) < 18 * 365.25 || admissionDate.isGreater(Date.currentDate())) {
            throw new InvalidCollaboratorDataException("Invalid date. Enter again!");
        }

        return admissionDate;
    }

    /**
     * Validates a collaborator's address.
     *
     * @param address the address to validate
     * @return true if the address is valid
     * @throws InvalidCollaboratorDataException if the address is null, empty, or blank
     */
    public static boolean isValidAddress(String address) throws InvalidCollaboratorDataException {

        if (address == null || address.isEmpty() || address.isBlank()) {
            throw new InvalidCollaboratorDataException("The address cannot be empty or blank.");
        } else {
            return true;
        }
    }

    /**
     * Validates a collaborator's mobile number.
     *
     * @param mobile the mobile number to validate
     * @return true if the mobile number is valid
     * @throws InvalidCollaboratorDataException if the mobile number is not exactly 9 digits, does not start with a valid prefix, or contains non-numeric characters
     */
    public static boolean isValidMobileNumber(String mobile) throws InvalidCollaboratorDataException {

        if (mobile.length() != 9) {
            throw new InvalidCollaboratorDataException("Invalid input. The number must be exactly 9 digits.");

        } else if (!mobile.startsWith("91") && !mobile.startsWith("92") && !mobile.startsWith("93") && !mobile.startsWith("96")) {
            throw new InvalidCollaboratorDataException("Invalid input. The number must start with 91, 92, 93, or 96.");

        } else if (!mobile.matches("\\d+")) {
            throw new InvalidCollaboratorDataException("Invalid Input. The mobile number must be a number.");

        } else {
            return true;
        }
    }

    /**
     * Validates a collaborator's email address.
     *
     * @param email the email address to validate
     * @return true if the email address is valid
     * @throws InvalidCollaboratorDataException if the email is null, blank, or does not match the valid email format
     */
    public static boolean isValidEmail(String email) throws InvalidCollaboratorDataException {
        if (email == null || email.isBlank()) {
            throw new InvalidCollaboratorDataException("Invalid input. The e-mail address cannot be blank.");
        } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            throw new InvalidCollaboratorDataException("Invalid input. Enter a valid e-mail address.");
        } else {
            return true;
        }
    }

    /**
     * Validates a collaborator's taxpayer number.
     *
     * @param taxpayerNumber the taxpayer number to validate
     * @return true if the taxpayer number is valid
     * @throws InvalidCollaboratorDataException if the taxpayer number is not exactly 9 digits, contains non-numeric characters, starts with an invalid digit, or fails the check digit calculation
     */
    public static boolean isValidTaxpayerNumber(String taxpayerNumber) throws InvalidCollaboratorDataException {
        if (taxpayerNumber.length() != 9) {
            throw new InvalidCollaboratorDataException("Invalid input. The tax number must have exactly 9 digits.");
        } else if (!taxpayerNumber.matches("\\d+")) {
            throw new InvalidCollaboratorDataException("Invalid Input. The tax payer number must be a number.");
        } else if (taxpayerNumber.startsWith("0") || taxpayerNumber.startsWith("5") || taxpayerNumber.startsWith("6")
                || taxpayerNumber.startsWith("7") || taxpayerNumber.startsWith("8") || taxpayerNumber.startsWith("9")) {

            throw new InvalidCollaboratorDataException("Invalid input. The first digit of the tax payer number must be 1, 2, 3 or 4");
        } else if (!checkDigitCalculation(Integer.parseInt(taxpayerNumber))) {
            throw new InvalidCollaboratorDataException("Invalid input. Enter a valid tax payer number.");
        } else {
            return true;
        }
    }

    /**
     * Performs a check digit calculation on the taxpayer number to validate it.
     *
     * @param taxPayerNumber the taxpayer number to validate
     * @return true if the check digit calculation is valid
     */
    private static boolean checkDigitCalculation(int taxPayerNumber) {
        int digit;
        int sum = 0;

        int controlDigit = taxPayerNumber % 10;
        taxPayerNumber = taxPayerNumber / 10;

        for (int i = 2; i <= 9; i++) {
            digit = taxPayerNumber % 10;
            taxPayerNumber = taxPayerNumber / 10;

            sum = sum + digit * i;

        }

        int restDivision = sum % 11;

        if (restDivision == 0 || restDivision == 1 && controlDigit == 0) {
            return true;
        } else return 11 - restDivision == controlDigit;
    }

    /**
     * Validates a collaborator's ID document number based on the document type.
     *
     * @param idDocType the type of ID document
     * @param idDocNumber the ID document number to validate
     * @return true if the ID document number is valid
     * @throws InvalidCollaboratorDataException if the document number format is invalid based on the document type
     */
    public static boolean isValidDocumentNumber(Collaborator.IdDocType idDocType, String idDocNumber) throws InvalidCollaboratorDataException {

        if (idDocType == Collaborator.IdDocType.CC && idDocNumber.length() != 12 && !idDocNumber.matches("[0-9]{9}[A-Z]{2}[0-9]")) {

            throw new InvalidCollaboratorDataException("Invalid input. The CC number must be 12 characters " +
                    "long and must be of the form 012345678ZZ9.");

        } else if (idDocType == Collaborator.IdDocType.BI && idDocNumber.length() != 9 && idDocNumber.matches("[0-9]{9}")) {
            throw new InvalidCollaboratorDataException("Invalid input. The BI must be 9 characters.");

        } else if (idDocType == Collaborator.IdDocType.NISS && idDocNumber.length() != 11 && idDocNumber.matches("[0-9]{11}")) {
            throw new InvalidCollaboratorDataException("Invalid input. The NISS must be 11 characters.");

        } else if (idDocType == Collaborator.IdDocType.PASSPORT && idDocNumber.length() != 7 && idDocNumber.matches("[A-Z][0-9]{6}")) {
            throw new InvalidCollaboratorDataException("Invalid input. The Passport must be 7 characters long and must be of the form A012345");

        } else {
            return true;
        }
    }

    public static boolean isValidPlate(String plate) throws InvalidVehicleDataException {
        if(plate.length() > 5) {
            String validation = String.valueOf(plate.charAt(2));
            String validation2 = String.valueOf(plate.charAt(5));
            if (!validation.equals("-") || !validation2.equals("-")) {
                throw new InvalidVehicleDataException("Invalid plate, please try again.");
            }
        }else {
            throw new InvalidVehicleDataException("Invalid plate, the plate must have eight characters");
        }
        return true;
    }

    public static boolean isValidBrand(String brand) throws InvalidVehicleDataException {
        for (int i = 0; i < brand.length(); i++) {
            String validation = String.valueOf(brand.charAt(i));
            if(validation.matches("[0-9]*") || validation.matches("\\W")){
                throw new InvalidVehicleDataException("Invalid input, please insert the data without special characters or numbers");
            }
        }
        return true;
    }

    public static boolean isValidNumber(String tare) throws InvalidVehicleDataException {
        for (int i = 0; i < tare.length(); i++) {
            String validation = String.valueOf(tare.charAt(i));
            if (!validation.matches("[0-9]*")){
                throw new InvalidVehicleDataException("Invalid value, please insert only numbers");
            }
        }
        return true;
    }

    public static boolean isValidDate(String date) throws InvalidVehicleDataException {
        String[] parts = date.split("-");

        if (parts.length != 3) {
            throw new InvalidVehicleDataException("Invalid format. Please enter the date in YYYY-MM-DD format.");
        }

        for (int i = 0; i < date.length(); i++) {
            String validation = String.valueOf(date.charAt(i));
            if (validation.matches("[A-Z]*")){
                throw new InvalidVehicleDataException("Invalid format. Please enter the date in YYYY-MM-DD format.");
            }
        }

        return true;
    }
}



