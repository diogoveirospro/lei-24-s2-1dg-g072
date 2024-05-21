package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidVehicleDataException;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;

import java.util.Optional;
import java.util.Scanner;

/**
 *
 *
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
     * Vehicle's acquisition date as a String.
     */
    private String acqDate;

    /**
     * Vehicle's registration date as a String.
     */
    private String regDate;

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
     * Array containing year month and day of acquisition date.
     */
    private String[] acqDateSeparated;

    /**
     * Array containing year month and day of registration date.
     */
    private String[] regDateSeparated;

    /**
     * Vehicle's registration date.
     */
    private Date registrationDate;

    /**
     * Vehicle's acquisition date.
     */
    private Date acquisitionDate;

    /**
     * Register vehicle UI builder.
     */
    public RegisterVehicleUI() {
        this.controller = new RegisterVehicleController();
    }

    /**
     * Lets the UI get the register vehicle controller.
     * @return RegisterVehicleController.
     */
    private RegisterVehicleController getController(){
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
        System.out.println("Enter model");
        model = validateModel(sc);

        //Request vehicle type from console
        type = requestType(sc);

        //Request vehicle tare from console
        tare = requestTare(sc);

        //Request vehicle gross weight from console
        grossWeight = requestGrossWeight(sc);

        //Request vehicle current Kms from console
        currentKms = requestCurrentKms(sc);
        sc.nextLine();

        //Request vehicle registration date from console
        regDate = requestRegistrationDate(sc);

        //Request vehicle acquisition date from console
        acqDate = requestAcquisitionDate(sc);

        //Request vehicle service frequency from console
        serviceFrequency = requestServiceFrequency(sc);

        //Request vehicle Km at last maintenance from console
        kmAtLastMaintenance = requestKmAtLastMaintenance(sc);

        //Separate dates soo they can be used in class Date
        acqDateSeparated = separateDate(acqDate);
        regDateSeparated = separateDate(regDate);

        //Creat instances of Date
        acquisitionDate = new Date(Integer.parseInt(acqDateSeparated[0]), Integer.parseInt(acqDateSeparated[1]), Integer.parseInt(acqDateSeparated[2]));
        registrationDate = new Date(Integer.parseInt(regDateSeparated[0]), Integer.parseInt(regDateSeparated[1]), Integer.parseInt(regDateSeparated[2]));
    }

    /**
     * Validate model
     * @param sc Scanner
     * @return model
     */
    private String validateModel(Scanner sc) {
        String model = "";
        boolean valid = false;

        while (!valid){

            try {
                model = sc.nextLine();
                valid = ValidatorUtils.isValidBrand(model);

            }catch (InvalidVehicleDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter model: ");
            }
        }
        return model;
    }

    /**
     * Validate brand
     * @param sc Scanner
     * @return brand
     */
    private String validateBrand(Scanner sc) {
        String brand = "";
        boolean valid = false;

        while (!valid){

            try {
                brand = sc.nextLine();
                valid = ValidatorUtils.isValidBrand(brand);

            }catch (InvalidVehicleDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter brand: ");
            }
        }
        return brand;
    }

    /**
     * Validate plate number
     * @param sc Scanner
     * @return plate number
     */
    private String validatePlateNumber(Scanner sc) {
        String plate = "";
        boolean valid = false;
        while (!valid){

            try {
                plate = sc.nextLine();
                valid = ValidatorUtils.isValidPlate(plate);

            }catch (InvalidVehicleDataException e){
                System.out.println(e.getMessage());
                System.out.print("Enter plate: ");
            }
        }
        return plate;
    }

    /**
     * Request brand.
     * @param sc scanner.
     * @return brand.
     */
    private String requestBrand(Scanner sc) {
        System.out.println("Insert brand: ");
        return sc.nextLine();
    }

    /**
     * Request model.
     * @param sc scanner.
     * @return model.
     */
    private String requestModel(Scanner sc) {
        System.out.println("Insert model: ");
        return sc.nextLine();
    }

    /**
     * Request type.
     * @param sc scanner.
     * @return type.
     */
    private String requestType(Scanner sc) {
        System.out.println("Insert type: ");
        return sc.nextLine();
    }

    /**
     * Request tare.
     * @param sc scanner.
     * @return tare.
     */
    private Double requestTare(Scanner sc) {
        System.out.println("Insert tare: ");
        return sc.nextDouble();
    }

    /**
     * Request gross weight.
     * @param sc scanner.
     * @return gross weight.
     */
    private Double requestGrossWeight(Scanner sc) {
        System.out.println("Insert gross weight: ");
        return sc.nextDouble();
    }

    /**
     * Request current Kms.
     * @param sc scanner.
     * @return current Kms.
     */
    private Double requestCurrentKms(Scanner sc) {
        System.out.println("Insert current Kms: ");
        return sc.nextDouble();
    }

    /**
     * Request registration date.
     * @param sc scanner.
     * @return registration date.
     */
    private String requestRegistrationDate(Scanner sc) {
        System.out.println("Insert registration date (YYYY-MM-DD): ");
        return sc.nextLine();
    }

    /**
     * Request acquisition date.
     * @param sc scanner.
     * @return acquisition date.
     */
    private String requestAcquisitionDate(Scanner sc) {
        System.out.println("Insert acquisition date (YYYY-MM-DD): ");
        return sc.nextLine();
    }

    /**
     * Request service frequency.
     * @param sc scanner.
     * @return service frequency.
     */
    private Double requestServiceFrequency(Scanner sc) {
        System.out.println("Insert service frequency: ");
        return sc.nextDouble();
    }

    /**
     * Request Km at last maintenance.
     * @param sc scanner.
     * @return Km at last maintenance.
     */
    private Double requestKmAtLastMaintenance(Scanner sc) {
        System.out.println("Insert Km at last maintenance: ");
        return sc.nextDouble();
    }

    /**
     * Register the vehicle.
     */
    private void submitData() {
        Optional<Vehicle> vehicle = getController().registerVehicle(plate, model, type, brand, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);

        if(vehicle.isPresent()){
            System.out.println("\nVehicle successfully registered");

        }else
            System.out.println("\nVehicle not registered");
    }

    /**
     * Separate the date as String in an array.
     * @param date date as String
     * @return array containing year month and day of date.
     */
    private String[] separateDate(String date){
        return date.split("-");
    }
}
