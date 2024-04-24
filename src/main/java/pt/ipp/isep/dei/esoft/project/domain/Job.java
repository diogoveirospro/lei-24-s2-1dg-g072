package pt.ipp.isep.dei.esoft.project.domain;

public class Job {

    private String name;

    private static final String DEFAULT_NAME = "none";

    public Job(){
        this.name = DEFAULT_NAME;
    }

    public Job(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
