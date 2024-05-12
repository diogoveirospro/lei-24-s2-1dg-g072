package PI_MDISC_Group_072.main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final int QUANTITY_OF_FILES = 30;
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
        ArrayList<Edge> sortedGraphEdges = sortCost(graphEdges);
        ArrayList<Vertex> verticesGraph = getVerticesGraph(graphEdges);
        Collections.sort(verticesGraph);
        Graph spanningTree = kruskal(sortedGraphEdges, verticesGraph);
        writeOutput(spanningTree, inputFile);
        graphInfo(sortedGraphEdges.size(),verticesGraph.size(),spanningTree,inputFile);
        createSpanningTree(spanningTree,inputFile);
    }
    private static void US14() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputFile = new StringBuilder(getFile(sc));
        StringBuilder aux = inputFile;
        ArrayList<Double> time = new ArrayList<>();
        ArrayList<Integer> quantityOfEdges = new ArrayList<Integer>();
        for (int i = 0; i <  QUANTITY_OF_FILES; i++) {
            inputFile = aux;
            inputFile = new StringBuilder(inputFile + "_" + (i + 1));
            StringBuilder file = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFile + ".csv");
            ArrayList<Edge> graphEdges = readFile(file);
            Graph graph = addEdges(graphEdges);
            createGraph(graph, inputFile);
            quantityOfEdges.add(graphEdges.size());
            ArrayList<Edge> sortedGraphEdges = sortCost(graphEdges);
            ArrayList<Vertex> verticesGraph = getVerticesGraph(graphEdges);
            Collections.sort(verticesGraph);
            Graph spanningTree = kruskal(sortedGraphEdges, verticesGraph);
            writeOutput(spanningTree,inputFile);
            graphInfo(sortedGraphEdges.size(),verticesGraph.size(),spanningTree,inputFile);
            createSpanningTree(spanningTree,inputFile);
            time.add((double) System.currentTimeMillis());
        }
        double [][] asymptoticGraph = new double[2][time.size()];
        addData(time,quantityOfEdges,asymptoticGraph);
        writeData(asymptoticGraph);
        createGnuplotGraph();
    }

    private static void addData(ArrayList<Double> time, ArrayList<Integer> quantityOfEdges,double[][] asymptoticGraph) {
        for (int i = 0; i < time.size(); i++) {
            asymptoticGraph[0][i] = quantityOfEdges.get(i);
            asymptoticGraph[1][i] = time.get(i);
        }
    }

    private static void writeData(double[][] asymptoticGraph) {
        try (PrintWriter writer = new PrintWriter(new FileWriter( "src/main/java/PI_MDISC_Group_072/Output/AsymptoticData.dat"))) {
            for (int i = 0; i < asymptoticGraph.length; i++) {
                writer.printf("%d;%f",(int) asymptoticGraph[0][i],asymptoticGraph[1][i]);
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    private static void createGraph(Graph graph, StringBuilder file) throws IOException, InterruptedException {
        createScriptGraph(graph,file);
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
        try (PrintWriter writer = new PrintWriter(new FileWriter( "src/main/java/PI_MDISC_Group_072/Output/Graph_" + file + ".dot"))) {
            writer.println("graph G {");
            writer.println("    fontname=\"Helvetica,Arial,sans-serif\"");
            writer.println("    node [fontname=\"Helvetica,Arial,sans-serif\"]");
            writer.println("    edge [fontname=\"Helvetica,Arial,sans-serif\"]");
            writer.println("    layout=neato");
            for (Edge edge : graph.getEdges()) {
                writer.printf("    %s -- %s;",edge.getOrigin().getV(),edge.getDestiny().getV());
                writer.printf("%n");
            }
            writer.print("}");
            System.out.println("DOT file 'src/main/java/PI_MDISC_Group_072/Output/Graph.dot' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to DOT file: " + e.getMessage());
        }
    }

    private static Graph addEdges(ArrayList<Edge> graphEdges) {
        Graph graph = new Graph();
        for (Edge edge : graphEdges){
            graph.addEdge(edge);
        }
        return graph;
    }

    private static void graphInfo(int graphDimension, int graphOrder, Graph spanningTree, StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter( "src/main/java/PI_MDISC_Group_072/Output/SpanningTreeInformation_" + file + ".csv"))) {
            int size = 0;
            for (int i = 0; i < spanningTree.getEdges().size(); i++){
                size+= spanningTree.getEdges().get(i).getCost();
            }
            writer.print("Graph Dimension = " + graphDimension + " ; Graph Order = " + graphOrder + " ; Cost of a Minimum spanning tree = " + size);
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



    private static void createScriptTree(Graph spanningTree,StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter( "src/main/java/PI_MDISC_Group_072/Output/SpanningTreeGraph_" + file + ".dot"))) {
            writer.println("graph G {");
            writer.println("    fontname=\"Helvetica,Arial,sans-serif\"");
            writer.println("    node [fontname=\"Helvetica,Arial,sans-serif\"]");
            writer.println("    edge [fontname=\"Helvetica,Arial,sans-serif\"]");
            writer.println("    layout=neato");
            for (Edge edge : spanningTree.getEdges()) {
                writer.printf("    %s -- %s;",edge.getOrigin().getV(),edge.getDestiny().getV());
                writer.printf("%n");
            }
            writer.print("}");
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
        System.out.println("Insert the name of the file with the vertices, costs and connections:");
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

    private static ArrayList<Edge> sortCost(ArrayList<Edge> graphEdges) {
        Collections.sort(graphEdges, (e1, e2) -> Integer.compare(e1.getCost(), e2.getCost()));
        return graphEdges;
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

    private static void writeOutput(Graph spanningTree,StringBuilder file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter( "src/main/java/PI_MDISC_Group_072/Output/SpanningTree_" + file +".csv"))) {
            writer.println("Vertex1;Vertex2;Cost");
            int totalCost = 0;
            for (Edge edge : spanningTree.getEdges()) {
                writer.printf("%s;%s;%d",edge.getOrigin().getV(),edge.getDestiny().getV(),edge.getCost());
                writer.printf("%n");
                totalCost += edge.getCost();
            }
            writer.printf("Total cost of making SpanningTree: %d%n",totalCost);
            System.out.println("CSV file 'src/main/java/PI_MDISC_Group_072/Output/SpanningTree_" + file + "' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
    public static void createScriptGnuplot() throws FileNotFoundException {
        File gnuplot = new File("script_Gnuplot.gp");
        PrintWriter printWriter = new PrintWriter(gnuplot);
        printWriter.println("set terminal png size 800,600");
        printWriter.println("set output 'src/src/Output/asymptotic_graph.png'");
        printWriter.println("set title 'Asymptotic Graph'");
        printWriter.println("set xlabel 'quantity of edges'");
        printWriter.println("set ylabel 'time to create'");
        printWriter.println("set datafile separator '\\t'");
        printWriter.println("plot 'src/src/Output/AsymptoticData.dat' using 1:2 with linespoints linewidth 3  title 'Asymptotic Behavior', \\");
        printWriter.close();
    }
    public static void createGnuplotGraph() throws IOException, InterruptedException {
        createScriptGnuplot();
        String caminhoScript = "script_Gnuplot.gp";
        String comandoGnuplot = "gnuplot " + caminhoScript;
        Process processo = Runtime.getRuntime().exec(comandoGnuplot);

        int exitCode = processo.waitFor();

        if (exitCode == 0) {

            System.out.println("Graph generated successfully!");

        } else {

            System.out.println("Error generating the graph. Output code: " + exitCode);

        }
    }
}
