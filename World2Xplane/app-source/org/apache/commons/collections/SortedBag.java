package org.apache.commons.collections;

import java.util.Comparator;

public interface SortedBag extends Bag {
  Comparator comparator();
  
  Object first();
  
  Object last();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\SortedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */