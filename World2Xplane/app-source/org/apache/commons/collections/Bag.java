package org.apache.commons.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public interface Bag extends Collection {
  int getCount(Object paramObject);
  
  boolean add(Object paramObject);
  
  boolean add(Object paramObject, int paramInt);
  
  boolean remove(Object paramObject);
  
  boolean remove(Object paramObject, int paramInt);
  
  Set uniqueSet();
  
  int size();
  
  boolean containsAll(Collection paramCollection);
  
  boolean removeAll(Collection paramCollection);
  
  boolean retainAll(Collection paramCollection);
  
  Iterator iterator();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\Bag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */