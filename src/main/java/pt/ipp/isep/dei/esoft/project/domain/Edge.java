package pt.ipp.isep.dei.esoft.project.domain;

public class Edge {
    private Vertice destino;
    private Vertice origem;
    private int distancia;

    public Edge(Vertice origem, Vertice destino, int distancia) {
        this.destino = destino;
        this.origem = origem;
        this.distancia = distancia;
    }

    public Vertice getDestino() {
        return destino;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    @Override
    public boolean equals(Object outroObject) {
        Edge outroEdge = (Edge) outroObject;
        return this.getOrigem().equals(outroEdge.getOrigem()) && this.getDestino().equals(outroEdge.getDestino());
    }
}
