package pt.ipp.isep.dei.esoft.project.domain;

public class Maintenance {
    private double kmAtMaintenance;

    /**
     * A constructor of Maintenance that creates an object that initiates the instance kmAtMaintenance
     *
     * @param kmAtMaintenance km at the vehicle maintenance
     */
    public Maintenance(double kmAtMaintenance){
        this.kmAtMaintenance = kmAtMaintenance;
    }

    /**
     * Lets the user get the value of kmAtMaintenance
     *
     * @return kmAtMaintenance
     */
    public double getKmAtMaintenance() {
        return kmAtMaintenance;
    }

    /**
     * Lets the user change the value of kmAtMaintenance
     *
     * @param kmAtMaintenance km at the vehicle maintenance
     */

    public void setKmAtMaintenance(double kmAtMaintenance) {
        this.kmAtMaintenance = kmAtMaintenance;
    }
}
