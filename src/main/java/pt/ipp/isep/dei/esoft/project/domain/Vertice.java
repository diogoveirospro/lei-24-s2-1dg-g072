package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Vertice {
    private String v;

    public Vertice(String v) {
        this.v = v;

    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public boolean equals(Object outroObject) {
        String outroVertice = (String) outroObject;

        return Objects.equals(v, outroVertice);
    }
}
