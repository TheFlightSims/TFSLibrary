package org.apache.xerces.dom;

import java.util.Hashtable;

class LCount {
  static Hashtable lCounts = new Hashtable();
  
  public int captures = 0;
  
  public int bubbles = 0;
  
  public int defaults;
  
  public int total = 0;
  
  static LCount lookup(String paramString) {
    LCount lCount = (LCount)lCounts.get(paramString);
    if (lCount == null)
      lCounts.put(paramString, lCount = new LCount()); 
    return lCount;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\LCount.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */