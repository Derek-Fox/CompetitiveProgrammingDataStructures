import java.util.*;
import DisjointSet.*;
import Graph.*;


public class Main {
    public static void main(String[] args) {
        testConnectedComponents();
        testKruskal();
    }

    private static void testKruskal() {
        Graph<Character> graph2 = new Graph<Character>();
        for (int i = 0; i < 7; i++) {
            graph2.addVertex((char) ('A' + i));
        }

        graph2.addEdge('A', 'B', 7);
        graph2.addEdge('A', 'D', 5);
        graph2.addEdge('D', 'B', 9);
        graph2.addEdge('C', 'B', 8);
        graph2.addEdge('C', 'E', 5);
        graph2.addEdge('D', 'E', 15);
        graph2.addEdge('D', 'F', 6);
        graph2.addEdge('F', 'E', 8);
        graph2.addEdge('F', 'G', 11);
        graph2.addEdge('G', 'E', 9);
        graph2.addEdge('B', 'E', 7);


        System.out.println(kruskal(graph2).getEdges());
    }

    private static void testConnectedComponents() {
        Graph<Integer> graph = new Graph<Integer>();
        for (int i = 0; i < 6; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        List<List<Integer>> components = connectedComponents(graph);
        System.out.println(components);
        System.out.println(components.size());
    }

    private static <T> List<List<T>> connectedComponents(Graph<T> graph) {
        DisjointSet<T> dsu = new DisjointSet<T>();
        for (T i : graph.getVertices()) {
            dsu.makeSet(i);
        }

        for (Edge<T> edge : graph.getEdges()) {
            dsu.union(edge.getSource(), edge.getDest());
        }

        return new ArrayList<>(dsu.getSets().values());
    }

    private static <T> Graph<T> kruskal(Graph<T> graph) {
        Graph<T> mst = new Graph<T>();
        DisjointSet<T> dsu = new DisjointSet<T>();
        for (T vertex : graph.getVertices()) {
            mst.addVertex(vertex);
            dsu.makeSet(vertex);
        }
        List<Edge<T>> edges = graph.getEdges();
        edges.sort(Comparator.naturalOrder());
        for (Edge<T> edge : edges) {
            T u = edge.getSource();
            T v = edge.getDest();
            if (!dsu.sameSet(u, v)) {
                mst.addEdge(u, v, edge.getWeight());
                dsu.union(u, v);
            }
        }
        return mst;
    }

}