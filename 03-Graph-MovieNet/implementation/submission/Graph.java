import java.util.*;

public class Graph<T extends Object> {
    private Map<T, Set<T>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(T v, T w) {
        if (!isVertex(v)) addVertex(v);
        if (!isVertex(w)) addVertex(w);
        if (!isEdge(v, w)) {
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
    }

    public void addVertex(T v) {
        if (!isVertex(v)) graph.put(v, new HashSet<>());
    }

    public void addVertex(T v, Iterable<T> W) {
        if (!W.iterator().hasNext()) addVertex(v);
        else for (T w : W) addEdge(v, w);
    }
    
    public Set<T> Neighbors(T v) {
        if (!isVertex(v)) return new HashSet<>();
        return new HashSet<>(graph.get(v));
    }

    public boolean isVertex(T v) {
        return graph.containsKey(v);
    }

    public boolean isVertex(Iterable<T> V) {
        for (T v : V) if (!isVertex(v)) return false;
        return true;
    }

    public boolean isEdge(T v, T w) {
        if (!isVertex(v) || !isVertex(w)) return false;
        return Neighbors(v).contains(w) || Neighbors(w).contains(v);
    }
}