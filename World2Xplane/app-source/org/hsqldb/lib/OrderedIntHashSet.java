package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class OrderedIntHashSet extends BaseHashMap {
  public OrderedIntHashSet() {
    this(8);
  }
  
  public OrderedIntHashSet(int paramInt) throws IllegalArgumentException {
    super(paramInt, 1, 0, false);
  }
  
  public boolean contains(int paramInt) {
    return containsKey(paramInt);
  }
  
  public boolean add(int paramInt) {
    int i = size();
    addOrRemove(paramInt, 0L, null, null, false);
    return (i != size());
  }
  
  public boolean remove(int paramInt) {
    int i = size();
    addOrRemove(paramInt, 0L, null, null, true);
    boolean bool = (i != size()) ? true : false;
    if (bool) {
      int[] arrayOfInt = toArray();
      clear();
      for (byte b = 0; b < arrayOfInt.length; b++)
        add(arrayOfInt[b]); 
    } 
    return bool;
  }
  
  public int get(int paramInt) {
    checkRange(paramInt);
    return this.intKeyTable[paramInt];
  }
  
  public int getIndex(int paramInt) {
    return getLookup(paramInt);
  }
  
  public int getStartMatchCount(int[] paramArrayOfint) {
    byte b;
    for (b = 0; b < paramArrayOfint.length && containsKey(paramArrayOfint[b]); b++);
    return b;
  }
  
  public int getOrderedStartMatchCount(int[] paramArrayOfint) {
    byte b;
    for (b = 0; b < paramArrayOfint.length && b < size() && get(b) == paramArrayOfint[b]; b++);
    return b;
  }
  
  public boolean addAll(Collection paramCollection) {
    int i = size();
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext())
      add(iterator.nextInt()); 
    return (i != size());
  }
  
  public int[] toArray() {
    int i = -1;
    int[] arrayOfInt = new int[size()];
    for (byte b = 0; b < arrayOfInt.length; b++) {
      i = nextLookup(i);
      int j = this.intKeyTable[i];
      arrayOfInt[b] = j;
    } 
    return arrayOfInt;
  }
  
  private void checkRange(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\OrderedIntHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */