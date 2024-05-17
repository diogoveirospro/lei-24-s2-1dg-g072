package PI_MDISC_Group_072.main.java;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static final int QUANTITY_OF_FILES = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("""
                    Choose the option you want:\s
                    Option 1: Make a savings tree.\s
                    Option 2: Make a graph of time as a function of the number of vertices.""");
            option = sc.nextInt();

            if (option == 1){
                US13();
            } else if (option == 2){
                US14();
            } else {
                System.out.println("Please insert a valid option");
            }

        } while (option < 1 || option > 2);
    }

    private static void US13() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputFile = new StringBuilder(getFile(sc));
        StringBuilder file = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFile + ".csv");

        ArrayList<Edge> graphEdges = readFile(file);
        Graph graph = addEdges(graphEdges);
        createGraph(graph,inputFile);

        bubbleSort(graphEdges);

        ArrayList<Vertex> verticesGraph = getVerticesGraph(graphEdges);

        Graph spanningTree = kruskal(graphEdges, verticesGraph);

        writeOutput(spanningTree, inputFile);
        graphInfo(graphEdges.size(), verticesGraph.size(), spanningTree, inputFile);
        createSpanningTree(spanningTree, inputFile);
    }

    private static void US14() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputFile = new StringBuilder(getFile(sc));
        StringBuilder aux = inputFile;

        ArrayList<Double> time = new ArrayList<>();
        ArrayList<Integer> quantityOfEdges = new ArrayList<>();

        for (int i = 0; i <  QUANTITY_OF_FILES; i++) {
            long startTime = System.nanoTime();

            inputFile = aux;
            inputFile = new StringBuilder(inputFile + "_" + (i + 1));
            StringBuilder file = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFile + ".csv");


            ArrayList<Edge> graphEdges = readFile(file);
            quantityOfEdges.add(graphEdges.size());
            ArrayList<Vertex> verticesGraph = getVerticesGraph(graphEdges);
            Graph spanningTree = kruskal(graphEdges, verticesGraph);
            long endTime = System.nanoTime();
            long durationTime =((endTime - startTime));
            time.add((double) durationTime);
            System.out.println("File: " + inputFile + ".csv done!");
        }

        asymptoticInfo(quantityOfEdges,time);
        createGnuplotGraph();
    }





    private static void createGraph(Graph graph, StringBuilder file) throws IOException, InterruptedException {
        createScriptGraph(graph, file);
        executeScriptGraph(file);
    }

    private static void executeScriptGraph(StringBuilder file) throws IOException, InterruptedException {
        String caminhoScript = "src/main/java/PI_MDISC_Group_072/Output/Graph_" + file;
        String commandGraphviz = "dot -Tsvg " + caminhoScript + ".dot -o " + caminhoScript + ".svg";
        Process processo = Runtime.getRuntime().exec(commandGraphviz);

        BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.err.println(line);
        }

        int exitCode = processo.waitFor();

        if (exitCode == 0) {
            System.out.println("Graph generated successfully!");
        } else {
            System.out.println("Error generating the graph. Output code: " + exitCode);
        }
    }

    private static void createScriptGraph(Graph graph, StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/PI_MDISC_Group_072/Output/Graph_" + file + ".dot"))) {
            writer.println("graph G {");
            writer.println("    fontname=\"Helvetica,Arial,sans-serif\"");
            writer.println("    nodesep=1.0");
            writer.println("    layout=dot");
            writer.println("    node [fontname=\"Helvetica,Arial,sans-serif\"]");
            writer.println("    edge [fontname=\"Helvetica,Arial,sans-serif\"]");

            for (Edge edge : graph.getEdges()) {
                String origin = edge.getOrigin().getV();
                String destiny = edge.getDestiny().getV();
                int cost = edge.getCost();
                writer.printf("    %s -- %s [label=\"%d\"];%n", origin, destiny, cost);
            }

            writer.println("}");
            System.out.println("DOT file 'src/main/java/PI_MDISC_Group_072/Output/Graph.dot' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to DOT file: " + e.getMessage());
        }
    }

    private static Graph addEdges(ArrayList<Edge> graphEdges) {
        Graph graph = new Graph();
        for (Edge edge : graphEdges) {
            graph.addEdge(edge);
        }
        return graph;
    }

    private static void graphInfo(int graphDimension, int graphOrder, Graph spanningTree, StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/PI_MDISC_Group_072/Output/SpanningTreeInformation_" + file + ".csv"))) {
            int totalCost = spanningTree.getEdges().stream().mapToInt(Edge::getCost).sum();
            writer.printf("Graph Dimension = %d ; Graph Order = %d ; Cost of a Minimum spanning tree = %d%n", graphDimension, graphOrder, totalCost);
            System.out.println("CSV file 'src/main/java/PI_MDISC_Group_072/Output/SpanningTreeInformation.csv' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private static void createSpanningTree(Graph spanningTree, StringBuilder file) throws IOException, InterruptedException {
        createScriptTree(spanningTree, file);
        executeScriptTree(file);
    }

    private static void executeScriptTree(StringBuilder file) throws IOException, InterruptedException {
        String caminhoScript = "src/main/java/PI_MDISC_Group_072/Output/SpanningTreeGraph_" + file;
        String commandGraphviz = "dot -Tsvg " + caminhoScript + ".dot -o " + caminhoScript + ".svg";
        Process processo = Runtime.getRuntime().exec(commandGraphviz);

        BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.err.println(line);
        }

        int exitCode = processo.waitFor();

        if (exitCode == 0) {
            System.out.println("Spanning Tree Graph successfully generated!");
        } else {
            System.out.println("Error when generating the Spanning Tree Graph. Output code: " + exitCode);
        }
    }

    private static void createScriptTree(Graph spanningTree, StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/PI_MDISC_Group_072/Output/SpanningTreeGraph_" + file + ".dot"))) {
            writer.println("graph G {");
            writer.println("    fontname=\"Helvetica,Arial,sans-serif\"");
            writer.println("    node [fontname=\"Helvetica,Arial,sans-serif\"]");
            writer.println("    nodesep=1.0");
            writer.println("    layout=dot");
            writer.println("    edge [fontname=\"Helvetica,Arial,sans-serif\"]");

            for (Edge edge : spanningTree.getEdges()) {
                String origin = edge.getOrigin().getV();
                String destiny = edge.getDestiny().getV();
                int cost = edge.getCost();
                String edgeString = String.format("    %s -- %s [label=\"%d\"];", origin, destiny, cost);
                writer.println(edgeString);
            }

            writer.println("}");
            System.out.println("DOT file 'src/main/java/PI_MDISC_Group_072/Output/SpanningTreeGraph.dot' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to DOT file: " + e.getMessage());
        }
    }

    private static Graph kruskal(ArrayList<Edge> sortedGraphEdges, ArrayList<Vertex> verticesGraph) {
        Graph A = new Graph();
        Vertex[][] S = new Vertex[verticesGraph.size()][verticesGraph.size()];
        int nA = 0;
        addVerticesToSack(verticesGraph, S, nA);
        int i = 0;
        while (nA < verticesGraph.size() - 1) {
            Edge edge = sortedGraphEdges.get(i);
            int Sp = locationVertexInS(edge.getOrigin(), S);
            int Sq = locationVertexInS(edge.getDestiny(), S);
            if (Sp != Sq && (Sp != -1 && Sq != -1)) {
                A.addEdge(edge);
                moveVertices(S, Sp, Sq);
                nA++;
            }
            i++;
        }
        return A;
    }

    private static void addVerticesToSack(ArrayList<Vertex> verticesGraph, Vertex[][] S, int nA) {
        for (Vertex vertex : verticesGraph) {
            S[0][nA] = vertex;
            nA++;
        }
    }

    private static int locationVertexInS(Vertex vertex, Vertex[][] S) {
        for (int i = 0; i < S[0].length; i++) {
            for (Vertex[] vertices : S) {
                if (vertices[i] != null && vertices[i].equals(vertex)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void moveVertices(Vertex[][] S, int Sp, int Sq) {
        int i = 0;
        int j = 0;

        while (S[j][Sp] != null) {
            j++;
            if (j == S.length){
                break;
            }
        }

        while (S[i][Sq] != null && j < S.length) {
            S[j][Sp] = S[i][Sq];
            S[i][Sq] = null;
            i++;
            j++;

            if (j == S.length) {
                break;
            }

            while (S[j][Sp] != null) {
                j++;
                if (j == S.length) {
                    break;
                }
            }
        }
    }

    private static String getFile(Scanner sc) {
        System.out.println("Insert the name of the file with the vertices, costs, and connections:");
        return sc.nextLine();
    }

    private static ArrayList<Edge> readFile(StringBuilder file) throws FileNotFoundException {
        ArrayList<Edge> graphEdges = new ArrayList<>();
        Scanner in = new Scanner(new File(String.valueOf(file)));

        while (in.hasNextLine()) {
            Edge edge = readLine(in);
            if (edge != null) {
                graphEdges.add(edge);
            }
        }

        in.close();
        return graphEdges;
    }

    private static Edge readLine(Scanner in) {
        if (in.hasNextLine()) {
            String line = in.nextLine();
            String[] parts = line.split(";");
            if (parts.length == 3) {
                String origin = parts[0];
                String destiny = parts[1];
                int cost = Integer.parseInt(parts[2]);
                return new Edge(new Vertex(origin), new Vertex(destiny), cost);
            }
        }
        return null;
    }

    private static ArrayList<Vertex> getVerticesGraph(ArrayList<Edge> graphEdges) {
        ArrayList<Vertex> verticesGraph = new ArrayList<>();
        for (Edge edge : graphEdges) {
            Vertex vertex1 = edge.getOrigin();
            Vertex vertex2 = edge.getDestiny();
            if (!verticesGraph.contains(vertex1)) {
                verticesGraph.add(vertex1);
            }
            if (!verticesGraph.contains(vertex2)) {
                verticesGraph.add(vertex2);
            }
        }
        return verticesGraph;
    }

    private static void writeOutput(Graph spanningTree, StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/PI_MDISC_Group_072/Output/SpanningTree_" + file + ".csv"))) {
            writer.println("Vertex1;Vertex2;Cost");
            int totalCost = spanningTree.getEdges().stream().mapToInt(Edge::getCost).sum();
            for (Edge edge : spanningTree.getEdges()) {
                writer.printf("%s;%s;%d%n", edge.getOrigin().getV(), edge.getDestiny().getV(), edge.getCost());
            }
            writer.printf("Total cost of making SpanningTree: %d%n", totalCost);
            System.out.println("CSV file 'src/main/java/PI_MDISC_Group_072/Output/SpanningTree_" + file + "' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static void createScriptGnuplot() throws FileNotFoundException {
        File gnuplot = new File("src/main/java/PI_MDISC_Group_072/Output/script_Gnuplot.gp");
        PrintWriter printWriter = new PrintWriter(gnuplot);
        printWriter.println("set terminal png size 800,600");
        printWriter.println("set output 'src/main/java/PI_MDISC_Group_072/Output/asymptotic_graph.png'");
        printWriter.println("set title 'Execution Time vs Input Size'");
        printWriter.println("set xlabel 'Input Size'");
        printWriter.println("set ylabel 'Time (milliseconds)'");
        printWriter.println("set datafile separator ';'");
        printWriter.println("plot 'src/main/java/PI_MDISC_Group_072/Output/AsymptoticBehavior.csv' skip 1 using 1:2 with linespoints linewidth 3  title 'Asymptotic Behavior', \\");
        printWriter.close();
    }

    public static void createGnuplotGraph() throws IOException, InterruptedException {
        createScriptGnuplot();
        String caminhoScript = "src/main/java/PI_MDISC_Group_072/Output/script_Gnuplot.gp";
        String comandoGnuplot = "gnuplot " + caminhoScript;
        Process processo = Runtime.getRuntime().exec(comandoGnuplot);

        int exitCode = processo.waitFor();

        if (exitCode == 0) {
            System.out.println("Graph generated successfully!");
        } else {
            System.out.println("Error generating the graph. Output code: " + exitCode);
        }
    }

    private static void bubbleSort(ArrayList<Edge> graphEdges) {
        int n = graphEdges.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (graphEdges.get(j).getCost() > graphEdges.get(j + 1).getCost()) {

                    Edge temp = graphEdges.get(j);
                    graphEdges.set(j, graphEdges.get(j + 1));
                    graphEdges.set(j + 1, temp);
                }
            }
        }
    }
    private static void asymptoticInfo(ArrayList<Integer> quantityOfEdges, ArrayList<Double> time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/PI_MDISC_Group_072/Output/AsymptoticBehavior.csv"))) {
            writer.println("size;time");
            for (int i = 0; i < quantityOfEdges.size(); i++) {
                writer.printf("%d;%f ", quantityOfEdges.get(i), time.get(i));
                writer.printf("%n");
            }
            System.out.println("CSV file '\"src/main/java/PI_MDISC_Group_072/Output/AsymptoticBehavior.csv' has been created successfully.");

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }

    }
}