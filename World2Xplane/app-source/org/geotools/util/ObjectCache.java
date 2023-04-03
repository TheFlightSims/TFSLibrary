package org.geotools.util;

import java.util.Set;

public interface ObjectCache {
  void clear();
  
  Object get(Object paramObject);
  
  Object peek(Object paramObject);
  
  void put(Object paramObject1, Object paramObject2);
  
  void writeLock(Object paramObject);
  
  void writeUnLock(Object paramObject);
  
  Set<Object> getKeys();
  
  void remove(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\ObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */