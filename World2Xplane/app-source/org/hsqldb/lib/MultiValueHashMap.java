package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class MultiValueHashMap extends BaseHashMap {
  Set keySet;
  
  Collection values;
  
  Iterator valueIterator;
  
  public MultiValueHashMap() {
    this(8);
  }
  
  public MultiValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 3, 3, false);
    this.multiValueTable = new boolean[this.objectValueTable.length];
  }
  
  public Iterator get(Object paramObject) {
    int i = paramObject.hashCode();
    return getValuesIterator(paramObject, i);
  }
  
  public Object put(Object paramObject1, Object paramObject2) {
    return addOrRemoveMultiVal(0L, 0L, paramObject1, paramObject2, false, false);
  }
  
  public Object remove(Object paramObject) {
    return addOrRemoveMultiVal(0L, 0L, paramObject, null, true, false);
  }
  
  public Object remove(Object paramObject1, Object paramObject2) {
    return addOrRemoveMultiVal(0L, 0L, paramObject1, paramObject2, false, true);
  }
  
  public boolean containsKey(Object paramObject) {
    return super.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject) {
    return super.containsValue(paramObject);
  }
  
  public int valueCount(Object paramObject) {
    int i = paramObject.hashCode();
    return valueCount(paramObject, i);
  }
  
  public void putAll(HashMap paramHashMap) {
    Iterator iterator = paramHashMap.keySet.iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      put(object, paramHashMap.get(object));
    } 
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
      MultiValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(MultiValueHashMap.this, false);
    }
    
    public int size() {
      return MultiValueHashMap.this.size();
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
      MultiValueHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      MultiValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.MultiValueKeyIterator(MultiValueHashMap.this);
    }
    
    public int size() {
      return MultiValueHashMap.this.size();
    }
    
    public boolean contains(Object param1Object) {
      return MultiValueHashMap.this.containsKey(param1Object);
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
      int i = size();
      MultiValueHashMap.this.remove(param1Object);
      return (size() != i);
    }
    
    public boolean isEmpty() {
      return (size() == 0);
    }
    
    public void clear() {
      MultiValueHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\MultiValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */