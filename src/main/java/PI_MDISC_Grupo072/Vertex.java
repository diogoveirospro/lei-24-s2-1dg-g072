package PI_MDISC_Grupo072;

import java.util.Objects;

public class Vertex {
    /**
     * A vertex of the graph
     *
     */
    private String v;

    /**
     * A constructor of Vertex that receives v, as an instance
     *
     * @param v a vertex of the graph
     */
    public Vertex(String v) {
        this.v = v;

    }

    /**
     * Lets the user get the value of vertex v
     *
     * @return v
     */
    public String getV() {
        return v;
    }

    /**
     * Lets the user change the value of vertex v
     * @param v
     */
    public void setV(String v) {
        this.v = v;
    }

    /**
     *
     * @param outroObject another vertex
     * @return the comparison of two vertices
     */
    @Override
    public boolean equals(Object outroObject) {
        String otherVertex = (String) outroObject;

        return Objects.equals(v, otherVertex);
    }
}
