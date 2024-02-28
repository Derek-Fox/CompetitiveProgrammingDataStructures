import java.util.*;

/**
 * Class to represent a disjoint set data structure.
 * Supports makeSet, findSet, union operations.
 * Uses union by rank and path compression optimizations.
 * @param <T> type of the unique labels for values in the set
 */
public class DisjointSet<T> {
    private int numSets;
    private final Map<T, Node<T>> nodes; //value -> node obj

    public DisjointSet() {
        nodes = new HashMap<>();
        numSets = 0;
    }

    /**
     * Get the number of sets in the disjoint set
     * @return number of sets
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Create a new set with a single element.
     * Throws IAE if the value already exists in the set.
     * @param value unique label for the element
     */
    public void makeSet(T value) {
        if (nodes.containsKey(value)) {
            throw new IllegalArgumentException("Node values must be distinct.");
        }
        nodes.put(value, new Node<>(value));
        numSets++;
    }

    /**
     * Find the representative of the set that contains the value.
     * @param value value to find the set of
     * @return representative of the set
     */
    public T findSet(T value) {
        Node<T> x = nodes.get(value);
        return findSet(x).label;
    }

    private Node<T> findSet(Node<T> node) {
        if (!node.equals(node.parent)) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }

    /**
     * Check if two values are in the same set.
     * @param label1 first value
     * @param label2 second value
     * @return true if in the same set, false otherwise
     */
    public boolean sameSet(T label1, T label2) {
        Node<T> x = nodes.get(label1);
        Node<T> y = nodes.get(label2);
        return findSet(x).equals(findSet(y));
    }

    /**
     * Merge the two sets of each argument.
     * @param label1 first value
     * @param label2 second value
     */
    public void union(T label1, T label2) {
        Node<T> x = nodes.get(label1);
        Node<T> y = nodes.get(label2);

        x = findSet(x);
        y = findSet(y);

        link(x, y);
        numSets--;
    }

    private void link(Node<T> x, Node<T> y) {
        if (x.rank > y.rank) {
            y.parent = x;
        } else {
            x.parent = y;
            if (x.rank == y.rank) y.rank++;
        }
    }

    /**
     * Get the sets in the disjoint set.
     * @return map of representative to list of elements in the set
     */
    public Map<T, List<T>> getSets() {
        Map<T, List<T>> sets = new HashMap<>();
        for (Node<T> node : nodes.values()) {
            Node<T> parent = findSet(node);
            if (!sets.containsKey(parent.label)) {
                sets.put(parent.label, new ArrayList<>());
            }
            sets.get(parent.label).add(node.label);
        }
        return sets;
    }

    /**
     * Print the sets in the disjoint set.
     */
    public void printSets() {
        Map<T, List<T>> sets = getSets();
        int i = 1;
        for (Map.Entry<T, List<T>> entry : sets.entrySet()) {
            System.out.println("Set " + i++ + ": " + entry.getValue());
        }
    }
}

class Node<T> {
    final T label;
    Node<T> parent;
    int rank;

    public Node(T label) {
        this.label = label;
        parent = this;
        rank = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return rank == node.rank && Objects.equals(label, node.label) && Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, parent, rank);
    }
}