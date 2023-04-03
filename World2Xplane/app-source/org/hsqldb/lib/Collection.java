package org.hsqldb.lib;

public interface Collection {
  int size();
  
  boolean isEmpty();
  
  boolean contains(Object paramObject);
  
  Iterator iterator();
  
  boolean add(Object paramObject);
  
  boolean remove(Object paramObject);
  
  boolean addAll(Collection paramCollection);
  
  void clear();
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\Collection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */