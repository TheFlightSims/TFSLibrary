package org.hsqldb.lib;

public interface Set extends Collection {
  int size();
  
  boolean isEmpty();
  
  boolean contains(Object paramObject);
  
  Iterator iterator();
  
  boolean add(Object paramObject);
  
  Object get(Object paramObject);
  
  boolean remove(Object paramObject);
  
  void clear();
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\Set.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */