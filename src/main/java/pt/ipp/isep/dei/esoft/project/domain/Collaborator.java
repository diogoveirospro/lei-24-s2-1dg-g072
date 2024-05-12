package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collaborator with all its attributes.
 *
 * @author Group 072 - Byte Masters - ISEP
 */
public class Collaborator {

    /**
     * Collaborator name.
     */
    private String name;

    /**
     * Collaborator birthdate.
     */
    private LocalDate birthDate;

    /**
     * Collaborator admission date.
     */
    private LocalDate admissionDate;

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
     * Collaborator's job.
     */
    private Job job;

    /**
     * Collaborator's skill set.
     */
    private List<Skill> skillSet;

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
     * @param jobName        Collaborator's Job Name.
     */
    public Collaborator(String name, LocalDate birthDate, LocalDate admissionDate, String address, String mobile, String email,
                        String taxpayerNumber, String idDocType, String idDocNumber, String jobName) {
        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.taxpayerNumber = taxpayerNumber;
        this.idDocType = idDocType;
        this.idDocNumber = idDocNumber;

        JobRepository jobRepository = new JobRepository();
        this.job = jobRepository.getJob(jobName);

        skillSet = new ArrayList<>();
    }

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
     */
    public Collaborator(String name, LocalDate birthDate, LocalDate admissionDate, String address, String mobile, String email,
                        String taxpayerNumber, String idDocType, String idDocNumber) {
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
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Get collaborator admission date.
     *
     * @return Collaborator admission date.
     */
    public LocalDate getAdmissionDate() {
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
    public String getIdDocType() {
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
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * Change collaborator birthdate
     *
     * @param birthDate of the collaborator
     */
    public void setBirthDate(LocalDate birthDate) {
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
    public void setIdDocType(String idDocType) {
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
}

