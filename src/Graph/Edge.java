package Graph;

/**
 * Graph.Edge class to represent an edge in a graph
 * @param <T> type of the label for the vertex
 */
public class Edge<T> implements Comparable<Edge<T>> {
    private final T srcLabel;
    private final T dstLabel;
    private final int weight;

    public Edge(T srcLabel, T dstLabel, int weight) {
        this.srcLabel = srcLabel;
        this.dstLabel = dstLabel;
        this.weight = weight;
    }

    public Edge(T srcLabel, T dstLabel) {
        this.srcLabel = srcLabel;
        this.dstLabel = dstLabel;
        this.weight = 1;
    }

    public Edge<T> reversed() {
        return new Edge<>(dstLabel, srcLabel, weight);
    }

    @Override
    public int compareTo(Edge<T> o) {
        return weight - o.weight;
    }

    @Override
    public String toString() {
        return "Graph.Edge{" +
                "src=" + srcLabel +
                ", dst=" + dstLabel +
                '}';
    }

    public T getSource() {
        return srcLabel;
    }

    public T getDest() {
        return dstLabel;
    }

    public int getWeight() {
        return weight;
    }
}
