package pt.ipp.isep.dei.esoft.project.domain.utils;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.exceptions.InvalidCollaboratorDataException;

public class ValidatorUtils {

    public static boolean isValidName(String name) throws InvalidCollaboratorDataException {
        if (name == null || name.isBlank()) {
            throw new InvalidCollaboratorDataException("Invalid input. The name cannot be empty or blank.");
        } else if (!name.matches("[a-zA-Z ]+")) {
            throw new InvalidCollaboratorDataException("Invalid input. The name must not contain numbers or special characters, and must be composed of letters and spaces only.");
        } else {
            return true;
        }
    }

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

    public static Date validateAdmissionDate(String dateStr, Date birthDate) throws InvalidCollaboratorDataException {
        String[] parts = dateStr.split("-");

        if (parts.length != 3) {
            throw new InvalidCollaboratorDataException("Invalid format. Please enter the date in YYYY-MM-DD format.");
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Date admissionDate = new Date(year, month, day);

        if (admissionDate.difference(birthDate) < 18 * 365.25 && admissionDate.isGreater(Date.currentDate())) {
            throw new InvalidCollaboratorDataException("Invalid date. Enter again!");
        }

        return admissionDate;
    }

    public static boolean isValidAddress(String address) throws InvalidCollaboratorDataException {

        if (address == null || address.isEmpty() || address.isBlank()) {
            throw new InvalidCollaboratorDataException("The address cannot be empty or blank.");
        } else {
            return true;
        }
    }

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

    public static boolean isValidEmail(String email) throws InvalidCollaboratorDataException {
        if (email == null || email.isBlank()) {
            throw new InvalidCollaboratorDataException("Invalid input. The e-mail address cannot be blank.");
        } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            throw new InvalidCollaboratorDataException("Invalid input. Enter a valid e-mail address.");
        } else {
            return true;
        }
    }

    public static boolean isValidTaxpayerNumber(String taxpayerNumber) throws InvalidCollaboratorDataException {
        if (taxpayerNumber.length() != 9) {
            throw new InvalidCollaboratorDataException("Invalid input. The tax number must have exactly 9 digits.");
        } else if (!taxpayerNumber.matches("\\d+")) {
            throw new InvalidCollaboratorDataException("Invalid Input. The tax payer number must be a number.");
        } else if (taxpayerNumber.startsWith("0") || taxpayerNumber.startsWith("4")) {
            throw new InvalidCollaboratorDataException("Invalid input. The first digit of the tax payer number must be 1, 2, 3, 5, 6, 7, 8 or 9.");
        } else if (!checkDigitCalculation(Integer.parseInt(taxpayerNumber))) {
            throw new InvalidCollaboratorDataException("Invalid input. Enter a valid tax payer number.");
        } else {
            return true;
        }
    }

    private static boolean checkDigitCalculation(int taxPayerNumber) {
        int digit;
        int soma = 0;

        int controlDigit = taxPayerNumber % 10;
        taxPayerNumber = taxPayerNumber / 10;

        for (int i = 2; i <= 9; i++) {
            digit = taxPayerNumber % 10;
            taxPayerNumber = taxPayerNumber / 10;

            soma = soma + digit * i;

        }

        int restDivision = soma % 11;

        if (restDivision == 0 || restDivision == 1 && controlDigit == 0) {
            return true;
        } else return 11 - restDivision == controlDigit;
    }

    public static boolean isValidDocumentNumber(Collaborator.IdDocType idDocType, String idDocNumber) throws InvalidCollaboratorDataException {

        if (idDocType == Collaborator.IdDocType.CC && idDocNumber.length() != 12 && !idDocNumber.matches("[0-9]{9}[A-Z]{2}[0-9]")) {

            throw new InvalidCollaboratorDataException("Invalid input. The CC number must be 12 characters " +
                    "long and must be of the form 012345678ZZ9.");

        } else if (idDocType == Collaborator.IdDocType.BI && idDocNumber.length() != 9 && idDocNumber.matches("[0-9]{9}")) {
            throw new InvalidCollaboratorDataException("Invalid input. The BI must be 9 characters.");

        } else if (idDocType == Collaborator.IdDocType.NISS && idDocNumber.length() != 11 && idDocNumber.matches("[0-9]{11}")) {
            throw new InvalidCollaboratorDataException("Invalid input. The NISS must be 11 characters.");

        } else if (idDocType == Collaborator.IdDocType.PASSPORT && idDocNumber.length() != 8 && idDocNumber.matches("[A-Z]{2}[0-9]{6}")) {
            throw new InvalidCollaboratorDataException("Invalid input. The Passport must be 8 characters long and must be of the form AB012345");

        } else {
            return true;
        }
    }

}



