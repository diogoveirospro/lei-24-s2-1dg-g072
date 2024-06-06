package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.utils.ValidatorUtils;
import pt.ipp.isep.dei.esoft.project.Exceptions.InvalidCollaboratorDataException;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collaborator with all its attributes.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Collaborator implements Comparable<Collaborator>, Serializable {

    /**
     * Collaborator name.
     */
    private String name;

    /**
     * Collaborator birthdate.
     */
    private  Date birthDate;

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
     * All types of collaborator identification documents.
     */
    public enum IdDocType {
        CC("CC"),
        BI("BI"),
        NISS("Social Security Number"),
        PASSPORT("Passport");

        private final String displayName;

        IdDocType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

        public static IdDocType fromDisplayName(String displayName) {
            for (IdDocType type : IdDocType.values()) {
                if (type.displayName.equalsIgnoreCase(displayName)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid document type: " + displayName);
        }
    }

    /**
     * Collaborator ID document type.
     */
    private IdDocType idDocType;

    /**
     * Collaborator ID document number.
     */
    private String idDocNumber;

    /**
     * Collaborator's job.
     */
    private Job job;

    /**
     * Collaborator's skill set.
     */
    private List<Skill> skillSet;

    /**
     * All types of collaborator email.
     */
    public enum typeOfCollaboratorEmails {
        COLLABORATOR("@collaborator.com"),
        GSM("@gsm.com"),
        VFM("@vfm.com"),
        QAM("@qam.com"),
        HRM("@hrm.com");
        private final String emailType;
        typeOfCollaboratorEmails(String emailType) {
            this.emailType = emailType;
        }
        public String getEmailType() {
            return emailType;
        }
        public static typeOfCollaboratorEmails fromEmailType(String emailType) {
            for (typeOfCollaboratorEmails type : typeOfCollaboratorEmails.values()) {
                if (type.emailType.equalsIgnoreCase(emailType)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid email type: " + emailType);
        }

    }

    /**
     * Collaborator email type.
     */

    private typeOfCollaboratorEmails emailType;

    /**
     * Collaborator password.
     */
    private String pwd;

    /**
     * Variable that lets you know if a collaborator has a team.
     */
    private boolean hasTeam = false;

    /**
     * Constructor for Collaborator class.
     * Initializes collaborator attributes based on provided parameters.
     *
     * @param name           Collaborator name.
     * @param birthDate      Collaborator birthdate.
     * @param admissionDate  Collaborator admission date.
     * @param address        Collaborator address.
     * @param mobile         Collaborator mobile number.
     * @param email          Collaborator email.
     * @param taxpayerNumber Collaborator taxpayer number.
     * @param idDocType      Collaborator ID document type.
     * @param idDocNumber    Collaborator ID document number.
     * @param pwd            Collaborator password.
     * @param jobName        Collaborator's Job Name.
     * @throws InvalidCollaboratorDataException if any of the provided data is invalid
     */
    public Collaborator(String name, Date birthDate, Date admissionDate, String address, String mobile, String email,
                        String taxpayerNumber, IdDocType idDocType, String idDocNumber, String pwd, String jobName) throws InvalidCollaboratorDataException {

        if (!ValidatorUtils.isValidName(name)) {
            throw new InvalidCollaboratorDataException("Invalid name.");
        }

        try {
            ValidatorUtils.validateBirthDate(birthDate.toYearMonthDayString());
        }catch (InvalidCollaboratorDataException e){
            throw new InvalidCollaboratorDataException("Invalid birthdate.");
        }

        try {
            ValidatorUtils.validateAdmissionDate(admissionDate.toYearMonthDayString(), birthDate);
        }catch (InvalidCollaboratorDataException e){
            throw new InvalidCollaboratorDataException("Invalid admission date.");
        }

        if (!ValidatorUtils.isValidAddress(address)) {
            throw new InvalidCollaboratorDataException("Invalid address.");
        }

        if (!ValidatorUtils.isValidMobileNumber(mobile)) {
            throw new InvalidCollaboratorDataException("Invalid mobile number.");
        }

        if (!ValidatorUtils.isValidEmail(email)) {
            throw new InvalidCollaboratorDataException("Invalid email.");
        }

        if (!ValidatorUtils.isValidTaxpayerNumber(taxpayerNumber)) {
            throw new InvalidCollaboratorDataException("Invalid taxpayer number.");
        }

        if (!ValidatorUtils.isValidDocumentNumber(idDocType, idDocNumber)) {
            throw new InvalidCollaboratorDataException("Invalid ID document number.");
        }

        if (!ValidatorUtils.isValidPwd(pwd)) {
            throw new InvalidCollaboratorDataException("Invalid password.");
        }


        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;
        this.pwd = pwd;

        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        this.job = jobRepository.getJob(jobName);

        skillSet = new ArrayList<>();
    }

    /**
     * Constructor for Collaborator class.
     * Initializes collaborator attributes based on provided parameters.
     *
     * @param name Collaborator name.
     */
    public Collaborator(String name) throws InvalidCollaboratorDataException {

        if (!ValidatorUtils.isValidName(name)) {
            throw new InvalidCollaboratorDataException("Invalid name.");
        }

        try {
            ValidatorUtils.validateBirthDate(birthDate.toYearMonthDayString());
        }catch (InvalidCollaboratorDataException e){
            throw new InvalidCollaboratorDataException("Invalid birthdate.");
        }

        try {
            ValidatorUtils.validateAdmissionDate(admissionDate.toYearMonthDayString(), birthDate);
        }catch (InvalidCollaboratorDataException e){
            throw new InvalidCollaboratorDataException("Invalid admission date.");
        }

        if (!ValidatorUtils.isValidAddress(address)) {
            throw new InvalidCollaboratorDataException("Invalid address.");
        }

        if (!ValidatorUtils.isValidMobileNumber(mobile)) {
            throw new InvalidCollaboratorDataException("Invalid mobile number.");
        }

        if (!ValidatorUtils.isValidEmail(email)) {
            throw new InvalidCollaboratorDataException("Invalid email.");
        }

        if (!ValidatorUtils.isValidTaxpayerNumber(taxpayerNumber)) {
            throw new InvalidCollaboratorDataException("Invalid taxpayer number.");
        }

        if (!ValidatorUtils.isValidDocumentNumber(idDocType, idDocNumber)) {
            throw new InvalidCollaboratorDataException("Invalid ID document number.");
        }

        if (!ValidatorUtils.isValidPwd(pwd)) {
            throw new InvalidCollaboratorDataException("Invalid password.");
        }

        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;
        this.pwd = pwd;
        skillSet = new ArrayList<>();
    }

    // Getters and setters for collaborator attributes

    /**
     * Get collaborator name.
     *
     * @return Collaborator name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get collaborator birthdate.
     *
     * @return Collaborator birthdate.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Get collaborator admission date.
     *
     * @return Collaborator admission date.
     */
    public Date getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Get collaborator address.
     *
     * @return Collaborator address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get collaborator mobile number.
     *
     * @return Collaborator mobile number.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Get collaborator email.
     *
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
    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    /**
     * Get collaborator ID document type.
     *
     * @return Collaborator ID document type.
     */
    public IdDocType getIdDocType() {
        return idDocType;
    }

    /**
     * Get collaborator ID document number.
     *
     * @return Collaborator ID document number.
     */
    public String getIdDocNumber() {
        return idDocNumber;
    }

    /**
     * Get collaborator password.
     *
     * @return Collaborator password.
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Get the collaborator job.
     *
     * @return collaborator job
     */
    public Job getJob() {
        return job;
    }

    /**
     * Get collaborator skill set.
     *
     * @return All collaborator skills.
     */
    public List<Skill> getSkillSet() {
        return skillSet;
    }

    /**
     * Change collaborator address
     *
     * @param address of the collaborator
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Change collaborator admission date on the enterprise
     *
     * @param admissionDate of the collaborator
     */
    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * Change collaborator birthdate
     *
     * @param birthDate of the collaborator
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Change collaborator email
     *
     * @param email of the collaborator
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Change collaborator identification document number
     *
     * @param idDocNumber of the collaborator
     */
    public void setIdDocNumber(String idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    /**
     * Change collaborator identification document type
     *
     * @param idDocType of the collaborator
     */
    public void setIdDocType(IdDocType idDocType) {
        this.idDocType = idDocType;
    }

    /**
     * Change collaborator mobile phone
     *
     * @param mobile number of the collaborator
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Change collaborator name
     *
     * @param name of the collaborator
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Change collaborator password
     *
     * @param pwd of the collaborator
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Change collaborator skill set
     *
     * @param skillSet of the collaborator
     */
    public void setSkillSet(List<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    /**
     * Change collaborator taxpayer number
     *
     * @param taxpayerNumber of the collaborator
     */
    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    /**
     * Change a collaborator's job.
     *
     * @param job new collaborator job
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Assigns a skill to the collaborator.
     *
     * @param skill skill to be assigned.
     */
    public void assignSkill(Skill skill) {
        this.skillSet.add(skill);
    }

    /**
     * Analyses whether a collaborator has a team.
     *
     * @return True if the collaborator has a team and false otherwise.
     */
    public boolean hasTeam() {
        return hasTeam;
    }

    /**
     * Changes the value of the variable that lets you know if the collaborator has a team.
     *
     * @param b True if the collaborator has a team and false otherwise.
     */
    public void setHasTeam(boolean b) {
        this.hasTeam = b;
    }

    /**
     * String representation of collaborator.
     *
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
                ", job='" + job + '\'' +
                '}';
    }

    /**
     * Analyses whether a collaborator has a skill.
     *
     * @param skill skill to be analysed.
     * @return True if the collaborator has the skill and false otherwise.
     */
    public boolean analyseCollaborator(Skill skill) {
        return this.skillSet.contains(skill);
    }

    /**
     * Adds a team based on a given skill.
     *
     * @param skill the skill to analyze for adding a team
     * @return true if the skill analysis is successful and the team has not been added, false otherwise
     */
    public boolean addTeam(Skill skill){

        return analyseCollaborator(skill) && !this.hasTeam;
    }

    /**
     * Compares this collaborator to another collaborator by name.
     *
     * @param collaborator the collaborator to be compared
     * @return a negative integer, zero, or a positive integer as this collaborator's name
     *         is less than, equal to, or greater than the specified collaborator's name
     */
    @Override
    public int compareTo(Collaborator collaborator){
        return this.name.compareTo(collaborator.getName());
    }
}
