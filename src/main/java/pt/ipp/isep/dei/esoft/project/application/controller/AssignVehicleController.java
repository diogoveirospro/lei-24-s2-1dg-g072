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

/**
 * Controller for assigning vehicles to agenda entries.
 *
 * This controller handles the operations related to assigning vehicles to
 * agenda entries and interacting with external vehicle services.
 */

public class AssignVehicleController {
    private final Agenda agenda;
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs an instance of AssignVehicleController, initializing the agenda
     * and vehicle repository.
     */

    public AssignVehicleController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Retrieves a list of all agenda entries.
     *
     * @return a list of agenda entries
     */

    public List<AgendaEntry> getAgendaEntries() {
        return this.agenda.getEntryList();
    }

    /**
     * Retrieves a list of valid vehicles for a given agenda entry.
     *
     * @param agendaEntry the agenda entry to find valid vehicles for
     * @return a list of valid vehicles for the given agenda entry
     */

    public List<Vehicle> getValidVehicles(AgendaEntry agendaEntry) {
        return this.vehicleRepository.getValidVehicles(agendaEntry);
    }

    /**
     * Assigns a vehicle to an agenda entry.
     *
     * @param agendaEntry the agenda entry to assign the vehicle to
     * @param vehicle the vehicle to be assigned
     * @return true if the vehicle was successfully assigned, false otherwise
     */

    public boolean assignVehicle(AgendaEntry agendaEntry, Vehicle vehicle) {
        return agenda.assignVehicleToAgendaEntry(agendaEntry, vehicle);
    }

    /**
     * Reads the available vehicle services from a properties file.
     *
     * @return a list of vehicle services
     * @throws IOException if an error occurs while reading the properties file
     */

    public List<String> showVehicleServices() throws IOException {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/config/config.properties")) {
            props.load(in);
        }
        String vehicleServices = props.getProperty("vehicle.services");
        return Arrays.asList(vehicleServices.split("\\s*,\\s*"));
    }

    /**
     * Sends a service request for a vehicle associated with a given agenda entry.
     *
     * @param agendaEntry the agenda entry for which to send the service request
     * @param service the service to request
     * @return true if the service request was successfully sent, false otherwise
     */

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

    /**
     * Retrieves a list of agenda entries that do not have an assigned vehicle.
     *
     * @return a list of agenda entries without an assigned vehicle
     */

    public List<AgendaEntry> getAgendaEntriesWithoutVehicle() {
        return this.agenda.getAgendaEntriesWithoutVehicle();
    }
}
