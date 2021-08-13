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

  // Each instance of movielines is String[] such that
  //	String[0] = title of movie
  //	String[1..n-1] = list of actors
  public MovieNet(LinkedList<String[]> movielines) {}	// Constructor

/*============================================================================*/

  // [Q1]
  public String[] moviesby(String[] actors) { }

  // [Q2]
  public String[] castin(String[] titles) { }

  // [Q3]
  public String[] pairmost(String[] actors) { }

  // [Q4]
  public int Bacon(String actor) { }

  // [Q5]
  public int distance(String src, String dst) { }

  // [Q6]
  public int npath(String src, String dst) { }

  // [Q7]
  public String[] apath(String src, String dst) { }

  // [Q8]
  public int eccentricity(String actor) { }

  // [Q9]
  public float closeness(String actor) { }

/*============================================================================*/

}

