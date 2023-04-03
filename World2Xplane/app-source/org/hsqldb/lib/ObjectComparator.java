package org.hsqldb.lib;

import java.util.Comparator;

public interface ObjectComparator extends Comparator {
  int hashCode(Object paramObject);
  
  long longKey(Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\ObjectComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */