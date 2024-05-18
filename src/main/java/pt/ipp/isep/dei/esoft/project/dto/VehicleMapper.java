package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleMapper {
    /**
     * List of vehicles converted to data
     *
     */
    private List<VehicleDto> vehicleListDto;

    public VehicleMapper() {
        vehicleListDto = new ArrayList<>();
    }

    public List<VehicleDto> getVehicleListDto() {
        return vehicleListDto;
    }

    public void setVehicleListDto(List<VehicleDto> vehicleListDto) {
        this.vehicleListDto = vehicleListDto;
    }

    public void addVehicle(VehicleDto vehicle){
        vehicleListDto.add(vehicle);
    }

    public List<VehicleDto> toDTO(List<Vehicle> vehicleList){
        for (Vehicle vehicle : vehicleList){
            VehicleDto vehicleDto = new VehicleDto(
                vehicle.getPlateNumber(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getType(),
                vehicle.getTare(),
                vehicle.getGrossWeight(),
                vehicle.getCurrentKms(),
                vehicle.getRegistrationDate(),
                vehicle.getAcquisitionDate(),
                vehicle.getServiceFrequency(),
                vehicle.getKmAtLastMaintenance()
            );
            addVehicle(vehicleDto);
        }
        return vehicleListDto;
    }
}
