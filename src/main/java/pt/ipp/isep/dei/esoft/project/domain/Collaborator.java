package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collaborator with all its attributes.
 */
public class Collaborator {

    /**
     * Collaborator name.
     */
    private String name;

    /**
     * Collaborator birthdate.
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
    private int mobile;

    /**
     * Collaborator email.
     */
    private String email;

    /**
     * Collaborator taxpayer number.
     */
    private int taxpayerNumber;

    /**
     * Collaborator ID document type.
     */
    private String idDocType;

    /**
     * Collaborator ID document number.
     */
    private int idDocNumber;

    private List<Skill> skillSet;

    private final boolean hasTeam = false;

    /**
     * Constructor for Collaborator class.
     * @param name Collaborator name.
     * @param birthDate Collaborator birthdate.
     * @param admissionDate Collaborator admission date.
     * @param address Collaborator address.
     * @param mobile Collaborator mobile number.
     * @param email Collaborator email.
     * @param taxpayerNumber Collaborator taxpayer number.
     * @param idDocType Collaborator ID document type.
     * @param idDocNumber Collaborator ID document number.
     */
    public Collaborator(String name, Date birthDate, Date admissionDate, String address, int mobile, String email, int taxpayerNumber, String idDocType, int idDocNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;
        skillSet = new ArrayList<>();
    }

    /**
     * Get collaborator name.
     * @return Collaborator name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get collaborator birthdate.
     * @return Collaborator birthdate.
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
    public int getMobile() {
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
     *
     * @return Collaborator taxpayer number.
     */
    public int getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Get collaborator ID document type.
     *
     * @return Collaborator ID document type.
     */
    public String getIdDocType() {
        return idDocType;
    }

    /**
     * Get collaborator ID document number.
     *
     * @return Collaborator ID document number.
     */
    public int getIdDocNumber() {
        return idDocNumber;
    }

    /**
     * Get collaborator skill set.
     * @return All collaborator skills.
     */
    public List<Skill> getSkillSet() {
        return skillSet;
    }

    /**
     * Change collaborator address
     * @param address of the collaborator
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Change collaborator admission date on the enterprise
     * @param admissionDate of the collaborator
     */
    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * Change collaborator birthdate
     * @param birthDate of the collaborator
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Change collaborator email
     * @param email of the collaborator
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Change collaborator identification document number
     * @param idDocNumber of the collaborator
     */
    public void setIdDocNumber(int idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    /**
     * Change collaborator identification document type
     * @param idDocType of the collaborator
     */
    public void setIdDocType(String idDocType) {
        this.idDocType = idDocType;
    }

    /**
     * Change collaborator mobile phone
     * @param mobile number of the collaborator
     */
    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    /**
     * Change collaborator name
     * @param name of the collaborator
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Change collaborator skill set
     * @param skillSet of the collaborator
     */
    public void setSkillSet(List<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    /**
     * Change collaborator taxpayer number
     * @param taxpayerNumber of the collaborator
     */
    public void setTaxpayerNumber(int taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
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

    public boolean analyseCollaborator(Skill skill) {
        return skillSet.contains(skill);
    }

    public void setHasTeam(boolean b) {

    }

    public void setJob(Job job) {
    }

    public void assignSkill(Skill skill) {
    }

    public boolean hasTeam() {
        return hasTeam;
    }
}

