package org.apache.commons.collections;

public interface OrderedMap extends IterableMap {
  OrderedMapIterator orderedMapIterator();
  
  Object firstKey();
  
  Object lastKey();
  
  Object nextKey(Object paramObject);
  
  Object previousKey(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\OrderedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */