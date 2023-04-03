package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class HashSet extends BaseHashMap implements Set {
  public HashSet() {
    this(8);
  }
  
  public HashSet(int paramInt) throws IllegalArgumentException {
    super(paramInt, 3, 0, false);
  }
  
  public void setComparator(ObjectComparator paramObjectComparator) {
    super.setComparator(paramObjectComparator);
  }
  
  public boolean contains(Object paramObject) {
    return containsKey(paramObject);
  }
  
  public boolean containsAll(Collection paramCollection) {
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      if (contains(iterator.next()))
        continue; 
      return false;
    } 
    return true;
  }
  
  public Object get(Object paramObject) {
    int i = getLookup(paramObject, paramObject.hashCode());
    return (i < 0) ? null : this.objectKeyTable[i];
  }
  
  public boolean add(Object paramObject) {
    int i = size();
    addOrRemove(0L, 0L, paramObject, null, false);
    return (i != size());
  }
  
  public boolean addAll(Collection paramCollection) {
    boolean bool = false;
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext())
      bool |= add(iterator.next()); 
    return bool;
  }
  
  public boolean addAll(Object[] paramArrayOfObject) {
    boolean bool = false;
    for (byte b = 0; b < paramArrayOfObject.length; b++)
      bool |= add(paramArrayOfObject[b]); 
    return bool;
  }
  
  public boolean addAll(Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
    boolean bool = false;
    for (int i = paramInt1; i < paramArrayOfObject.length && i < paramInt2; i++)
      bool |= add(paramArrayOfObject[i]); 
    return bool;
  }
  
  public boolean remove(Object paramObject) {
    return (removeObject(paramObject, false) != null);
  }
  
  public boolean removeAll(Collection paramCollection) {
    Iterator iterator = paramCollection.iterator();
    boolean bool;
    for (bool = true; iterator.hasNext(); bool &= remove(iterator.next()));
    return bool;
  }
  
  public boolean removeAll(Object[] paramArrayOfObject) {
    boolean bool = true;
    for (byte b = 0; b < paramArrayOfObject.length; b++)
      bool &= remove(paramArrayOfObject[b]); 
    return bool;
  }
  
  public void toArray(Object[] paramArrayOfObject) {
    Iterator iterator = iterator();
    for (byte b = 0; iterator.hasNext(); b++)
      paramArrayOfObject[b] = iterator.next(); 
  }
  
  public Object[] toArray() {
    if (isEmpty())
      return emptyObjectArray; 
    Object[] arrayOfObject = new Object[size()];
    toArray(arrayOfObject);
    return arrayOfObject;
  }
  
  public Iterator iterator() {
    return (Iterator)new BaseHashMap.BaseHashIterator(this, true);
  }
  
  public String toString() {
    Iterator iterator = iterator();
    StringBuffer stringBuffer = new StringBuffer();
    while (iterator.hasNext()) {
      if (stringBuffer.length() > 0) {
        stringBuffer.append(", ");
      } else {
        stringBuffer.append('[');
      } 
      stringBuffer.append(iterator.next());
    } 
    return stringBuffer.toString() + ']';
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */