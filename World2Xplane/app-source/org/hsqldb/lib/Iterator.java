package org.hsqldb.lib;

import java.util.NoSuchElementException;

public interface Iterator {
  boolean hasNext();
  
  Object next() throws NoSuchElementException;
  
  int nextInt() throws NoSuchElementException;
  
  long nextLong() throws NoSuchElementException;
  
  void remove() throws NoSuchElementException;
  
  void setValue(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\Iterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */