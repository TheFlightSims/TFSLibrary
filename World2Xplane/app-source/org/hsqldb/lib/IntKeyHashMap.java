package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class IntKeyHashMap extends BaseHashMap {
  Set keySet;
  
  Collection values;
  
  public IntKeyHashMap() {
    this(8);
  }
  
  public IntKeyHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 1, 3, false);
  }
  
  public Object get(int paramInt) {
    int i = getLookup(paramInt);
    return (i != -1) ? this.objectValueTable[i] : null;
  }
  
  public Object put(int paramInt, Object paramObject) {
    return addOrRemove(paramInt, paramObject, null, false);
  }
  
  public boolean containsValue(Object paramObject) {
    return super.containsValue(paramObject);
  }
  
  public Object remove(int paramInt) {
    return addOrRemove(paramInt, null, null, true);
  }
  
  public boolean containsKey(int paramInt) {
    return super.containsKey(paramInt);
  }
  
  public void valuesToArray(Object[] paramArrayOfObject) {
    Iterator iterator = values().iterator();
    for (byte b = 0; iterator.hasNext(); b++)
      paramArrayOfObject[b] = iterator.next(); 
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
      IntKeyHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntKeyHashMap.this, false);
    }
    
    public int size() {
      return IntKeyHashMap.this.size();
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
      IntKeyHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      IntKeyHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntKeyHashMap.this, true);
    }
    
    public int size() {
      return IntKeyHashMap.this.size();
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
      IntKeyHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\IntKeyHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */