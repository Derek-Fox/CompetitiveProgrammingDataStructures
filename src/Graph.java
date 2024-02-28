import java.util.*;

/**
 * Class to represent a graph.
 * Supports both weighted and unweighted edges.
 *
 * @param <T> type of label for nodes
 */
public class Graph<T> {
    private int numNodes;
    private int numEdges;
    private int sumOfWeights;
    private final Map<T, List<Edge<T>>> adjList;
    private final List<Edge<T>> edges;

    public Graph() {
        adjList = new HashMap<>();
        edges = new ArrayList<>();
    }

    /**
     * Add a vertex to the graph
     *
     * @param label unique identifier for the vertex
     */
    public void addVertex(T label) {
        if (adjList.containsKey(label)) {
            throw new IllegalArgumentException("Vertex already exists in the graph!");
        }
        adjList.put(label, new ArrayList<>());
        numNodes++;
    }

    /**
     * Add a weighted edge between two vertices
     *
     * @param u          first vertex
     * @param v          second vertex
     * @param weight     weight of edge
     * @param isDirected true if the edge is directed, false otherwise
     */
    public void addEdge(T u, T v, int weight, boolean isDirected) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) {
            throw new IllegalArgumentException("Both nodes must exist in the graph to create an edge!");
        }

        Edge<T> edge = new Edge<>(u, v, weight);
        adjList.get(u).add(edge);
        if (!isDirected) {
            adjList.get(v).add(edge.reversed());
        }
        edges.add(edge);
        numEdges++;
        sumOfWeights += weight;
    }

    /**
     * Add an undirected, unweighted edge between two vertices
     *
     * @param u first vertex
     * @param v second vertex
     */
    public void addEdge(T u, T v) {
        addEdge(u, v, 1, false);
    }

    /**
     * Add an unweighted edge between two vertices
     *
     * @param u          first vertex
     * @param v          second vertex
     * @param isDirected true if the edge is directed, false otherwise
     */
    public void addEdge(T u, T v, boolean isDirected) {
        addEdge(u, v, 1, isDirected);
    }

    /**
     * Add an undirected, weighted edge between two vertices
     *
     * @param u      first vertex
     * @param v      second vertex
     * @param weight weight of edge
     */
    public void addEdge(T u, T v, int weight) {
        addEdge(u, v, weight, false);
    }

    /**
     * Get the neighbors of a vertex
     *
     * @param node vertex to get neighbors of
     * @return list of neighbors
     */
    public List<Edge<T>> getNeighbors(T node) {
        return adjList.get(node);
    }

    /**
     * Get all the vertices in the graph
     *
     * @return set of all vertices
     */
    public Set<T> getVertices() {
        return adjList.keySet();
    }

    /**
     * Get all the edges in the graph
     *
     * @return list of all edges
     */
    public List<Edge<T>> getEdges() {
        return edges;
    }

    /**
     * Get the number of nodes in the graph
     *
     * @return number of nodes
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Get the number of edges in the graph
     *
     * @return number of edges
     */
    public int getNumEdges() {
        return numEdges;
    }

    /**
     * Get the sum of weights of all edges in the graph.
     * Note, for unweighted graphs, this will be the same as the number of edges.
     *
     * @return sum of weights
     */
    public int getSumOfWeights() {
        return sumOfWeights;
    }

}


