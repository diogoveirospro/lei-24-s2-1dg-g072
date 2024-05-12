import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
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
        if (outroObject == null || getClass() != outroObject.getClass()){
            return false;
        } else {
            Vertex otherVertex = (Vertex) outroObject;
            return Objects.equals(v, otherVertex.v);
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(v);
    }

    /**
     *
     * @param otherVertex the object to be compared.
     * @return compares two vertices
     */
    @Override
    public int compareTo(Vertex otherVertex) {
        return this.v.compareTo(otherVertex.v);
    }
}
