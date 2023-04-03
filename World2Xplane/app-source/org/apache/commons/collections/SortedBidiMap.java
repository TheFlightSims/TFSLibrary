package org.apache.commons.collections;

import java.util.SortedMap;

public interface SortedBidiMap extends OrderedBidiMap, SortedMap {
  BidiMap inverseBidiMap();
  
  SortedBidiMap inverseSortedBidiMap();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\SortedBidiMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */