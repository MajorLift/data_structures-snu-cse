// Test Main for AVL and Linear Hashing
//	File IO is done through Stuart Reges' TextInputStream class.
//
import java.io.*;
//import java.lang.Object;
import java.lang.management.*;

public class MainSearch {
  public static ThreadMXBean TMB;

  public static void main(String args[]) throws IOException
  {
    long cputime;

    if (args.length != 3) {
	System.err.println("Usage: java MainBst train-file query-file LHsize");
	System.exit(0);
    }

    String trainFile = args[0];
    String queryFile = args[1];
    int LHsize = Integer.parseInt(args[2]);

    System.out.print(">> train: "+trainFile+", query: "+queryFile);
    System.out.println(", LHsize: "+LHsize+"\n");

    TMB = ManagementFactory.getThreadMXBean();
    if (! TMB.isThreadCpuTimeSupported()) {
	System.out.println("ThreadCpuTime is not supported.");
	System.exit(0);
    }

    // (1) Create a plain BST and an AVL from the train set.
    BST bst = new BST();
    buildBST(bst, trainFile);

    AVL avl = new AVL();
    buildBST(avl, trainFile);
    System.out.println("Number of words in the BST: "+bst.size()
		+" (number of insertions: "+bst.sumFreq()+")");

    // (2) Probe the plain BST and AVL to find words from the query set.
    //System.out.println("Sum of Weighted Path Lengths (BST): "
    //		+bst.sumWeightedPath());
    bst.resetCounters();
    probeBST(bst,queryFile);

    //System.out.println("Sum of Weighted Path Lengths (AVL): "
    //		+avl.sumWeightedPath());
    avl.resetCounters();
    probeBST(avl,queryFile);
    System.out.println();

    // (3) Create a Linear Hash table from the train set.
    LinearHash lhash = new LinearHash(LHsize);
    buildLHash(lhash,trainFile);

    // (4) Probe the Linear Hash table to find words from the query set.
    probeLHash(lhash,queryFile);

    // (5) Report the memory usage.
    Runtime runtime = Runtime.getRuntime();
    System.out.println("\nMemory consumption: "
		+ (runtime.totalMemory() - runtime.freeMemory()) + " bytes\n");
  }

// ************************************************************************** //

  private static void buildBST(BST bst, String input)
  {
    TextInputStream sfs = new TextInputStream(input);

    long cputime = TMB.getCurrentThreadCpuTime();
    while(sfs.ready()) bst.insert(sfs.readWord());
    cputime = TMB.getCurrentThreadCpuTime() - cputime;

    bst.print();
    String bstType = (bst instanceof AVL)? "AVL" : "BST";
    System.out.println("CPU time to build a(n) "+bstType+": "
				+(cputime/1000000)+" millisec");
  }

  private static void probeBST(BST bst, String keys)
  {
    TextInputStream qfs = new TextInputStream(keys);
    int	notfound=0;

    long cputime = TMB.getCurrentThreadCpuTime();
    while(qfs.ready()) {
	String queryWord = qfs.readWord();
	if (bst.find(queryWord)==false) {
	    // Uncomment the next line to make it verbose.
	    //System.out.println("The word `"+queryWord+"' not found.");
	    notfound++;
	}
    }
    cputime = TMB.getCurrentThreadCpuTime() - cputime;

    bst.print();
    String bstType = (bst instanceof AVL)? "AVL" : "BST";
    System.out.println("Total number of node accesses ("+bstType+"): "
		+bst.sumProbes()+" (failed searches: "+notfound+")");
    System.out.println("CPU time for searching keys ("+bstType+"): "
		+(cputime/1000000)+" millisec");
  }

// ************************************************************************** //

  private static void buildLHash(LinearHash lhash, String trainFile)
  {
    TextInputStream dfs = new TextInputStream(trainFile);
    int failedInsert = 0;

    long cputime = TMB.getCurrentThreadCpuTime();
    while(dfs.ready()) {
        String trainWord = dfs.readWord();
        //if (lhash.insert(trainWord) < 0) {
        if (lhash.insertUnique(trainWord) < 0) {
	    // Uncomment the next line to make it verbose.
            //System.out.println("Failed to insert `"+trainWord+"' into HT.");
            failedInsert++;
        }
    }
    cputime = TMB.getCurrentThreadCpuTime() - cputime;

    float storageUtil = (float)lhash.wordCount()
                                / (lhash.wordCount()+lhash.emptyCount());
        // lhash.wordCount()/lhash.size() does not work for Linear Hashing.

    lhash.print();
    //lhash.printHashStat();
    System.out.println("CPU time to build the Hash Table: "
				+(cputime/1000000)+" millisec");
    System.out.print("Words inserted: "+lhash.wordCount());
    System.out.print(" (insertions failed: "+failedInsert+"); ");
    System.out.print("Storage util: "+storageUtil);
    System.out.println(" (hash entries: "+lhash.size()+")");
  }

  private static void probeLHash(LinearHash lhash, String queryFile)
  {
    TextInputStream qfs = new TextInputStream(queryFile);
    int hdepth, nfound=0, notfound=0;
    float accesses=0;

    long cputime = TMB.getCurrentThreadCpuTime();
    while(qfs.ready()) {
        String queryWord = qfs.readWord();
        if ((hdepth=lhash.lookup(queryWord)) > 0) {
	    // Uncomment the next line to make it verbose.
            //System.out.println("The word `"+queryWord+"' found: "+hdepth);
            nfound++;
	    accesses += (float)hdepth / 2. + .5;
        } else {
	    // Uncomment the next line to make it verbose.
            //System.out.println("The word `"+queryWord+"' not found.");
            notfound++;
	    accesses += (float)(-hdepth);
        }
    }
    cputime = TMB.getCurrentThreadCpuTime() - cputime;

    System.out.print("Words found: "+nfound);
    System.out.print(" (searches failed: "+notfound+")\n");
    System.out.println("Total number of Hash Table accesses: "+accesses);
    System.out.println("CPU time for searching keys in the Hash Table: "
		+(cputime/1000000)+" millisec");
  }

}
