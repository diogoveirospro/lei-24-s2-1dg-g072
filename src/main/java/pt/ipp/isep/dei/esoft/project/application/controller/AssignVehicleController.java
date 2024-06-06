
package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.VehicleServicesExternalModule;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * The AssignVehicleController class represents the controller for assigning vehicles to agenda entries.
 */
public class AssignVehicleController {
    private final Agenda agenda;
    private final VehicleRepository vehicleRepository;

    /**
     * Constructs a new AssignVehicleController with the necessary repositories.
     */
    public AssignVehicleController() {
        this.agenda = Repositories.getInstance().getAgenda();
        this.vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Retrieves the list of agenda entries.
     *
     * @return the list of agenda entries
     */
    public List<AgendaEntry> getAgendaEntries() {
        return this.agenda.getEntryList();
    }

    /**
     * Retrieves the agenda entry with the specified name.
     *
     * @param name the name of the agenda entry
     * @return the agenda entry with the specified name
     */
    public AgendaEntry getAgendaEntry(String name) {
        return this.agenda.getAgendaEntry(name);
    }

    /**
     * Retrieves the list of valid vehicles for assignment to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry to be assigned to
     * @return the list of valid vehicles
     */
    public List<Vehicle> getValidVehicles(AgendaEntry agendaEntry) {
        return this.vehicleRepository.getValidVehicles(agendaEntry);
    }

    /**
     * Assigns the specified vehicle to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry to be assigned to
     * @param vehicle     the vehicle to be assigned
     * @return true if the vehicle is successfully assigned to the agenda entry, otherwise false
     */
    public boolean assignVehicleToAgendaEntry(AgendaEntry agendaEntry, Vehicle vehicle) {
        return agenda.assignVehicleToAgendaEntry(agendaEntry, vehicle);
    }

    /**
     * Retrieves the list of available vehicle services.
     *
     * @return the list of available vehicle services
     * @throws IOException if an I/O error occurs while reading the config.properties file
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
     * Sends a service request to the specified vehicle service provider for the vehicle assigned to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry with the vehicle to be serviced
     * @param service     the vehicle service provider to be contacted
     * @return true if the service request is successfully sent, otherwise false
     */
    public boolean sendServiceRequest(AgendaEntry agendaEntry, String service) {
        Vehicle assignedVehicle = agendaEntry.getAssignedVehicle();
        try {
            Class<?> vehicleServiceClass = Class.forName("pt.ipp.isep.dei.esoft.project.domain.externalModules." + service + "VehicleService");
            VehicleServicesExternalModule vehicleModule = (VehicleServicesExternalModule) vehicleServiceClass.getDeclaredConstructor().newInstance();
            vehicleModule.requestService(assignedVehicle);
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {

            e.getTargetException().printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the list of agenda entries without assigned vehicles.
     *
     * @return the list of agenda entries without assigned vehicles
     */
    public List<AgendaEntry> getAgendaEntriesWithoutVehicle() {
        return this.agenda.getAgendaEntriesWithoutVehicle();
    }

    /**
     * Assigns the specified vehicle to the specified agenda entry.
     *
     * @param agendaEntry the agenda entry to be assigned to
     * @param vehicle     the vehicle to be assigned
     * @return true if the vehicle is successfully assigned to the agenda entry, otherwise false
     */
    public boolean assignVehicle(AgendaEntry agendaEntry, Vehicle vehicle) {
        return agenda.assignVehicleToAgendaEntry(agendaEntry, vehicle);
    }
}

