package org.hsqldb.lib;

public interface HsqlHeap {
  void clear();
  
  boolean isEmpty();
  
  boolean isFull();
  
  void add(Object paramObject) throws IllegalArgumentException, RuntimeException;
  
  Object peek();
  
  Object remove();
  
  int size();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlHeap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */