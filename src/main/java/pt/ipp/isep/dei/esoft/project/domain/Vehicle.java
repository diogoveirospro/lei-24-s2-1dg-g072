package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {

    /**
     * The vehicle plate number
     */
    private String plateNumber;

    /**
     * The vehicle brand
     */
    private String brand;

    /**
     * Vehicle model
     */
    private String model;

    /**
     * Vehicle type
     */
    private String type;

    /**
     * Vehicle tare
     */
    private Double tare;

    /**
     * Vehicle gross weight
     */
    private Double grossWeight;

    /**
     * Kms of the vehicle when registered
     */
    private Double currentKms;

    /**
     * Date when the vehicle is being registered
     */
    private String registrationDate;

    /**
     * Date when the vehicle was bought
     */
    private String acquisitionDate;

    /**
     * The frequency of the vehicle maintenance
     */
    private Double serviceFrequency;

    /**
     * The kms the vehicle had at the last maintenance
     */
    private Double kmAtLastMaintenance;

    /**
     * Creates an instance of vehicle
     * @param plateNumber Vehicle plate number
     * @param brand Vehicle brand
     * @param model Vehicle model
     * @param type Vehicle type
     * @param tare Vehicle tare
     * @param grossWeight Vehicle gross weight
     * @param currentKms Kms of the vehicle when registered
     * @param registrationDate Date when the vehicle was bought
     * @param acquisitionDate Date when the vehicle was bought
     * @param serviceFrequency The frequency of the vehicle maintenance
     * @param kmAtLastMaintenance The kms the vehicle had at the last maintenance
     */
    public Vehicle(String plateNumber, String brand, String model, String type, Double tare, Double grossWeight, Double currentKms, String registrationDate, String acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance){
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKms = currentKms;
        this.registrationDate = registrationDate;
        this.acquisitionDate = acquisitionDate;
        this.serviceFrequency = serviceFrequency;
        this.kmAtLastMaintenance = kmAtLastMaintenance;
    }

    public boolean validateVehicle(Vehicle vehicle){

    }




}
