package pt.ipp.isep.dei.esoft.project.domain;

public class Collaborator {
    private String name;

    public Collaborator (String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
