package submission;
/*
 * Six Degrees of Kevin Bacon
 *
 * Bongki Moon (bkmoon@snu.ac.kr), Oct/23/2014.
*/

import java.lang.*;
import java.util.*;

import Graph;

import java.io.*;

public class MovieNet {
  static final String KevinBacon = "Bacon, Kevin";
  private Graph<String> graph = new Graph<>();
  private Set<String> Movies = new HashSet<>();

  // Each instance of movielines is String[] such that
  //	String[0] = title of movie
  //	String[1..n-1] = list of actors
  // Constructor
  public MovieNet(LinkedList<String[]> movielines) {
    for (String[] line : movielines) {
      String movie = line[0];
      Set<String> cast = new HashSet<>(Arrays.asList(Arrays.copyOfRange(line, 1, line.length)));
      this.Movies.add(movie);
      this.graph.addVertex(movie, cast);
    }
  }

  private boolean checkInputAllVertices(String[] S) {
    if (S == null) throw new IllegalArgumentException("null input.");
    if (!this.graph.isVertex(Arrays.asList(S))) return false;
    return true;
  }

/*============================================================================*/

  // [Q1] 
  // @returns a list of movies co-starred by the input actors 
  // or null if there is no such movie.
  public String[] moviesby(String[] actors) {
    if (actors.length == 0 || !checkInputAllVertices(actors)) return null;
    Set<String> coFilmo = this.graph.Neighbors(actors[0]);
    for (String actor : Arrays.copyOfRange(actors, 1, actors.length)) {
        Set<String> actorFilmo = this.graph.Neighbors(actor);
        coFilmo.retainAll(actorFilmo);
        if (coFilmo.isEmpty()) return null;
    }
    String[] result = new String[coFilmo.size()];
    result = coFilmo.toArray(result);
    return result;
  }

  // [Q2] 
  // @returns a list of actors who were cast in all the movies 
  // or null if there is no such actor.
  public String[] castin(String[] titles) {
    if (titles.length == 0 || !checkInputAllVertices(titles)) return null;
    Set<String> coCast = this.graph.Neighbors(titles[0]);
    for (String title : Arrays.copyOfRange(titles, 1, titles.length)) {
        Set<String> cast = this.graph.Neighbors(title);
        coCast.retainAll(cast);
        if (coCast.isEmpty()) return null; 
    }
    String[] result = new String[coCast.size()];
    result = coCast.toArray(result);
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
      String actorI = actors[i];
      Set<String> filmoI = this.graph.Neighbors(actorI);
      for (int j = i + 1; j < actors.length; j++) {
        String actorJ = actors[j];
        Set<String> filmoJ = this.graph.Neighbors(actorJ);
        Set<String> coFilmo = new HashSet<>(filmoI);
        coFilmo.retainAll(filmoJ);
        int k = coFilmo.size();
        if (k > max) {
          max = k;
          result[0] = actorI;
          result[1] = actorJ;
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
    ActorPathFinder<String> traversals = new ActorPathFinder<>(this.graph, KevinBacon, this.Movies);
    if (!this.graph.isVertex(Arrays.asList(actor,KevinBacon)) || !traversals.pathExistsTo(actor)) return -1;
    return traversals.distanceTo(actor);
  }

  // [Q5]
  // @returns the shortest collaboration distance between the @params two actors. 
  // @returns -1 if the two actors are not reachable from each other, 
  // or either of the actors are not found in the MovieNet database.
  public int distance(String src, String dst) {
    ActorPathFinder<String> traversals = new ActorPathFinder<>(this.graph, src, this.Movies);
    if (!this.graph.isVertex(Arrays.asList(src,dst)) || !traversals.pathExistsTo(dst)) return -1;
    return traversals.distanceTo(dst);
  }

  // [Q6]
  // @returns the number of distinct shortest collaboration paths between the @params two actors.
  // @returns 0 if there exists no path between the actors 
  // (i.e., they are not reachable from each other), 
  // or either of the actors are not found in the MovieNet database.
  public int npath(String src, String dst) {
    ActorPathFinder<String> traversals = new ActorPathFinder<>(this.graph, src, this.Movies);
    if (!this.graph.isVertex(Arrays.asList(src,dst)) || !traversals.pathExistsTo(dst)) return 0;
    return traversals.pathsTo(dst).size();
  }

  // [Q7]
  // @returns a shortest collaboration path between the two @params actors. 
  // @returns null if there exists no path between the actors 
  // (i.e., they are not reachable from each other),
  // or either of the actors are not found in the MovieNet database. 
  // Recall that more than one shortest path may exist between two actors.
  public String[] apath(String src, String dst) {
    ActorPathFinder<String> traversals = new ActorPathFinder<>(this.graph, src, this.Movies);
    if (!this.graph.isVertex(Arrays.asList(src,dst)) || !traversals.pathExistsTo(dst)) return null;
    LinkedList<String> path = traversals.pathsTo(dst).iterator().next();
    int pathLen = path.size();
    String[] result = new String[pathLen];
    result = path.toArray(result);
    return result;
  }

  // [Q8]
  // @returns the eccentricity measure of an actor, 
  // which is defined as the maximum of all shortest collaboration distances 
  // from the @param actor to the other reachable actors.
  public int eccentricity(String actor) {
    ActorPathFinder<String> traversals = new ActorPathFinder<>(this.graph, actor, this.Movies);
    int max = 0;
    Set<String> actors = traversals.pathExistsAll();
    for (String t : actors) {
      int k = traversals.distanceTo(t);
      if (k > max) max = k;
    }
    return max;
  }

  // [Q9]
  // @returns Dangalchev’s closeness centrality of the @param actor.
  // closeness(s) := sum_(s!=t) 1/(2^d(s,t))
  // where d(s,t) = shortest collaboration distance between actors s and t.
  // d(s,t) = \infty if t is unreachable from s.
  public float closeness(String actor) {
    ActorPathFinder<String> traversals = new ActorPathFinder<>(this.graph, actor, this.Movies);
    float result = 0;
    Set<String> actors = traversals.pathExistsAll();
    actors.remove(actor);
    for (String t : actors) {
      int k = traversals.distanceTo(t);
      result += Math.pow(2, -k);
    }
    return result;
  }

/*============================================================================*/

}

