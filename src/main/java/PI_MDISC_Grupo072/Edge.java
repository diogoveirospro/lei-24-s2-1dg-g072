package PI_MDISC_Grupo072;
public class Edge {

    /**
     * Arrival point of the pipeline
     *
     */
    private Vertex destiny;
    /**
     * Origin point of the pipeline
     *
     */
    private Vertex origin;
    /**
     * Cost of making the Pipeline
     *
     */
    private int cost;

    /**
     * Constructor of Edge with the instances origin, destiny and cost.
     *
     * @param origin point of the pipeline
     * @param destiny point of the pipeline
     * @param cost of making the pipeline
     */
    public Edge(Vertex origin, Vertex destiny, int cost) {
        this.destiny = destiny;
        this.origin = origin;
        this.cost = cost;
    }

    /**
     * Lets the user get the destiny of the pipeline
     *
     * @return destiny
     */
    public Vertex getDestiny() {
        return destiny;
    }

    /**
     * Lets the user get the origin of the pipeline
     *
     * @return origin
     */
    public Vertex getOrigin() {
        return origin;
    }

    /**
     * Lets the user get the cost of making the pipeline
     *
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Lets the user change the value of making a pipeline
     *
     * @param cost of making a pipeline
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Lets the user change the destiny point of a pipeline
     *
     * @param destiny of the pipeline
     */
    public void setDestiny(Vertex destiny) {
        this.destiny = destiny;
    }

    /**
     * Lets the user change the origin point of a pipeline
     *
     * @param origin of the pipeline
     */
    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    /**
     * Compares two different edges
     *
     * @param otherObject Another edge
     * @return the comparison between two edges
     */
    @Override
    public boolean equals(Object otherObject) {
        Edge otherEdge = (Edge) otherObject;
        return this.getOrigin().equals(otherEdge.getOrigin()) && this.getDestiny().equals(otherEdge.getDestiny());
    }
}
