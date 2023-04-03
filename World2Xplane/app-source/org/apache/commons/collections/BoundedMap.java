package org.apache.commons.collections;

import java.util.Map;

public interface BoundedMap extends Map {
  boolean isFull();
  
  int maxSize();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BoundedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */