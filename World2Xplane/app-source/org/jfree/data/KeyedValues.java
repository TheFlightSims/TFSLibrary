package org.jfree.data;

import java.util.List;

public interface KeyedValues extends Values {
  Comparable getKey(int paramInt);
  
  int getIndex(Comparable paramComparable);
  
  List getKeys();
  
  Number getValue(Comparable paramComparable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\KeyedValues.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */