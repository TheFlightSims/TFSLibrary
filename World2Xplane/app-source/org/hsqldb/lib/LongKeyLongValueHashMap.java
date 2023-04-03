package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.map.BaseHashMap;

public class LongKeyLongValueHashMap extends BaseHashMap {
  private Set keySet;
  
  private Collection values;
  
  public LongKeyLongValueHashMap() {
    this(8);
  }
  
  public LongKeyLongValueHashMap(boolean paramBoolean) {
    this(8);
    this.minimizeOnEmpty = paramBoolean;
  }
  
  public LongKeyLongValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 2, 2, false);
  }
  
  public long get(long paramLong) throws NoSuchElementException {
    int i = getLookup(paramLong);
    if (i != -1)
      return this.longValueTable[i]; 
    throw new NoSuchElementException();
  }
  
  public long get(long paramLong1, long paramLong2) {
    int i = getLookup(paramLong1);
    return (i != -1) ? this.longValueTable[i] : paramLong2;
  }
  
  public boolean get(long paramLong, long[] paramArrayOflong) {
    int i = getLookup(paramLong);
    if (i != -1) {
      paramArrayOflong[0] = this.longValueTable[i];
      return true;
    } 
    return false;
  }
  
  public boolean put(long paramLong1, long paramLong2) {
    int i = size();
    addOrRemove(paramLong1, paramLong2, null, null, false);
    return (i != size());
  }
  
  public boolean remove(long paramLong) {
    int i = size();
    addOrRemove(paramLong, 0L, null, null, true);
    return (i != size());
  }
  
  public Set keySet() {
    if (this.keySet == null)
      this.keySet = new KeySet(); 
    return this.keySet;
  }
  
  public Collection values() {
    if (this.values == null)
      this.values = new Values(); 
    return this.values;
  }
  
  class Values implements Collection {
    public Iterator iterator() {
      LongKeyLongValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongKeyLongValueHashMap.this, false);
    }
    
    public int size() {
      return LongKeyLongValueHashMap.this.size();
    }
    
    public boolean contains(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean add(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean addAll(Collection param1Collection) {
      throw new RuntimeException();
    }
    
    public boolean remove(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean isEmpty() {
      return (size() == 0);
    }
    
    public void clear() {
      LongKeyLongValueHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      LongKeyLongValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongKeyLongValueHashMap.this, true);
    }
    
    public int size() {
      return LongKeyLongValueHashMap.this.size();
    }
    
    public boolean contains(Object param1Object) {
      throw new RuntimeException();
    }
    
    public Object get(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean add(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean addAll(Collection param1Collection) {
      throw new RuntimeException();
    }
    
    public boolean remove(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean isEmpty() {
      return (size() == 0);
    }
    
    public void clear() {
      LongKeyLongValueHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LongKeyLongValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */