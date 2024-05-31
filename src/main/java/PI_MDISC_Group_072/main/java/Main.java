package PI_MDISC_Group_072.main.java;

import java.io.*;
import java.util.*;

public class Main {

    public static final int QUANTITY_OF_FILES = 30;

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("""
                    Choose the option you want:\s
                    Option 1: Make a spanning tree.\s
                    Option 2: Make a graph of time as a function of the number of vertices.\s
                    Option 3: Make a graph of the evacuation routes to one Meeting Point.
                    Option 4: Make a graph of the evacuation routes to different Meeting Points.""");
            try {
                option = sc.nextInt();

                if (option == 1) {
                    US13();
                } else if (option == 2) {
                    US14();
                } else if (option == 3) {
                    US17();
                } else if (option == 4) {
                    US18();
                } else {
                    System.out.println("Please insert a valid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please insert a number!");
                sc.next();
            }
            ;


        } while (option < 1 || option > 4);
    }

    private static void US18() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputVerticesFile = new StringBuilder(getFile(sc));
        StringBuilder inputFileWeights = new StringBuilder(getFile(sc));
        StringBuilder fileWeights = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFileWeights + ".csv");
        StringBuilder fileVertices = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputVerticesFile + ".csv");

        ArrayList<Vertex> vertices = readVertexFile(fileVertices);
        int[][] weights = readWeightFile(fileWeights);
        ArrayList<Edge> graphEdges = new ArrayList<>();
        makeEdges(graphEdges, vertices, weights);
        // bubbleSort(graphEdges);
        ArrayList<String> MP = new ArrayList<>();
        sortMP(MP, vertices);
        ArrayList<Graph> evacuationRoutes = new ArrayList<>();
        if (MP.isEmpty()) {
            System.out.println("There is no Meeting Point in the file!");
        } else {
            for (int i = 0; i < MP.size(); i++) {
                String vertex = sc.nextLine();
                evacuationRoutes.add(Dijkstra(graphEdges, vertices,vertex));
            }
        }
        System.out.println("Insert the vertex you want to know the shortest path to the closest AP or 'done'(if you want to stop):");
        String vertex = sc.nextLine();
        while (!vertex.equalsIgnoreCase("done")) {
            vertex = sc.nextLine();
        }

    }

    private static void sortMP(ArrayList<String> AP, ArrayList<Vertex> vertices) {

        for (Vertex vertex : vertices) {
            if (vertex.getV().contains("AP")) {
                AP.add(vertex.getV());
            }
        }
    }


    private static void US17() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputFile = new StringBuilder(getFile(sc));
        StringBuilder file = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFile + ".csv");

        ArrayList<Vertex> vertices = readVertexFile(file);
        int[][] weights = readWeightFile(file);
        ArrayList<Edge> graphEdges = new ArrayList<>();
        makeEdges(graphEdges, vertices, weights);
        // bubbleSort(graphEdges);

        String MP = getMP(vertices);
        if (MP == null) {
            System.out.println("There is no Meeting Point in the file!");
        } else {
            System.out.println("Insert the vertex you want to know the shortest path to the AP or 'done'(if you want to stop):");
            String vertex = sc.nextLine();
            Graph evacuationRoutes = Dijkstra(graphEdges, vertices,vertex);
            System.out.println("Insert the vertex you want to know the shortest path to the AP or 'done'(if you want to stop):");
            vertex = sc.nextLine();
            while (!vertex.equalsIgnoreCase("done")) {

                vertex = sc.nextLine();
            }
        }

    }

    private static String getMP(ArrayList<Vertex> vertices) {
        String AP = null;
        for (Vertex vertex : vertices) {
            if (vertex.getV().contains("AP")) {
                AP = vertex.getV();
            }
        }
        return AP;
    }

    private static void makeEdges(ArrayList<Edge> graphEdges, ArrayList<Vertex> vertices, int[][] weights) {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (weights[i][j] != 0) {
                    if (i != j) {
                        Edge edge = new Edge(vertices.get(i), vertices.get(j), weights[i][j]);
                        graphEdges.add(edge);
                    }

                }
            }
        }

    }

    private static void US13() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputFile = new StringBuilder(getFile(sc));
        StringBuilder file = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFile + ".csv");

        ArrayList<Edge> graphEdges = readFile(file);
        Graph graph = addEdges(graphEdges);
        createGraph(graph, inputFile);

        bubbleSort(graphEdges);

        ArrayList<Vertex> verticesGraph = getVerticesGraph(graphEdges);

        Graph spanningTree = Graph.kruskal(graphEdges, verticesGraph);

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

        for (int i = 0; i < QUANTITY_OF_FILES; i++) {


            inputFile = aux;
            inputFile = new StringBuilder(inputFile + "_" + (i + 1));
            StringBuilder file = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFile + ".csv");


            ArrayList<Edge> graphEdges = readFile(file);
            quantityOfEdges.add(graphEdges.size());
            ArrayList<Vertex> verticesGraph = getVerticesGraph(graphEdges);
            long startTime = System.currentTimeMillis();
            Graph spanningTree = Graph.kruskal(graphEdges, verticesGraph);
            long endTime = System.currentTimeMillis();
            long durationTime = ((endTime - startTime));
            time.add((double) durationTime * Math.pow(10, -2));
            System.out.println("File: " + inputFile + ".csv done!");
        }

        asymptoticInfo(quantityOfEdges, time);
        createGnuplotGraph();
    }


    private static Graph Dijkstra(ArrayList<Edge> edges, ArrayList<Vertex> vertices, String startVertex) {

        int[] distances = new int[vertices.size()];
        boolean[] visited = new boolean[vertices.size()];
        Vertex[] previous = new Vertex[vertices.size()];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[vertices.indexOf(new Vertex(startVertex))] = 0;


        for (int i = 0; i < vertices.size(); i++) {

            int u = -1;
            for (int j = 0; j < vertices.size(); j++) {
                if (!visited[j] && (u == -1 || distances[j] < distances[u])) {
                    u = j;
                }
            }

            if (distances[u] == Integer.MAX_VALUE) {
                break;
            }

            visited[u] = true;

            for (Edge edge : edges) {
                if (edge.getOrigin().equals(vertices.get(u))) {
                    int v = vertices.indexOf(edge.getDestiny());
                    int alt = distances[u] + edge.getCost();
                    if (alt < distances[v]) {
                        distances[v] = alt;
                        previous[v] = vertices.get(u);
                    }
                }
            }
        }

        ArrayList<Edge> shortestPathEdges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (previous[i] != null) {
                shortestPathEdges.add(new Edge(previous[i], vertices.get(i), distances[i]));
            }
        }
        Graph graph = new Graph();
        for (Edge edge : shortestPathEdges) {
            graph.addEdge(edge);
        }
        return graph;
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

    private static ArrayList<Vertex> readVertexFile(StringBuilder file) throws FileNotFoundException {
        ArrayList<Vertex> vertices = new ArrayList<>();
        Scanner in = new Scanner(new File(String.valueOf(file)));

        String allVertices = in.nextLine();
        String[] aux = allVertices.split(";");
        for (String vertex : aux) {
            Vertex newVertex = new Vertex(vertex);
            vertices.add(newVertex);
        }

        in.close();
        return vertices;
    }

    private static int[][] readWeightFile(StringBuilder file) throws FileNotFoundException {
        int[][] dimensions = getDimensions(file);
        int[][] weights = new int[dimensions.length][dimensions[0].length];
        Scanner in = new Scanner(new File(String.valueOf(file)));
        int line = 0;
        while (in.hasNextLine()) {
            int columns = 0;
            String[] costs = readLineCosts(in);
            for (String cost : costs) {
                weights[line][columns] = Integer.parseInt(cost);
                columns++;
            }
            line++;
        }

        in.close();
        return weights;
    }

    private static int[][] getDimensions(StringBuilder file) throws FileNotFoundException {
        int lines = 0;
        int columns = 0;
        Scanner in = new Scanner(new File(String.valueOf(file)));
        while (in.hasNextLine()) {
            String[] costs = readLineCosts(in);
            if (costs != null) {
                columns = costs.length;
                lines++;
            }
        }
        return new int[lines][columns];
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

    private static String[] readLineCosts(Scanner in) {
        if (in.hasNextLine()) {
            String line = in.nextLine();
            return line.split(";");
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
        printWriter.println("set ylabel 'Time (seconds)'");
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