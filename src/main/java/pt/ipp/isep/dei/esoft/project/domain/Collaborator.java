package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * Represents a collaborator with all its attributes.
 */
public class Collaborator {

    /**
     * Collaborator name.
     */
    private String name;

    /**
     * Collaborator birth date.
     */
    private Date birthDate;

    /**
     * Collaborator admission date.
     */
    private Date admissionDate;

    /**
     * Collaborator address.
     */
    private String address;

    /**
     * Collaborator mobile number.
     */
    private String mobile;

    /**
     * Collaborator email.
     */
    private String email;

    /**
     * Collaborator taxpayer number.
     */
    private String taxpayerNumber;

    /**
     * Collaborator ID document type.
     */
    private String idDocType;

    /**
     * Collaborator ID document number.
     */
    private String idDocNumber;

    /**
     * Constructor for Collaborator class.
     * @param name Collaborator name.
     * @param birthDate Collaborator birth date.
     * @param admissionDate Collaborator admission date.
     * @param address Collaborator address.
     * @param mobile Collaborator mobile number.
     * @param email Collaborator email.
     * @param taxpayerNumber Collaborator taxpayer number.
     * @param idDocType Collaborator ID document type.
     * @param idDocNumber Collaborator ID document number.
     */
    public Collaborator(String name, Date birthDate, Date admissionDate, String address, String mobile, String email, String taxpayerNumber, String idDocType, String idDocNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;
    }

    /**
     * Get collaborator name.
     * @return Collaborator name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get collaborator birth date.
     * @return Collaborator birth date.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Get collaborator admission date.
     * @return Collaborator admission date.
     */
    public Date getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Get collaborator address.
     * @return Collaborator address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get collaborator mobile number.
     * @return Collaborator mobile number.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Get collaborator email.
     * @return Collaborator email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get collaborator taxpayer number.
     * @return Collaborator taxpayer number.
     */
    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Get collaborator ID document type.
     * @return Collaborator ID document type.
     */
    public String getIdDocType() {
        return idDocType;
    }

    /**
     * Get collaborator ID document number.
     * @return Collaborator ID document number.
     */
    public String getIdDocNumber() {
        return idDocNumber;
    }

    /**
     * String representation of collaborator.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", admissionDate=" + admissionDate +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", taxpayerNumber='" + taxpayerNumber + '\'' +
                ", idDocType='" + idDocType + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                '}';
    }
}

