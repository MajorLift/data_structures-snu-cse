// Class TextInputStream provides methods for reading from an input
// stream.  A TextInputStream can be constructed either from an InputStream
// such as System.in or by specifying the name of a file as a String.
//
// The public methods are summarized below.
//   public TextInputStream(InputStream input)
//     constructs a TextInputStream from the given InputStream
//   public TextInputStream(StringReader reader)
//     constructs a TextInputStream using the given StringReader
//   public TextInputStream(String fileName)
//     constructs a TextInputStream using the given file name
//   public String readLine()
//     reads one line of text and returns it as a String
//   public char read()
//     reads one character
//   public void unread(char ch)
//     puts the given character back into the input stream
//   public char peek()
//     returns next character in input stream without reading it
//   public String readWord()
//     reads next word from input stream
//   public int readInt()
//     reads next token from input stream and returns it as an int
//   public double readDouble()
//     reads next token from input stream and returns it as a double
//   public boolean ready()
//     returns true if stream is ready for reading; otherwise returns false

import java.io.*;

public class TextInputStream
{
  private PushbackReader in; // the input stream

  public TextInputStream(InputStream input)
       // pre : input stream is open for reading
       // post: constructs a TextInputStream from the given InputStream
  {
    in = new PushbackReader(new InputStreamReader(input));
  }

  public TextInputStream(StringReader reader)
       // pre : input stream is open for reading
       // post: constructs a TextInputStream from the given StringReader
  {
    in = new PushbackReader(reader);
  }

  public TextInputStream(String fileName)
       // pre : fileName is the name of a file that can be opened for reading
       // post: constructs a TextInputStream tied to the given file
  {
    try {
      in = new PushbackReader(new FileReader(fileName));
    } catch(Exception e) {
      System.out.println("Can't open input file '" + fileName + "', exiting");
      System.exit(1);
    }
  }
      
  public String readLine()
       // pre : not at end-of-file of the input stream
       // post: reads the next line of input and returns it as a String
  {
    String result = "";
    try {
      for(;;) {
    int next = in.read();
    if (next == '\r') // skip carriage-return on Windows systems
      continue;
    if (next == -1 || next == '\n')
      break;
    result += (char)next;
      }
    } catch (Exception e) {
      System.out.println("Failure in call on readLine method, exiting.");
      System.exit(1);
    }
    return result;
  }

  public char read()
       // pre : not at end-of-file of the input stream
       // post: reads the next character of input and returns it
  {
    char result = ' ';
    try {
      result = (char)in.read();
    if (result == '\r') // skip carriage-return on Windows systems
      result = (char)in.read();
    } catch (Exception e) {
      System.out.println("Failure in call on read method, exiting.");
      System.exit(1);
    }
    return result;
  }

  public void unread(char ch)
       // post: puts the given character back into the input stream to be
       //       read again
  {
    try {
      in.unread((byte)ch);
    } catch (Exception e) {
      System.out.println("Failure in call on unread method, exiting");
      System.exit(1);
    }
  }

  public char peek()
       // post: returns the next character in the input stream without
       //       actually reading it
  {
    int next = 0;
    try {
      next = in.read();
    } catch (Exception e) {
      System.out.println("Failure in call on peek method, exiting");
      System.exit(1);
    }
    if (next != -1)
      unread((char)next);
    return (char)next;
  }

  public String readWord()
       // pre : stream contains at least one nonwhitespace character
       // post: skips leading whitespace, reads one word (terminated by
       //       end-of-file or whitespace)
  {
    String result = "";
    try {
      int next;
      do
    next = in.read();
      while (next != -1 && Character.isWhitespace((char)next));
      while (next != -1 && !Character.isWhitespace((char)next)) {
    result += (char)next;
    next = in.read();
      }
      while (next != -1 && next != '\n' && Character.isWhitespace((char)next))
    next = in.read();
      if (next != -1 && next != '\n')
    unread((char)next);
    } catch (Exception e) {
      System.out.println("Failure in call on readWord method, exiting.");
      System.exit(1);
    }
    return result;
  }

  public int readInt()
       // pre : next token in input stream is an int
       // post: reads int and skips any trailing whitespace on current line
  {
    int result = 0;
    try {
      result = Integer.parseInt(readWord());
    } catch (Exception e) {
      System.out.println("Failure in call on readInt method, returning 0");
    }
    return result;
  }

  public double readDouble()
       // pre : next token in input stream is a double
       // post: reads double and skips any trailing whitespace on current line
  {
    double result = 0.0;
    try {
      // Double constructor : deprecated in java11 or later
      // result = new Double(readWord()).doubleValue();
      result = Double.parseDouble(readWord());
    } catch (Exception e) {
      System.out.println("Failure in call on readDouble method, returning 0");
    }
    return result;
  }

  public boolean ready()
       // post: returns true if input stream is ready for reading;
       //       otherwise returns false
  {
    boolean result = false;
    try {
      result = in.ready();
    } catch (IOException e) {
      System.out.println("Failure in call on ready method, returning false.");
    }      
    return result;
  }
}
