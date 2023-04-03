package org.apache.commons.collections;

import java.util.Iterator;

public interface MapIterator extends Iterator {
  boolean hasNext();
  
  Object next();
  
  Object getKey();
  
  Object getValue();
  
  void remove();
  
  Object setValue(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\MapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */