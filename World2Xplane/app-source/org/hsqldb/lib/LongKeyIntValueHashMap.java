package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.map.BaseHashMap;

public class LongKeyIntValueHashMap extends BaseHashMap {
  private Set keySet;
  
  private Collection values;
  
  public LongKeyIntValueHashMap() {
    this(8);
  }
  
  public LongKeyIntValueHashMap(boolean paramBoolean) {
    this(8);
    this.minimizeOnEmpty = paramBoolean;
  }
  
  public LongKeyIntValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 2, 1, false);
  }
  
  public int get(long paramLong) throws NoSuchElementException {
    int i = getLookup(paramLong);
    if (i != -1)
      return this.intValueTable[i]; 
    throw new NoSuchElementException();
  }
  
  public int get(long paramLong, int paramInt) {
    int i = getLookup(paramLong);
    return (i != -1) ? this.intValueTable[i] : paramInt;
  }
  
  public boolean get(long paramLong, int[] paramArrayOfint) {
    int i = getLookup(paramLong);
    if (i != -1) {
      paramArrayOfint[0] = this.intValueTable[i];
      return true;
    } 
    return false;
  }
  
  public int getLookup(long paramLong) {
    return super.getLookup(paramLong);
  }
  
  public boolean put(long paramLong, int paramInt) {
    int i = size();
    addOrRemove(paramLong, paramInt, null, null, false);
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
      LongKeyIntValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongKeyIntValueHashMap.this, false);
    }
    
    public int size() {
      return LongKeyIntValueHashMap.this.size();
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
      LongKeyIntValueHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      LongKeyIntValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongKeyIntValueHashMap.this, true);
    }
    
    public int size() {
      return LongKeyIntValueHashMap.this.size();
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
      LongKeyIntValueHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LongKeyIntValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */