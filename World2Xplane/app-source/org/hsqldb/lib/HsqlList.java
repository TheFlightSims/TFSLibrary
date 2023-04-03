package org.hsqldb.lib;

public interface HsqlList extends Collection {
  void add(int paramInt, Object paramObject);
  
  boolean add(Object paramObject);
  
  Object get(int paramInt);
  
  Object remove(int paramInt);
  
  Object set(int paramInt, Object paramObject);
  
  boolean isEmpty();
  
  int size();
  
  Iterator iterator();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */