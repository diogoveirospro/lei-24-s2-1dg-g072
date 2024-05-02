package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.Optional;
import java.util.Scanner;

public class RegisterVehicleUI implements Runnable {

    private final RegisterVehicleController controller;

    private String plate;

    private String model;

    private String brand;

    private String type;

    private Double tare;

    private Double currentKms;

    private String acqDate;

    private String regDate;

    private Double kmAtLastMaintenance;

    private Double grossWeight;

    private Double serviceFrequency;

    private String[] acqDateSeparated;

    private String[] regDateSeparated;

    private Date registrationDate;

    private Date acquisitionDate;

    public RegisterVehicleUI() {
        this.controller = new RegisterVehicleController();
    }

    private RegisterVehicleController getController(){
        return controller;
    }
    @Override
    public void run() {
        System.out.println("\n\n--- Register Vehicle Maintenance ------------------------");

        requestData();

        submitData();
    }

    private void requestData() {
        Scanner sc = new Scanner(System.in);

        //Request vehicle plate from console
        plate = requestPlate(sc);

        //Request vehicle brand from console
        brand = requestBrand(sc);

        //Request vehicle model from console
        model = requestModel(sc);

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

    private String requestPlate(Scanner sc) {
        System.out.println("Insert plate number: ");
        return sc.nextLine();
    }
    private String requestBrand(Scanner sc) {
        System.out.println("Insert brand: ");
        return sc.nextLine();
    }
    private String requestModel(Scanner sc) {
        System.out.println("Insert model: ");
        return sc.nextLine();
    }
    private String requestType(Scanner sc) {
        System.out.println("Insert type: ");
        return sc.nextLine();
    }
    private Double requestTare(Scanner sc) {
        System.out.println("Insert tare: ");
        return sc.nextDouble();
    }
    private Double requestGrossWeight(Scanner sc) {
        System.out.println("Insert gross weight: ");
        return sc.nextDouble();
    }
    private Double requestCurrentKms(Scanner sc) {
        System.out.println("Insert current Kms: ");
        return sc.nextDouble();
    }
    private String requestRegistrationDate(Scanner sc) {
        System.out.println("Insert registration date (YYYY-MM-DD): ");
        return sc.nextLine();
    }

    private String requestAcquisitionDate(Scanner sc) {
        System.out.println("Insert acquisition date (YYYY-MM-DD): ");
        return sc.nextLine();
    }

    private Double requestServiceFrequency(Scanner sc) {
        System.out.println("Insert service frequency: ");
        return sc.nextDouble();
    }

    private Double requestKmAtLastMaintenance(Scanner sc) {
        System.out.println("Insert Km at last maintenance: ");
        return sc.nextDouble();
    }


    private void submitData() {
        Optional<Vehicle> vehicle = getController().registerVehicle(plate, model, type, brand, tare, grossWeight, currentKms, registrationDate, acquisitionDate, serviceFrequency, kmAtLastMaintenance);

        if(vehicle.isPresent()){
            System.out.println("\nVehicle successfully registered");

        }else
            System.out.println("\nVehicle not registered");
    }
    private String[] separateDate(String date){
        return date.split("-");
    }
}
