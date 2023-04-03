package org.apache.commons.collections;

public interface OrderedMapIterator extends MapIterator, OrderedIterator {
  boolean hasPrevious();
  
  Object previous();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\OrderedMapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */