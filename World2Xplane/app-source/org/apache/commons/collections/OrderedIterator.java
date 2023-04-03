package org.apache.commons.collections;

import java.util.Iterator;

public interface OrderedIterator extends Iterator {
  boolean hasPrevious();
  
  Object previous();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\OrderedIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */