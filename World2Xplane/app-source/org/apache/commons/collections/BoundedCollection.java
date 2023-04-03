package org.apache.commons.collections;

import java.util.Collection;

public interface BoundedCollection extends Collection {
  boolean isFull();
  
  int maxSize();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BoundedCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */