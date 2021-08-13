// Test Main for the 'Six Degrees of Kevin Bacon' SNA program.
//	File IO is done through TextInputStream class.
//
// Bongki Moon (bkmoon@snu.ac.kr), Oct/23/2014.

import java.io.*;
import java.util.*;
import java.lang.Object;
import java.lang.management.*;

public class MainBacon {

  static final String NoActorName = "NoActor, Iam";
  static final String TomHanks = "Hanks, Tom";
  static final String MegRyan = "Ryan, Meg (I)";
  static final String TimAllen = "Allen, Tim (I)";
  static final String CharlizeTheron = "Theron, Charlize";

  public static ThreadMXBean TMB;

  public static void main(String args[]) throws IOException
  {
    long cputime;

    if (args.length != 2) {
	System.err.println("Usage: java MainBacon movie-file query-file");
	System.exit(0);
    }

    TMB = ManagementFactory.getThreadMXBean();
    if (! TMB.isThreadCpuTimeSupported()) {
	System.out.println("ThreadCpuTime is not supported.");
	System.exit(0);
    }

    /* (1) Read all movies from the 1st input file */
    /*       (Movie file format: movie/actor[0]/actor[1]/...) */
    LinkedList<String[]> movieList = new LinkedList<String[]>();
    TextInputStream sfs = new TextInputStream(args[0]);
    while(sfs.ready()) {
	String line = sfs.readLine();
	String[] casting = line.split("/");
	movieList.add(casting);
    }

    /* (2) Construct a MovieNet Database */
    cputime = TMB.getCurrentThreadCpuTime();
    MovieNet hollywood = new MovieNet(movieList);
    cputime = TMB.getCurrentThreadCpuTime() - cputime;
    System.out.println("CPU time to construct a MovieNet Database: "
		+(cputime/1000000)+" millisec");
    //movieList = null;		// eligible for garbage collection
    //hollywood.printStat();

    /* (3) Read all queries from the 2nd input file */
    /*       (Query file format: operator/operand[0]/operand[1]/...) */
    LinkedList<String[]> queryList = new LinkedList<String[]>();
    TextInputStream qfs = new TextInputStream(args[1]);
    while(qfs.ready()) {
	String line = qfs.readLine();
	String[] query = line.split("/");
	queryList.add(query);
    }

    /* (4) Process each query whose type is determined by an operator */
    cputime = TMB.getCurrentThreadCpuTime();
    for(int i=0; i < queryList.size() ;i++) {
	String[] query = queryList.get(i);
	String op = query[0];
	String[] opnd = removeOp(query);

	/* Query Types */
	switch(Integer.parseInt(op)) {
	case 1: System.out.print("Which movies were starred by ");
			printStrings(opnd,"? ");
			printStrings(hollywood.moviesby(opnd),"\n");
		break;
	case 2: System.out.print("Who were cast in all the movie(s): ");
			printStrings(opnd,"? ");
			printStrings(hollywood.castin(opnd),"\n");
		break;
	case 3: System.out.print("Which pair cast in "+opnd[0]
			+" costarred most often? ");
			String[] actors = hollywood.castin(opnd);
			if (actors==null) actors = new String[0];
			printStrings(hollywood.pairmost(actors),"\n");
		break;

	case 4: System.out.println("Bacon number of "+opnd[0]+" : "
			+hollywood.Bacon(opnd[0]));
		break;
	case 5: System.out.println("Collaboration distance between "
			+opnd[0]+"/" +opnd[1]+" : "
			+hollywood.distance(opnd[0],opnd[1]));
		break;
	case 6: System.out.println("The number of shortest paths between "
			+opnd[0]+"/" +opnd[1]+" : "
			+hollywood.npath(opnd[0],opnd[1]));
		break;
	case 7: System.out.print("A shortest path from "
			+opnd[0]+" to " +opnd[1]+" : ");
			printPath(hollywood.apath(opnd[0],opnd[1]),"\n");
		break;

	case 8: System.out.println("Eccentricity of "+opnd[0]+" : "
			+hollywood.eccentricity(opnd[0]));
		break;
	case 9: System.out.println("Closeness of "+opnd[0]+" : "
			+hollywood.closeness(opnd[0]));
		break;
	default:
	}
    }
    cputime = TMB.getCurrentThreadCpuTime() - cputime;
    System.out.println("CPU time to process queries : "
		+(cputime/1000000)+" millisec");

    // (5) Report the memory usage.
    Runtime runtime = Runtime.getRuntime();
    System.out.println("\nMemory consumption: "
                + (runtime.totalMemory() - runtime.freeMemory()) + " bytes\n");
  }

  public static void printStrings(String[] s, String end)
  {
    if (s == null) System.out.println("(null)");
    else {
      java.util.Arrays.sort(s);
      for(int i=0; i < s.length ;i++) {
	System.out.print(s[i]);
	System.out.print((i != s.length-1)? "/" : end);
      }
    }
  }

  public static void printPath(String[] s, String end)
  {
    if (s == null) System.out.println("(null)");
    else {
      for(int i=0; i < s.length ;i++) {
	System.out.print(s[i]);
	System.out.print((i != s.length-1)? " -> " : end);
      }
    }
  }

  public static String[] removeOp(String[] s)
  {
    String[] rest = new String[s.length-1];
    for(int i=0; i < s.length-1 ;i++) rest[i] = s[i+1];
    return rest;
  }

}
