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

    /**
     * This is the kruskal method that will generate the lowest
     * costing spanning tree possible
     *
     * @param sortedGraphEdges all the edges sorted
     * @param verticesGraph all the graph vertices
     * @return spanning tree
     */

    public static Graph kruskal(ArrayList<Edge> sortedGraphEdges, ArrayList<Vertex> verticesGraph) {
        Graph A = new Graph();
        int[] parent = new int[verticesGraph.size()];
        int[] rank = new int[verticesGraph.size()];
        for (int i = 0; i < verticesGraph.size(); i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        int nA = 0;
        int i = 0;
        while (nA < verticesGraph.size() - 1 && i < sortedGraphEdges.size()) {
            Edge e = sortedGraphEdges.get(i);
            int u = verticesGraph.indexOf(e.getOrigin());
            int v = verticesGraph.indexOf(e.getDestiny());
            int rootU = find(u, parent);
            int rootV = find(v, parent);
            if (rootU != rootV) {
                A.addEdge(e);
                union(rootU, rootV, parent, rank);
                nA++;
            }
            i++;
        }
        return A;
    }

    /**
     * Will check if the vertex is still in is original sack
     *
     * @param vertex of the graph
     * @param parent a sack where a vertex is original from
     * @return the vertex
     */
    private static int find(int vertex, int[] parent) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent[vertex], parent);
        }
        return parent[vertex];
    }

    /**
     * Will get the multiple vertices to move them to the sack
     *
     * @param rootU
     * @param rootV
     * @param parent
     * @param rank
     */
    public static void union(int rootU, int rootV, int[] parent, int[] rank) {
        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }
    public int getEdgeCost(Vertex u, Vertex v) {
        for (Edge edge : graph) {
            if ((edge.getOrigin().equals(u) && edge.getDestiny().equals(v)) ||
                    (edge.getOrigin().equals(v) && edge.getDestiny().equals(u))) {
                return edge.getCost();
            }
        }
        return -1;
    }
}
