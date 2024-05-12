package PI_MDISC_Group_072.main.java;

import java.util.ArrayList;

public class Graph {
    /**
     * A list containing all the edges(the origin and destiny points and cost of making them)
     */
    private ArrayList<Edge> graph;

    /**
     * A constructor of Graph that initiates the instance graph
     */
    public Graph() {
        graph = new ArrayList<>();
    }

    /**
     * A function that adds new vertices that exist in the graph
     *
     * @return vertices
     */
    public ArrayList<Vertex> getVertices() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (Edge graph : graph) {
            if (!vertices.contains(graph.getOrigin())) {
                vertices.add(graph.getOrigin());
            }
            if (!vertices.contains(graph.getDestiny())) {
                vertices.add(graph.getDestiny());
            }
        }
        return vertices;
    }

    /**
     * This function will get vertices that are equal to the vertex v, and will return an array with all those vertices.
     *
     * @param v a vertex
     * @return vertices
     */
    public ArrayList<Vertex> getVerticesConnectedTo(Vertex v) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (Edge graph : graph) {
            if (graph.getOrigin().equals(v)) {
                vertices.add(graph.getDestiny());
            }
            if (graph.getDestiny().equals(v)) {
                vertices.add(graph.getOrigin());
            }
        }

        return vertices;
    }

    /**
     * Adds an edge to the graph
     *
     * @param edge Origin and the destiny of the pipe, and also the cost to make it
     */
    public void addEdge(Edge edge){
        if (graph == null){
            graph = new ArrayList<>();
        }
        graph.add(edge);
    }

    public ArrayList<Edge> getEdges(){
        return this.graph;
    }
}
