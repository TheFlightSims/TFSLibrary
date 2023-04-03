package org.hsqldb.lib;

import java.util.NoSuchElementException;

public interface IntLookup {
  int add(int paramInt1, int paramInt2);
  
  boolean addUnsorted(int paramInt1, int paramInt2);
  
  int lookup(int paramInt) throws NoSuchElementException;
  
  int lookup(int paramInt1, int paramInt2);
  
  void clear();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\IntLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */