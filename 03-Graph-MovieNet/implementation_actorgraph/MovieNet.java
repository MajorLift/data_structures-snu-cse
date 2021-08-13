/*
 * Six Degrees of Kevin Bacon
 *
 * Bongki Moon (bkmoon@snu.ac.kr), Oct/23/2014.
*/

import java.lang.*;
import java.util.*; 
import java.io.*;

public class MovieNet {
  static final String KevinBacon = "Bacon, Kevin";
  private Graph<String> movieGraph = new Graph<>();
  private Graph<String> actorGraph = new Graph<>();
  // private Set<String> movieList = new HashSet<>();
  // private Set<String> actorList = new HashSet<>();

  // Each instance of movielines is String[] such that
  //	String[0] = title of movie
  //	String[1..n-1] = list of actors
  // Constructor
  public MovieNet(LinkedList<String[]> movielines) {
    for (String[] movieline : movielines) {
      String movie = movieline[0];
      Set<String> cast = new HashSet<>(Arrays.asList(Arrays.copyOfRange(movieline, 1, movieline.length)));
      // this.movieList.add(movie);
      // this.actorList.addAll(cast);
      this.movieGraph.addVertex(movie, cast);
      String[] ccast = new String[cast.size()];
      ccast = cast.toArray(ccast);
      this.actorGraph.addVertex(ccast);
    }
  }

  private boolean checkInputAllVertices(String[] S) {
    if (S == null) throw new IllegalArgumentException("null input.");
    if (!movieGraph.isVertex(Arrays.asList(S))) return false;
    return true;
  }

/*============================================================================*/

  // [Q1] 
  // @returns a list of movies co-starred by the input actors 
  // or null if there is no such movie.
  public String[] moviesby(String[] actors) {
    if (actors.length == 0 || !checkInputAllVertices(actors)) return null;
    Set<String> movies = new HashSet<>();
    for (String actor : actors) {
      Set<String> filmo = movieGraph.Neighbors(actor);
      if (movies.isEmpty()) movies = filmo;
      else {
        movies.retainAll(filmo);
        if (movies.isEmpty()) return null;
      }
    }
    String[] result = new String[movies.size()];
    result = movies.toArray(result);
    return result;
  }

  // [Q2] 
  // @returns a list of actors who were cast in all the movies 
  // or null if there is no such actor.
  public String[] castin(String[] titles) {
    if (titles.length == 0 || !checkInputAllVertices(titles)) return null;
    Set<String> actors = new HashSet<>();
    for (String movie : titles) {
      Set<String> cast = movieGraph.Neighbors(movie);
      if (actors.isEmpty()) actors = cast;
      else {
        actors.retainAll(cast);
        if (actors.isEmpty()) return null; 
      }
    }
    String[] result = new String[actors.size()];
    result = actors.toArray(result);
    return result;
  }

  // [Q3]
  // @returns a pair of actors who starred together most often 
  // or null if there is no such pair.
  public String[] pairmost(String[] actors) {
    if (actors.length == 0 || !checkInputAllVertices(actors)) return null;
    int max = 0;
    String[] result = new String[2];
    for (int i = 0; i < actors.length - 1; i++) {
      Set<String> filmoI = movieGraph.Neighbors(actors[i]);
      for (int j = i + 1; j < actors.length; j++) {
        Set<String> filmoJ = movieGraph.Neighbors(actors[j]);
        Set<String> coFilmo = new HashSet<>(filmoI);
        coFilmo.retainAll(filmoJ);
        if (coFilmo.size() > max) {
          max = coFilmo.size();
          result = new String[]{actors[i], actors[j]};
        }
      }
    }
    if (max > 0) return result;
    else return null;
  }

  // [Q4]
  // @returns the Bacon number of the @param actor.
  // @returns -1 if the actor is not reachable from Kevin Bacon, 
  // the actor is not found, or Kevin Bacon himself is not found in the MovieNet database. 
  // Two actors are said to be reachable from each other 
  // if they can be connected via one or more collaboration links.
  public int Bacon(String actor) {
    PathFinder<String> BaconPath = new PathFinder<>(this.actorGraph, KevinBacon);
    if (!actorGraph.isVertex(Arrays.asList(actor,KevinBacon)) || !BaconPath.pathExistsTo(actor)) return -1;
    return BaconPath.distanceTo(actor);
  }

  // [Q5]
  // @returns the shortest collaboration distance between the @params two actors. 
  // @returns -1 if the two actors are not reachable from each other, 
  // or either of the actors are not found in the MovieNet database.
  public int distance(String src, String dst) {
    PathFinder<String> srcPath = new PathFinder<>(this.actorGraph, src);
    if (!actorGraph.isVertex(Arrays.asList(src,dst)) || !srcPath.pathExistsTo(dst)) return -1;
    return srcPath.distanceTo(dst);
  }

  // [Q6]
  // @returns the number of distinct shortest collaboration paths between the @params two actors.
  // @returns 0 if there exists no path between the actors 
  // (i.e., they are not reachable from each other), 
  // or either of the actors are not found in the MovieNet database.
  public int npath(String src, String dst) {
    PathFinder<String> srcPath = new PathFinder<>(this.actorGraph, src);
    if (!actorGraph.isVertex(Arrays.asList(src,dst)) || !srcPath.pathExistsTo(dst)) return 0;
    return srcPath.pathsTo(dst).size();
    // Set<LinkedList<String>> paths = srcPath.pathsTo(dst);
    // Set<LinkedList<String>> prunedPaths = new HashSet<>();
    // for (LinkedList<String> path : paths) {
    //   path.retainAll(this.actorList);
    //   prunedPaths.add(path);
    // }
    // return prunedPaths.size();
  }

  // [Q7]
  // @returns a shortest collaboration path between the two @params actors. 
  // @returns null if there exists no path between the actors 
  // (i.e., they are not reachable from each other),
  // or either of the actors are not found in the MovieNet database. 
  // Recall that more than one shortest path may exist between two actors.
  public String[] apath(String src, String dst) {
    PathFinder<String> srcPath = new PathFinder<>(this.actorGraph, src);
    if (!actorGraph.isVertex(Arrays.asList(src,dst)) || !srcPath.pathExistsTo(dst)) return null;
    Deque<String> shortestPath = srcPath.pathsTo(dst).iterator().next();
    int pathLen = shortestPath.size() + 1;
    String[] result = new String[pathLen];
    for (int i = 0; i < pathLen; i++) {
      result[i] = shortestPath.poll();
      if (!shortestPath.isEmpty()) shortestPath.poll();
    }
    return result;
  }

  // [Q8]
  // @returns the eccentricity measure of an actor, 
  // which is defined as the maximum of all shortest collaboration distances 
  // from the @param actor to the other reachable actors.
  public int eccentricity(String actor) {
    PathFinder<String> actorPath = new PathFinder<>(this.actorGraph, actor);
    int max = 0;
    for (String t : actorPath.pathExistsAll()) {
      if (actorPath.pathExistsTo(t)) {
        int k = actorPath.distanceTo(t);
        if (k > max) max = k;
      }
    }
    return max;
  }

  // [Q9]
  // @returns Dangalchev’s closeness centrality of the @param actor.
  // closeness(s) := sum_(s!=t) 1/(2^d(s,t))
  // where d(s,t) = shortest collaboration distance between actors s and t.
  // d(s,t) = \infty if t is unreachable from s.
  public float closeness(String actor) {
    PathFinder<String> actorPath = new PathFinder<>(this.actorGraph, actor);    
    float result = 0;
    for (String t : actorPath.pathExistsAll()) {
      if (actorPath.pathExistsTo(t)) {
        result += Math.pow(2, -actorPath.distanceTo(t));
      }
    }
    return result;
  }

/*============================================================================*/

}

