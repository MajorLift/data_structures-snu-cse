package submission;
import java.util.*;

import Graph;

// dynamic traversal for shortest paths of movie-actor graph. 
// returns results for pruned actor-actor graph.
public class ActorPathFinder<T extends Object> {
    private Map<T, Set<T>> prev = new HashMap<>(); // previous nodes in shortest paths
    private Map<T, Integer> dist = new HashMap<>(); // visited (shortest path)
    private Set<T> Movies;

    // queue-based BFS implementation
    public ActorPathFinder(Graph<T> G, T s, Set<T> Movies) {
        this.Movies = new HashSet<>(Movies);
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
        if (this.Movies.contains(v)) return false;
        return dist.containsKey(v);
    }

    public Set<T> pathExistsAll() {
        Set<T> result = new HashSet<>(dist.keySet());
        result.removeAll(this.Movies);
        return result;
    }

    public Integer distanceTo(T v) {
        if (!pathExistsTo(v)) return Integer.MAX_VALUE;
        return dist.get(v) / 2;
    }

    // stack-based DFS implementation.
    public Set<LinkedList<T>> pathsTo(T v) {
        Set<LinkedList<T>> results = new HashSet<>();
        if (!pathExistsTo(v)) return results;

        T head = v;
        Deque<T> path = new ArrayDeque<>(Arrays.asList(head));
        Deque<Deque<T>> stack = new ArrayDeque<>();
        stack.push(path);
        while (!stack.isEmpty()) {
            path = stack.pop();
            head = path.peek();
            if (prev.containsKey(head)) {
                for (T w : prev.get(head)) {
                    Deque<T> wPath = new ArrayDeque<>(path);
                    wPath.push(w);
                    stack.push(wPath);
                }
            }
            else {
                path.removeAll(this.Movies); // prune into actor-actor graph
                results.add(new LinkedList<>(path)); // equals method not implemented for Deque interface, 
                                                    // but elements, size, and order are checked for Lists.
            }
        }
        return results;
    }
 }