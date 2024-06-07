package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class AssignVehicleController {
    private final Agenda agenda;
    private final VehicleRepository vehicleRepository;

    public AssignVehicleController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    public List<AgendaEntry> getAgendaEntries() {
        return this.agenda.getEntryList();
    }

    public List<Vehicle> getValidVehicles(AgendaEntry agendaEntry) {
        return this.vehicleRepository.getValidVehicles(agendaEntry);
    }

    public boolean assignVehicle(AgendaEntry agendaEntry, Vehicle vehicle) {
        return agenda.assignVehicleToAgendaEntry(agendaEntry, vehicle);
    }

    public List<String> showVehicleServices() throws IOException {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/config/config.properties")) {
            props.load(in);
        }
        String vehicleServices = props.getProperty("vehicle.services");
        return Arrays.asList(vehicleServices.split("\\s*,\\s*"));
    }

    public boolean sendServiceRequest(AgendaEntry agendaEntry, String service) {
        Vehicle assignedVehicle = agendaEntry.getAssignedVehicle();
        try {
            Class<?> vehicleServiceClass = Class.forName("pt.ipp.isep.dei.esoft.project.domain.externalModules." + service + "VehicleService");
            pt.ipp.isep.dei.esoft.project.domain.externalModules.VehicleServicesExternalModule vehicleModule =
                    (pt.ipp.isep.dei.esoft.project.domain.externalModules.VehicleServicesExternalModule)
                            vehicleServiceClass.getDeclaredConstructor().newInstance();
            vehicleModule.requestService(assignedVehicle);
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
            return false;
        }
    }

    public List<AgendaEntry> getAgendaEntriesWithoutVehicle() {
        return this.agenda.getAgendaEntriesWithoutVehicle();
    }
}