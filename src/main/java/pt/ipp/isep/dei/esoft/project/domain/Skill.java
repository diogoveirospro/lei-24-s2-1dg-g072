package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {
    private String name;
    public Skill (String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name:"+ name;
    }
}
