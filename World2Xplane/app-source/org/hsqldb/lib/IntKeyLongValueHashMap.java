package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.map.BaseHashMap;

public class IntKeyLongValueHashMap extends BaseHashMap {
  public IntKeyLongValueHashMap() {
    this(8);
  }
  
  public IntKeyLongValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 1, 2, false);
  }
  
  public long get(int paramInt) throws NoSuchElementException {
    int i = getLookup(paramInt);
    if (i != -1)
      return this.longValueTable[i]; 
    throw new NoSuchElementException();
  }
  
  public long get(int paramInt1, int paramInt2) {
    int i = getLookup(paramInt1);
    return (i != -1) ? this.longValueTable[i] : paramInt2;
  }
  
  public boolean get(int paramInt, long[] paramArrayOflong) {
    int i = getLookup(paramInt);
    if (i != -1) {
      paramArrayOflong[0] = this.longValueTable[i];
      return true;
    } 
    return false;
  }
  
  public boolean put(int paramInt1, int paramInt2) {
    int i = size();
    addOrRemove(paramInt1, paramInt2, null, null, false);
    return (i != size());
  }
  
  public boolean remove(int paramInt) {
    int i = size();
    addOrRemove(paramInt, 0L, null, null, true);
    return (i != size());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\IntKeyLongValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */