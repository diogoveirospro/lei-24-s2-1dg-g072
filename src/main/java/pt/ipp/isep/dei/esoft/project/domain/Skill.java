package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Skill class represents a skill with a unique name that can be registered
 * in the HR management system. It enforces that the skill name must not be null or empty.
 * @author Group 072 - Byte Masters - ISEP
 */

public class Skill implements Comparable<Skill> {
    private String name;

    /**
     * Constructs a new Skill with the specified name.
     * @param name the name of the skill; must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */

    public Skill (String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }

    /**
     * Returns the name of the skill.
     * @return the name of the skill.
     */

    public String getName(){
        return name;
    }

    /**
     * Sets the name of the skill.
     *
     * @param name the new name of the skill can not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */

    public void setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }

    /**
     * Rewriting the equals method for the skill class.
     * @param obj another skill to compare.
     * @return true if they are equally false, otherwise.
     */

    @Override
    public boolean equals(Object obj) {

        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Skill anotherSkill = (Skill) obj;

        return this.getName().equals(anotherSkill.getName()) ;
    }

    /**
     * Checks if the name sent by the user is between the letters specified.
     *
     * @param name of the skill
     * @return the result of the name being or not in accord with the pattern
     */
    public boolean validateSkill(String name){
        String pattern = "^[a-zA-Z0-9 ]+$";
        return name.matches(pattern);
    }

    /**
     * Rewriting the toString method for the skill class.
     * @return String representing a skill.
     */

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int compareTo(Skill skill){
        return this.name.compareTo(skill.getName());
    }
}
