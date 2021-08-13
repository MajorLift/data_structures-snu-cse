// My collection of Java utility functions.

import java.util.*;

public class MyUtil {

  // Hash a string key into a long integer in [0,M-1].
  static long ELFhash(String key, int M) {
    long h = 0;
    for (int i=0; i<key.length(); i++) {
      h = (h << 4) + (int) key.charAt(i);
      long g = h & 0xF0000000L;
      if (g != 0) h ^= g >>> 24;
      h &= ~g;
    }
    return h % M;
  }
}

