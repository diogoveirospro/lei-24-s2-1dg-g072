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
     * Collaborator email.
     */
    private String email;

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
