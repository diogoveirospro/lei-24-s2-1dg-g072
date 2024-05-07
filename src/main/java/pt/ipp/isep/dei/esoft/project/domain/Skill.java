package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Skill class represents a skill with a unique name that can be registered
 * in the HR management system. It enforces that the skill name must not be null or empty.
 */

public class Skill {
    private String name;

    /**
     * Constructs a new Skill with the specified name.
     *
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
     *
     * @return the name of the skill.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the skill.
     *
     * @param name the new name of the skill; must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }

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
}
