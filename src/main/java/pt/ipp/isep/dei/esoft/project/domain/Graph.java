package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Edge> grafo;

    public Graph() {
        grafo = new ArrayList<>();
    }

    public ArrayList<Vertice> getVertices() {
        ArrayList<Vertice> vertices = new ArrayList<>();
        for (Edge graph : grafo) {
            if (!vertices.contains(graph.getOrigem())) {
                vertices.add(graph.getOrigem());
            }
            if (!vertices.contains(graph.getDestino())) {
                vertices.add(graph.getDestino());
            }
        }
        return vertices;
    }

    public ArrayList<Vertice> getVerticesConnectedTo(Vertice v) {
        ArrayList<Vertice> vertices = new ArrayList<>();
        for (Edge graph : grafo) {
            if (graph.getOrigem().equals(v)) {
                return vertices;
            }
            if (graph.getDestino().equals(v)) {
                return vertices;
            }
        }

        return null;
    }
}
