package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class OrderedLongHashSet extends BaseHashMap {
  public OrderedLongHashSet() {
    this(8);
  }
  
  public OrderedLongHashSet(int paramInt) throws IllegalArgumentException {
    super(paramInt, 2, 0, false);
  }
  
  public boolean contains(long paramLong) {
    return containsKey(paramLong);
  }
  
  public boolean add(long paramLong) {
    int i = size();
    addOrRemove(paramLong, 0L, null, null, false);
    return (i != size());
  }
  
  public boolean remove(long paramLong) {
    int i = size();
    addOrRemove(paramLong, 0L, null, null, true);
    boolean bool = (i != size()) ? true : false;
    if (bool) {
      long[] arrayOfLong = toArray();
      clear();
      for (byte b = 0; b < arrayOfLong.length; b++)
        add(arrayOfLong[b]); 
    } 
    return bool;
  }
  
  public long get(int paramInt) {
    checkRange(paramInt);
    return this.longKeyTable[paramInt];
  }
  
  public int getIndex(long paramLong) {
    return getLookup(paramLong);
  }
  
  public int getStartMatchCount(long[] paramArrayOflong) {
    byte b;
    for (b = 0; b < paramArrayOflong.length && containsKey(paramArrayOflong[b]); b++);
    return b;
  }
  
  public int getOrderedStartMatchCount(long[] paramArrayOflong) {
    byte b;
    for (b = 0; b < paramArrayOflong.length && b < size() && get(b) == paramArrayOflong[b]; b++);
    return b;
  }
  
  public boolean addAll(Collection paramCollection) {
    int i = size();
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext())
      add(iterator.nextLong()); 
    return (i != size());
  }
  
  public long[] toArray() {
    int i = -1;
    long[] arrayOfLong = new long[size()];
    for (byte b = 0; b < arrayOfLong.length; b++) {
      i = nextLookup(i);
      long l = this.intKeyTable[i];
      arrayOfLong[b] = l;
    } 
    return arrayOfLong;
  }
  
  private void checkRange(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\OrderedLongHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */