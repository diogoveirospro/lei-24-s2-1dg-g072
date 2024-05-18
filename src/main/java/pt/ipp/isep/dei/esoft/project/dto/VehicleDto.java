package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Date;

public class VehicleDto {
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
    private Date registrationDate;

    /**
     * Date when the vehicle was bought
     */
    private Date acquisitionDate;

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
     *
     * @param plateNumber         Vehicle plate number
     * @param brand               Vehicle brand
     * @param model               Vehicle model
     * @param type                Vehicle type
     * @param tare                Vehicle tare
     * @param grossWeight         Vehicle gross weight
     * @param currentKms          Kms of the vehicle when registered
     * @param registrationDate    Date when the vehicle was bought
     * @param acquisitionDate     Date when the vehicle was bought
     * @param serviceFrequency    The frequency of the vehicle maintenance
     * @param kmAtLastMaintenance The kms the vehicle had at the last maintenance
     */
    public VehicleDto(String plateNumber, String brand, String model, String type, Double tare, Double grossWeight, Double currentKms, Date registrationDate, Date acquisitionDate, Double serviceFrequency, Double kmAtLastMaintenance) {
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

    /**
     * Gets plate number of vehicle
     *
     * @return plate number
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * Gets current kms of vehicle
     *
     * @return current kms
     */
    public Double getCurrentKms() {
        return currentKms;
    }

    /**
     * Gets gross weight of vehicle
     *
     * @return gross weight
     */
    public Double getGrossWeight() {
        return grossWeight;
    }

    /**
     * Gets Km at last maintenance of vehicle
     *
     * @return km at last maintenance
     */
    public Double getKmAtLastMaintenance() {
        return kmAtLastMaintenance;
    }

    /**
     * Gets service frequency of vehicle
     *
     * @return service frequency
     */
    public Double getServiceFrequency() {
        return serviceFrequency;
    }

    /**
     * Gets tare of vehicle
     *
     * @return tare
     */
    public Double getTare() {
        return tare;
    }

    /**
     * Gets acquisition date of vehicle
     *
     * @return acquisitions date
     */
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Gets brand of vehicle
     *
     * @return brand of vehicle
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets model of vehicle
     *
     * @return model of vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Get registration date of vehicle
     *
     * @return registration date of vehicle
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Gets type of vehicle
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Set acquisition date of vehicle
     *
     * @param acquisitionDate
     */
    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Set brand of vehicle
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Set current kms of vehicle
     *
     * @param currentKms
     */
    public void setCurrentKms(Double currentKms) {
        this.currentKms = currentKms;
    }

    /**
     * Set km at last maintenance of vehicle
     *
     * @param kmAtLastMaintenance
     */
    public void setKmAtLastMaintenance(Double kmAtLastMaintenance) {
        this.kmAtLastMaintenance = kmAtLastMaintenance;
    }

    /**
     * Set gross weight of vehicle
     *
     * @param grossWeight
     */
    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * Set model of vehicle
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Set plate number of vehicle
     *
     * @param plateNumber
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * Set registration date of vehicle
     *
     * @param registrationDate
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Set service frequency of vehicle
     *
     * @param serviceFrequency
     */
    public void setServiceFrequency(Double serviceFrequency) {
        this.serviceFrequency = serviceFrequency;
    }

    /**
     * Set tare of vehicle
     *
     * @param tare
     */
    public void setTare(Double tare) {
        this.tare = tare;
    }

    /**
     * Set type of vehicle
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

}
