import java.util.*;

public class PathFinder<T extends Object> {
    private Map<T, Set<T>> prev = new HashMap<>(); // previous nodes in shortest paths
    private Map<T, Integer> dist = new HashMap<>(); // visited (shortest path)

    public PathFinder(Graph<T> G, T s) {
        Deque<T> queue = new ArrayDeque<>();
        queue.offer(s);
        dist.put(s, 0);
        while (!queue.isEmpty()) {
            T v = queue.poll();
            for (T w : G.Neighbors(v)) {
                if (!dist.containsKey(w)) {
                    dist.put(w, dist.get(v) + 1);
                    prev.put(w, new HashSet<>(Arrays.asList(v)));
                    queue.offer(w);
                }
                else {
                    if (dist.get(v) + 1 < dist.get(w)) {
                        dist.replace(w, dist.get(v) + 1);
                        prev.replace(w, new HashSet<>(Arrays.asList(v)));
                    }
                    else if (dist.get(v) + 1 == dist.get(w)) {
                        prev.get(w).add(v);
                    }
                }
            }
        }
    }

    public boolean pathExistsTo(T v) {
        return dist.containsKey(v);
    }

    public Set<T> pathExistsAll() {
        return new HashSet<>(dist.keySet());
    }

    public Integer distanceTo(T v) {
        if (!pathExistsTo(v)) return Integer.MAX_VALUE;
        return dist.get(v);
    }

    public Set<LinkedList<T>> pathsTo(T v) {
        Set<LinkedList<T>> results = new HashSet<>();
        if (!pathExistsTo(v)) return results;

        T head = v;
        LinkedList<T> path = new LinkedList<>(Arrays.asList(head));
        Deque<LinkedList<T>> stack = new ArrayDeque<>();
        stack.push(path);
        while (!stack.isEmpty()) {
            path = stack.pop();
            head = path.peek();
            if (prev.containsKey(head)) {
                for (T w : prev.get(head)) {
                    LinkedList<T> wPath = new LinkedList<>(path);
                    wPath.push(w);
                    stack.push(wPath);
                }
            }
            else results.add(path);
        }
        return results;
    }
 }