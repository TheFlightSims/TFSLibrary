package org.hsqldb.lib;

public class OrderedHashSet extends HashSet implements HsqlList, Set {
  public OrderedHashSet() {
    super(8);
  }
  
  public boolean remove(Object paramObject) {
    return (removeObject(paramObject, true) != null);
  }
  
  public Object remove(int paramInt) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    return removeObject(this.objectKeyTable[paramInt], true);
  }
  
  public boolean insert(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    if (paramInt < 0 || paramInt > size())
      throw new IndexOutOfBoundsException(); 
    if (contains(paramObject))
      return false; 
    if (paramInt == size())
      return add(paramObject); 
    Object[] arrayOfObject = new Object[size()];
    toArray(arrayOfObject);
    clear();
    int i;
    for (i = 0; i < paramInt; i++)
      add(arrayOfObject[i]); 
    add(paramObject);
    for (i = paramInt; i < arrayOfObject.length; i++)
      add(arrayOfObject[i]); 
    return true;
  }
  
  public Object set(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException();
  }
  
  public void add(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException();
  }
  
  public Object get(int paramInt) throws IndexOutOfBoundsException {
    checkRange(paramInt);
    return this.objectKeyTable[paramInt];
  }
  
  public void toArray(Object[] paramArrayOfObject) {
    System.arraycopy(this.objectKeyTable, 0, paramArrayOfObject, 0, paramArrayOfObject.length);
  }
  
  public int getIndex(Object paramObject) {
    return getLookup(paramObject, paramObject.hashCode());
  }
  
  public int getLargestIndex(OrderedHashSet paramOrderedHashSet) {
    int i = -1;
    byte b = 0;
    int j = paramOrderedHashSet.size();
    while (b < j) {
      int k = getIndex(paramOrderedHashSet.get(b));
      if (k > i)
        i = k; 
      b++;
    } 
    return i;
  }
  
  public int getSmallestIndex(OrderedHashSet paramOrderedHashSet) {
    int i = -1;
    byte b = 0;
    int j = paramOrderedHashSet.size();
    while (b < j) {
      int k = getIndex(paramOrderedHashSet.get(b));
      if (k != -1 && (i == -1 || k < i))
        i = k; 
      b++;
    } 
    return i;
  }
  
  public int getCommonElementCount(Set paramSet) {
    byte b1 = 0;
    byte b2 = 0;
    int i = size();
    while (b2 < i) {
      if (paramSet.contains(this.objectKeyTable[b2]))
        b1++; 
      b2++;
    } 
    return b1;
  }
  
  public static OrderedHashSet addAll(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2) {
    if (paramOrderedHashSet2 == null)
      return paramOrderedHashSet1; 
    if (paramOrderedHashSet1 == null)
      paramOrderedHashSet1 = new OrderedHashSet(); 
    paramOrderedHashSet1.addAll(paramOrderedHashSet2);
    return paramOrderedHashSet1;
  }
  
  public static OrderedHashSet add(OrderedHashSet paramOrderedHashSet, Object paramObject) {
    if (paramObject == null)
      return paramOrderedHashSet; 
    if (paramOrderedHashSet == null)
      paramOrderedHashSet = new OrderedHashSet(); 
    paramOrderedHashSet.add(paramObject);
    return paramOrderedHashSet;
  }
  
  private void checkRange(int paramInt) {
    if (paramInt < 0 || paramInt >= size())
      throw new IndexOutOfBoundsException(); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\OrderedHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */