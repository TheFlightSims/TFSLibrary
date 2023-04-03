package org.hsqldb.lib;

import java.util.Comparator;

public class StringComparator implements Comparator {
  public int compare(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2) ? 0 : ((paramObject1 == null) ? -1 : ((paramObject2 == null) ? 1 : ((String)paramObject1).compareTo((String)paramObject2)));
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\StringComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */