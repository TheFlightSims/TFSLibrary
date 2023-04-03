package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.map.BaseHashMap;

public class IntKeyIntValueHashMap extends BaseHashMap {
  private Set keySet;
  
  private Collection values;
  
  public IntKeyIntValueHashMap() {
    this(8);
  }
  
  public IntKeyIntValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 1, 1, false);
  }
  
  public int get(int paramInt) throws NoSuchElementException {
    int i = getLookup(paramInt);
    if (i != -1)
      return this.intValueTable[i]; 
    throw new NoSuchElementException();
  }
  
  public int get(int paramInt1, int paramInt2) {
    int i = getLookup(paramInt1);
    return (i != -1) ? this.intValueTable[i] : paramInt2;
  }
  
  public boolean get(int paramInt, int[] paramArrayOfint) {
    int i = getLookup(paramInt);
    if (i != -1) {
      paramArrayOfint[0] = this.intValueTable[i];
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
      IntKeyIntValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntKeyIntValueHashMap.this, false);
    }
    
    public int size() {
      return IntKeyIntValueHashMap.this.size();
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
      IntKeyIntValueHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      IntKeyIntValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntKeyIntValueHashMap.this, true);
    }
    
    public int size() {
      return IntKeyIntValueHashMap.this.size();
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
      IntKeyIntValueHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\IntKeyIntValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */