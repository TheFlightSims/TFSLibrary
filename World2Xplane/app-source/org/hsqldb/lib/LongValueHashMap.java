package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.map.BaseHashMap;

public class LongValueHashMap extends BaseHashMap {
  Set keySet;
  
  public LongValueHashMap() {
    this(8);
  }
  
  public LongValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 3, 2, false);
  }
  
  public long get(Object paramObject) throws NoSuchElementException {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
      return this.longValueTable[j]; 
    throw new NoSuchElementException();
  }
  
  public long get(Object paramObject, int paramInt) {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    return (j != -1) ? this.longValueTable[j] : paramInt;
  }
  
  public boolean get(Object paramObject, long[] paramArrayOflong) {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1) {
      paramArrayOflong[0] = this.longValueTable[j];
      return true;
    } 
    return false;
  }
  
  public Object getKey(long paramLong) {
    BaseHashMap.BaseHashIterator baseHashIterator = new BaseHashMap.BaseHashIterator(this, false);
    while (baseHashIterator.hasNext()) {
      long l = baseHashIterator.nextLong();
      if (l == paramLong)
        return this.objectKeyTable[baseHashIterator.getLookup()]; 
    } 
    return null;
  }
  
  public boolean put(Object paramObject, long paramLong) {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = size();
    addOrRemove(0L, paramLong, paramObject, null, false);
    return (i != size());
  }
  
  public boolean remove(Object paramObject) {
    int i = size();
    addOrRemove(0L, 0L, paramObject, null, true);
    return (i != size());
  }
  
  public boolean containsKey(Object paramObject) {
    return super.containsKey(paramObject);
  }
  
  public Set keySet() {
    if (this.keySet == null)
      this.keySet = new KeySet(); 
    return this.keySet;
  }
  
  public void putAll(LongValueHashMap paramLongValueHashMap) {
    Iterator iterator = paramLongValueHashMap.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      put(object, paramLongValueHashMap.get(object));
    } 
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      LongValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongValueHashMap.this, true);
    }
    
    public int size() {
      return LongValueHashMap.this.size();
    }
    
    public boolean contains(Object param1Object) {
      return LongValueHashMap.this.containsKey(param1Object);
    }
    
    public Object get(Object param1Object) {
      int i = LongValueHashMap.this.getLookup(param1Object, param1Object.hashCode());
      return (i < 0) ? null : LongValueHashMap.this.objectKeyTable[i];
    }
    
    public boolean add(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean addAll(Collection param1Collection) {
      throw new RuntimeException();
    }
    
    public boolean remove(Object param1Object) {
      int i = size();
      LongValueHashMap.this.remove(param1Object);
      return (size() != i);
    }
    
    public boolean isEmpty() {
      return (size() == 0);
    }
    
    public void clear() {
      LongValueHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LongValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */