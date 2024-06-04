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


        } while (option < 1 || option > 4);
    }

    private static void US18() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputVerticesFile = new StringBuilder(getFileVertices(sc));
        StringBuilder inputFileWeights = new StringBuilder(getFileWeight(sc));
        StringBuilder fileWeights = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFileWeights + ".csv");
        StringBuilder fileVertices = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputVerticesFile + ".csv");

        ArrayList<Vertex> vertices = readVertexFile(fileVertices);
        for (Vertex vertex : vertices) {
            String sanitizedVertexName = sanitizeVertexName(vertex.getV());
            vertex.setV(sanitizedVertexName);
        }
        int[][] weights = readWeightFile(fileWeights);
        ArrayList<Edge> graphEdges = new ArrayList<>();
        makeEdges(graphEdges, vertices, weights);
        Graph graph = addEdges(graphEdges);
        createGraph(graph, inputVerticesFile);
        ArrayList<String> MPList = new ArrayList<>();
        sortMP(MPList, vertices);

        if (MPList.isEmpty()) {
            System.out.println("There is no Meeting Point in the file!");
        } else {
            List<Graph> evacuationRoutes;
            System.out.println("Insert the vertex you want to know the shortest path to the AP or 'done'(if you want to stop):");
            String vertex = sc.nextLine();
            while (!vertex.equalsIgnoreCase("done")) {
                if (!vertex.equalsIgnoreCase("done")) {
                    if (isPartOfVertices(vertices, vertex)) {
                        System.out.println("Vertex not found!");
                        System.out.println("Please insert a valid vertex that you want to know the shortest path to the AP or 'done'(if you want to stop):");
                    } else {
                        evacuationRoutes = DijkstraUS18(graphEdges, vertex, MPList);
                        List<Integer> totalCosts = new ArrayList<>();
                        for (Graph route : evacuationRoutes) {
                            totalCosts.add(route.getTotalCost());
                        }
                        bubbleSortCosts(totalCosts, evacuationRoutes);
                        Graph route = evacuationRoutes.get(0);
                        makeGraphCsv(route, vertex);
                        createGraphDisktra(graph, inputVerticesFile, route.getEdges(), vertex);
                        System.out.println("Insert the vertex you want to know the shortest path to the AP or 'done'(if you want to stop):");

                    }
                }
                vertex = sc.nextLine();

            }
        }


    }

    private static boolean isPartOfVertices(ArrayList<Vertex> vertices, String startVertex) {
        for (Vertex vertex : vertices) {
            if (vertex.getV().equalsIgnoreCase(startVertex)) {
                return false;
            }
        }
        return true;
    }

    private static void bubbleSortCosts(List<Integer> totalCosts, List<Graph> evacuationRoutes) {
        int n = totalCosts.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (totalCosts.get(j) > totalCosts.get(j + 1)) {
                    int temp = totalCosts.get(j);
                    Graph tempGraph = evacuationRoutes.get(j);
                    evacuationRoutes.set(j, evacuationRoutes.get(j + 1));
                    totalCosts.set(j, totalCosts.get(j + 1));
                    evacuationRoutes.set(j + 1, tempGraph);
                    totalCosts.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortMP(ArrayList<String> AP, ArrayList<Vertex> vertices) {

        for (Vertex vertex : vertices) {
            if (vertex.getV().contains("AP")) {
                AP.add(vertex.getV());
            }
        }
    }


    private static void US17() throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        StringBuilder inputVerticesFile = new StringBuilder(getFileVertices(sc));
        StringBuilder inputFileWeights = new StringBuilder(getFileWeight(sc));
        StringBuilder fileWeights = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputFileWeights + ".csv");
        StringBuilder fileVertices = new StringBuilder("src/main/java/PI_MDISC_Group_072/Input/" + inputVerticesFile + ".csv");

        ArrayList<Vertex> vertices = readVertexFile(fileVertices);
        for (Vertex vertex : vertices) {
            String sanitizedVertexName = sanitizeVertexName(vertex.getV());
            vertex.setV(sanitizedVertexName);
        }
        int[][] weights = readWeightFile(fileWeights);
        ArrayList<Edge> graphEdges = new ArrayList<>();
        makeEdges(graphEdges, vertices, weights);
        Graph graph = addEdges(graphEdges);
        createGraph(graph, inputVerticesFile);
        String MP = getMP(vertices);
        List<String> MPList = new ArrayList<>();

        for (Vertex vertex : vertices) {
            System.out.println(vertex.getV());
        }
        if (MP != null) {
            MPList.add(MP);
        }
        if (MPList.isEmpty()) {
            System.out.println("There is no Meeting Point in the file!");
        } else {
            List<Graph> evacuationRoutes;
            System.out.println("Insert the vertex you want to know the shortest path to the AP or 'done'(if you want to stop):");
            String vertex = sc.nextLine();
            while (!vertex.equalsIgnoreCase("done")) {
                if (!vertex.equalsIgnoreCase("done")) {
                    if (isPartOfVertices(vertices, vertex)) {
                        System.out.println("Vertex not found!");
                        System.out.println("Please insert a valid vertex that you want to know the shortest path to the AP or 'done'(if you want to stop):");
                    } else {
                        evacuationRoutes = Dijkstra(graphEdges, vertex, MPList);
                        for (Graph route : evacuationRoutes) {
                            makeGraphCsv(route, vertex);
                            createGraphDisktra(graph, inputVerticesFile, route.getEdges(), vertex);
                        }
                        System.out.println("Insert the vertex you want to know the shortest path to the AP or 'done'(if you want to stop):");

                    }
                }
                vertex = sc.nextLine();
            }
        }

    }
    public static String sanitizeVertexName(String inputName) {
        return inputName.replace("\uFEFF", "");
    }

    public static List<Graph> Dijkstra(ArrayList<Edge> edges, String start, List<String> MPs) {
        List<Graph> shortestPaths = new ArrayList<>();
        for (String MP : MPs) {
            if (MP.equals(start)) {
                System.out.println("The vertex is already the Meeting Point!");
            } else {
                Graph initialGraph = new Graph();
                for (Edge edge : edges) {
                    initialGraph.addEdge(edge);
                }
                List<Vertex> vertices = initialGraph.getVertices();
                int numVertices = vertices.size();
                int[] dist = new int[numVertices];
                Vertex[] prev = new Vertex[numVertices];
                boolean[] visited = new boolean[numVertices];

                // Initialize distances to -1, and visited to false
                for (int i = 0; i < numVertices; i++) {
                    dist[i] = -1;
                    prev[i] = null;
                    visited[i] = false;
                }

                PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> dist[vertices.indexOf(v)]));

                Vertex MPVertex = new Vertex(MP);
                Vertex startVertex = new Vertex(start);
                dist[vertices.indexOf(startVertex)] = 0;
                queue.add(startVertex);

                int[] oldDist = dist.clone();

                while (!queue.isEmpty()) {
                    Vertex u = queue.poll();
                    int uIndex = vertices.indexOf(u);
                    visited[uIndex] = true;

                    List<Vertex> neighbors = initialGraph.getVerticesConnectedTo(u);
                    for (Vertex neighbor : neighbors) {
                        int vIndex = vertices.indexOf(neighbor);
                        int weight = initialGraph.getEdgeCost(u, neighbor);

                        if (!visited[vIndex] && dist[uIndex] != -1 && (dist[vIndex] == -1 || dist[uIndex] + weight < dist[vIndex])) {
                            queue.remove(neighbor);
                            dist[vIndex] = dist[uIndex] + weight;
                            prev[vIndex] = u;
                            queue.add(neighbor); // Add it back to re-sort the queue
                        }
                    }
                }

                List<Vertex> path = new ArrayList<>();
                for (Vertex at = MPVertex; at != null; at = prev[vertices.indexOf(at)]) {
                    path.add(at);
                }
                Collections.reverse(path);

                Graph shortestPath = new Graph();
                for (int i = 0; i < path.size() - 1; i++) {
                    Vertex origin = path.get(i);
                    Vertex destiny = path.get(i + 1);
                    int cost = initialGraph.getEdgeCost(origin, destiny);
                    shortestPath.addEdge(new Edge(origin, destiny, cost));
                }

                shortestPaths.add(shortestPath);
            }
        }
        return shortestPaths;
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

    private static void createGraph(Graph graph, StringBuilder inputFile) throws IOException, InterruptedException {
        createScriptGraph(graph, inputFile);
        executeScriptGraph(inputFile);
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


    public static List<Graph> DijkstraUS18(ArrayList<Edge> edges, String start, List<String> MPs) {
        List<Graph> shortestPaths = new ArrayList<>();
        for (String MP : MPs) {
            if (MP.equals(start)) {
                System.out.println("The vertex is already the Meeting Point!");
            } else {
                Graph initialGraph = new Graph();
                for (Edge edge : edges) {
                    initialGraph.addEdge(edge);
                }
                List<Vertex> vertices = initialGraph.getVertices();
                int numVertices = vertices.size();
                int[] dist = new int[numVertices];
                Vertex[] prev = new Vertex[numVertices];
                boolean[] visited = new boolean[numVertices];

                // Initialize distances to -1, and visited to false
                for (int i = 0; i < numVertices; i++) {
                    dist[i] = -1;
                    prev[i] = null;
                    visited[i] = false;
                }

                PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> dist[vertices.indexOf(v)]));

                Vertex MPVertex = new Vertex(MP);
                Vertex startVertex = new Vertex(start);
                dist[vertices.indexOf(startVertex)] = 0;
                queue.add(startVertex);

                int[] oldDist = dist.clone();

                while (!queue.isEmpty()) {
                    Vertex u = queue.poll();
                    int uIndex = vertices.indexOf(u);
                    visited[uIndex] = true;

                    List<Vertex> neighbors = initialGraph.getVerticesConnectedTo(u);
                    for (Vertex neighbor : neighbors) {
                        int vIndex = vertices.indexOf(neighbor);
                        int weight = initialGraph.getEdgeCost(u, neighbor);

                        if (!visited[vIndex] && dist[uIndex] != -1 && (dist[vIndex] == -1 || dist[uIndex] + weight < dist[vIndex])) {
                            queue.remove(neighbor);
                            dist[vIndex] = dist[uIndex] + weight;
                            prev[vIndex] = u;
                            queue.add(neighbor); // Add it back to re-sort the queue
                        }
                    }
                }

                List<Vertex> path = new ArrayList<>();
                for (Vertex at = MPVertex; at != null; at = prev[vertices.indexOf(at)]) {
                    path.add(at);
                }
                Collections.reverse(path);

                Graph shortestPath = new Graph();
                for (int i = 0; i < path.size() - 1; i++) {
                    Vertex origin = path.get(i);
                    Vertex destiny = path.get(i + 1);
                    int cost = initialGraph.getEdgeCost(origin, destiny);
                    shortestPath.addEdge(new Edge(origin, destiny, cost));
                }

                shortestPaths.add(shortestPath);
            }
        }
        return shortestPaths;
    }

    private static int minDistance(int[] dist, boolean[] visited, List<Vertex> vertices) {
        int min = -1, minIndex = -1;

        for (int v = 0; v < vertices.size(); v++) {

            if (!visited[v] && dist[v] >= min) {
                min = dist[v];
                minIndex = v;
            }

        }
        return minIndex;
    }


    private static void createGraphDisktra(Graph graph, StringBuilder file, List<Edge> shortestPath, String vertex) throws IOException, InterruptedException {
        createScriptGraphDisktra(graph, file, shortestPath);
        executeScriptGraphDijkstra(file, vertex);
    }

    private static void createScriptGraphDisktra(Graph graph, StringBuilder file, List<Edge> shortestPath) {
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
                String edgeColor = "black";
                for (Edge edge1 : shortestPath) {
                    if (edge.equals(edge1)) {
                        edgeColor = "red";
                        break;
                    }
                }
                writer.printf("    %s -- %s [label=\"%d\", color=\"%s\"];%n", origin, destiny, cost, edgeColor);
            }

            writer.println("}");
            System.out.println("DOT file 'src/main/java/PI_MDISC_Group_072/Output/Graph.dot' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to DOT file: " + e.getMessage());
        }

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

    private static void executeScriptGraphDijkstra(StringBuilder file, String vertex) throws IOException, InterruptedException {
        String caminhoScript = "src/main/java/PI_MDISC_Group_072/Output/Graph_" + file;
        String commandGraphviz = "dot -Tsvg " + caminhoScript + ".dot -o " + caminhoScript + "_" + vertex + ".svg";
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

    private static void makeGraphCsv(Graph graph, String vertex) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/PI_MDISC_Group_072/Output/graph_" + vertex + ".csv"))) {
            int totalCost = 0;
            for (Edge edge : graph.getEdges()) {
                String origin = edge.getOrigin().getV();
                String destiny = edge.getDestiny().getV();
                int cost = edge.getCost();
                String edgeString = String.format("    %s -- %s cost : %d;", origin, destiny, cost);
                totalCost += cost;
                writer.println(edgeString);
            }
            writer.println("Total cost of making path: " + totalCost);
            System.out.println("CSV file 'src/main/java/PI_MDISC_Group_072/Output/graph_" + vertex + ".csv' has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private static String getFile(Scanner sc) {
        System.out.println("Insert the name of the file with the vertices, costs, and connections:");
        return sc.nextLine();
    }

    private static String getFileWeight(Scanner sc) {
        System.out.println("Insert the name of the file with the costs for each vertex and connection:");
        return sc.nextLine();
    }

    private static String getFileVertices(Scanner sc) {
        System.out.println("Insert the name of the file with the vertices:");
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
        int vertexPosition = 0;
        while (in.hasNextLine()) {
            int columns = 0;
            String[] costs = readLineCosts(in);
            for (String cost : costs) {
                if (columns > vertexPosition) {
                    weights[line][columns] = Integer.parseInt(cost);
                }
                columns++;
            }
            vertexPosition++;
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