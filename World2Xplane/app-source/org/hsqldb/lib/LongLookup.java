package org.hsqldb.lib;

import java.util.NoSuchElementException;

public interface LongLookup {
  int add(long paramLong1, long paramLong2);
  
  boolean addUnsorted(long paramLong1, long paramLong2);
  
  long lookup(long paramLong) throws NoSuchElementException;
  
  long lookup(long paramLong1, long paramLong2);
  
  void clear();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LongLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */