package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidVehicleDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;

import java.util.Optional;
import java.util.Scanner;

/**
 * @author Group 072 - Byte Masters - ISEP
 */
public class RegisterVehicleUI implements Runnable {

    /**
     * Register vehicle controller.
     */
    private final RegisterVehicleController controller;

    /**
     * Vehicle plate number.
     */
    private String plate;

    /**
     * Vehicle model.
     */
    private String model;

    /**
     * Vehicle brand.
     */
    private String brand;

    /**
     * vehicle type.
     */
    private String type;

    /**
     * Vehicle tare.
     */
    private Double tare;

    /**
     * Vehicle's current kms.
     */
    private Double currentKms;

    /**
     * Vehicle's acquisition date.
     */
    private Date acqDate;

    /**
     * Vehicle's registration date.
     */
    private Date regDate;

    /**
     * Vehicle's kms at last maintenance.
     */
    private Double kmAtLastMaintenance;

    /**
     * Vehicle's gross weight.
     */
    private Double grossWeight;

    /**
     * Vehicle service frequency.
     */
    private Double serviceFrequency;

    /**
     * Register vehicle UI builder.
     */
    public RegisterVehicleUI() {
        this.controller = new RegisterVehicleController();
    }

    /**
     * Lets the UI get the register vehicle controller.
     *
     * @return RegisterVehicleController.
     */
    private RegisterVehicleController getController() {
        return controller;
    }

    /**
     * Rewriting the run method so that it is possible to run the UI that allows the VFM to register a vehicle.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register Vehicle ------------------------");

        requestData();

        submitData();
    }

    /**
     * Request the data for the vehicle.
     */
    private void requestData() {
        Scanner sc = new Scanner(System.in);

        //Request vehicle plate from console
        System.out.println("Enter plate number: ");
        plate = validatePlateNumber(sc);

        //Request vehicle brand from console
        System.out.println("Enter brand: ");
        brand = validateBrand(sc);

        //Request vehicle model from console
        System.out.println("Enter model: ");
        model = validateModel(sc);

        //Request vehicle type from console
        System.out.println("Enter type:");
        type = validateType(sc);

        //Request vehicle tare from console
        System.out.println("Enter tare: ");
        tare = validateTare(sc);

        //Request vehicle gross weight from console
        System.out.println("Enter gross weight: ");
        grossWeight = validateGrossWeight(sc);

        //Request vehicle current Kms from console
        System.out.println("Enter current Kms");
        currentKms = validateKms(sc);

        //Request vehicle registration date from console
        System.out.println("Enter registration date (YYYY-MM-DD): ");
        regDate = validateDate(sc);

        //Request vehicle acquisition date from console
        System.out.println("Enter acquisition date (YYYY-MM-DD): ");
        acqDate = validateDate(sc);

        //Request vehicle service frequency from console
        System.out.println("Enter service frequency: ");
        serviceFrequency = validateKms(sc);

        //Request vehicle Km at last maintenance from console
        System.out.println("Enter Km at last maintenance: ");
        kmAtLastMaintenance = validateKms(sc);

    }

    /**
     * Validate dates
     *
     * @param sc Scanner
     * @return date
     */
    private Date validateDate(Scanner sc) {
        String date = "";
        boolean valid = false;

        while (!valid) {

            try {
                date = sc.nextLine();
                valid = ValidatorUtils.isValidDate(date);
            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter date: ");
            }
        }
        String[] parts = date.split("-");
        return new Date(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    /**
     * Validate Kms
     *
     * @param sc Scanner
     * @return kms
     */
    private Double validateKms(Scanner sc) {
        String kms = "";
        boolean valid = false;

        while (!valid) {

            try {
                kms = sc.nextLine();
                valid = ValidatorUtils.isValidNumber(kms);
            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter Kms: ");
            }
        }
        return Double.parseDouble(kms);
    }

    /**
     * Validate gross weight
     *
     * @param sc Scanner
     * @return gross weight
     */
    private Double validateGrossWeight(Scanner sc) {
        String grossWeight = "";
        boolean valid = false;

        while (!valid) {

            try {
                grossWeight = sc.nextLine();
                valid = ValidatorUtils.isValidNumber(grossWeight);
            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter gross weight: ");
            }
        }
        return Double.parseDouble(grossWeight);
    }

    /**
     * Validate tare
     *
     * @param sc Scanner
     * @return tare
     */
    private Double validateTare(Scanner sc) {
        String tare = "";
        boolean valid = false;

        while (!valid) {

            try {
                tare = sc.nextLine();
                valid = ValidatorUtils.isValidNumber(tare);
            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter tare:");
            }
        }
        return Double.parseDouble(tare);
    }

    /**
     * Validate type
     *
     * @param sc Scanner
     * @return type
     */
    private String validateType(Scanner sc) {
        String type = "";
        boolean valid = false;

        while (!valid) {

            try {
                type = sc.nextLine();
                valid = ValidatorUtils.isValidBrand(type);

            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter type: ");
            }
        }
        return type;
    }

    /**
     * Validate model
     *
     * @param sc Scanner
     * @return model
     */
    private String validateModel(Scanner sc) {
        String model = "";
        boolean valid = false;

        while (!valid) {

            try {
                model = sc.nextLine();
                valid = ValidatorUtils.isValidBrand(model);

            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter model: ");
            }
        }
        return model;
    }

    /**
     * Validate brand
     *
     * @param sc Scanner
     * @return brand
     */
    private String validateBrand(Scanner sc) {
        String brand = "";
        boolean valid = false;

        while (!valid) {

            try {
                brand = sc.nextLine();
                valid = ValidatorUtils.isValidBrand(brand);

            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter brand: ");
            }
        }
        return brand;
    }

    /**
     * Validate plate number
     *
     * @param sc Scanner
     * @return plate number
     */
    private String validatePlateNumber(Scanner sc) {
        String plate = "";
        boolean valid = false;
        while (!valid) {
            Date date = new Date(2000,11,12);
            try {
                plate = sc.nextLine();
                valid = ValidatorUtils.isValidPlate(plate,date);

            } catch (InvalidVehicleDataException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter plate: ");
            }
        }
        return plate;
    }

    /**
     * Register the vehicle.
     */
    private void submitData() {
        Optional<Vehicle> vehicle = getController().registerVehicle(plate, model, type, brand, tare, grossWeight, currentKms, regDate, acqDate, serviceFrequency, kmAtLastMaintenance);

        if (vehicle.isPresent()) {
            System.out.println("\nVehicle successfully registered");

        } else
            System.out.println("\nVehicle not registered");
    }
}