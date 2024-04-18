package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {
    private String name;
    private String adress;
    private double dimension;

    public GreenSpace(String name, String adress, double dimension) {
        this.name = name;
        this.adress = adress;
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public double getDimension() {
        return dimension;
    }

    public String setName(String name) {
        this.name = name;
    }

    public String setAdress(String adress) {
        this.adress = adress;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }
}
