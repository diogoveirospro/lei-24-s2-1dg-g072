package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collaborator with all its attributes.
 */
public class Collaborator {
    /**
     * Collaborator's name.
     */
    private String name;

    /**
     * Collaborator's birthday.
     */
    private String birthday;

    /**
     * Collaborator's admission date.
     */
    private String admissionDate;

    /**
     * Collaborator's address.
     */
    private String address;

    /**
     * Collaborator's mobile number.
     */
    private String mobileNumber;

    /**
     * Collaborator's email.
     */
    private String email;

    /**
     * Collaborator's ID document type.
     */
    private String idDocumentType;

    /**
     * Collaborator's ID document number.
     */
    private String idDocumentNumber;

    /**
     * Collaborator's skills.
     */
    private ArrayList<Skill> skills;

    private SkillRepository skillRepository;

    /**
     * It lets you know if an employee already has a team.
     */
    private boolean hasTeam = false;

    /**
     * Collaborator builder.
     * @param name collaborator's name
     * @param birthday collaborator's birthday
     * @param admissionDate collaborator's admission date
     * @param address collaborator's address
     * @param mobileNumber collaborator's mobile number
     * @param email collaborator's email
     * @param idDocumentType collaborator's ID document type
     * @param idDocumentNumber collaborator's ID document number
     */
    public Collaborator(String name, String birthday, String admissionDate, String address, String mobileNumber, String email, String idDocumentType, String idDocumentNumber) {
        this.name = name;
        this.birthday = birthday;
        this.admissionDate = admissionDate;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.idDocumentType = idDocumentType;
        this.idDocumentNumber = idDocumentNumber;
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    /**
     * Get the collaborator's name
     * @return collaborator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the collaborator's birthday
     * @return collaborator's birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Get the collaborator's admission date
     * @return collaborator's admission date
     */
    public String getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Get the collaborator's address
     * @return collaborator's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the collaborator's mobile number
     * @return collaborator's mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Get the collaborator's email
     * @return collaborator's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the collaborator's ID document type
     * @return collaborator's ID document type
     */
    public String getIdDocumentType(){
        return idDocumentType;
    }

    /**
     * Get the collaborator's ID document number
     * @return collaborator's ID document number
     */
    public String getIdDocumentNumber() {
        return idDocumentNumber;
    }

    /**
     * Get the collaborator's skills
     * @return collaborator's skills
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Change the collaborator's name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Change the collaborator's birthday
     * @param birthday new birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Change the collaborator's admission date
     * @param admissionDate new admission date
     */
    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * Change the collaborator's address
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Change the collaborator's mobile number
     * @param mobileNumber new mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Change the collaborator's email
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Change the collaborator's ID document type
     * @param idDocumentType new ID document type
     */
    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    /**
     * Change the collaborator's ID document number
     * @param idDocumentNumber new ID document number
     */
    public void setIdDocumentNumber(String idDocumentNumber) {
        this.idDocumentNumber = idDocumentNumber;
    }

    /**
     * Changes the status indicating whether the collaborator has a team
     * @param hasTeam true if the collaborator has a team, false otherwise
     */
    public void setHasTeam(boolean hasTeam){
        this.hasTeam = hasTeam;
    }

    /**
     * Analyses an employee to see if they can join a particular team
     * @param skill necessary skill
     * @return true if the collaborator can join the team and false otherwise
     */
    public boolean analyseCollaborator(Skill skill){

        if (skills.isEmpty()){
            return false;
        }else {
            return skills.contains(skill) && !hasTeam;
        }

    }

    public void setJob(Job job) {
    }

    /*public ArrayList<Skill> addSkill(){
        ArrayList<Skill> skills = new ArrayList<Skill>();

    }*/

    /**
     * Collaborator skills.
     */
    private List<Skill> skills;

    /**
     * Collaborator team.
     */
    private Team team;

    /**
     * Collaborator job.
     */
    private Job job;

    /**
     * Collaborator vehicle.
     */
    private Vehicle vehicle;

    /**
     * Constructor for Collaborator class.
     * @param name Collaborator name.
     * @param email Collaborator email.
     * @param job Collaborator job.
     */
    public Collaborator(String name, String email, Job job) {
        this.name = name;
        this.email = email;
        this.skills = new ArrayList<>();
        this.team = null; // Collaborator is not assigned to any team initially
        this.job = job;
        this.vehicle = null; // Collaborator does not have a vehicle initially
    }

    /**
     * Get collaborator name.
     * @return Collaborator name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get collaborator email.
     * @return Collaborator email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get collaborator skills.
     * @return Collaborator skills.
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Add a skill to the collaborator.
     * @param skill Skill to add.
     */
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    /**
     * Remove a skill from the collaborator.
     * @param skill Skill to remove.
     */
    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

    /**
     * Get collaborator team.
     * @return Collaborator team.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Set collaborator team.
     * @param team Team to set.
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Get collaborator job.
     * @return Collaborator job.
     */
    public Job getJob() {
        return job;
    }

    /**
     * Set collaborator job.
     * @param job Job to set.
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Get collaborator vehicle.
     * @return Collaborator vehicle.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Set collaborator vehicle.
     * @param vehicle Vehicle to set.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * String representation of collaborator.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", job=" + job.getName() +
                '}';
    }

}
