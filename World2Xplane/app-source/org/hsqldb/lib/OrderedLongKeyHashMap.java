package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class OrderedLongKeyHashMap extends BaseHashMap {
  Set keySet;
  
  Collection values;
  
  public OrderedLongKeyHashMap() {
    this(8);
  }
  
  public OrderedLongKeyHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 2, 3, false);
    this.isList = true;
  }
  
  public OrderedLongKeyHashMap(int paramInt, boolean paramBoolean) throws IllegalArgumentException {
    super(paramInt, 2, 3, false);
    this.objectKeyTable = new Object[this.objectValueTable.length];
    this.isTwoObjectValue = true;
    this.isList = true;
    if (paramBoolean)
      this.objectValueTable2 = new Object[this.objectValueTable.length]; 
    this.minimizeOnEmpty = true;
  }
  
  public Object get(long paramLong) {
    int i = getLookup(paramLong);
    return (i != -1) ? this.objectValueTable[i] : null;
  }
  
  public Object getValueByIndex(int paramInt) {
    return this.objectValueTable[paramInt];
  }
  
  public Object getSecondValueByIndex(int paramInt) {
    return this.objectKeyTable[paramInt];
  }
  
  public Object getThirdValueByIndex(int paramInt) {
    return this.objectValueTable2[paramInt];
  }
  
  public Object setSecondValueByIndex(int paramInt, Object paramObject) {
    Object object = this.objectKeyTable[paramInt];
    this.objectKeyTable[paramInt] = paramObject;
    return object;
  }
  
  public Object setThirdValueByIndex(int paramInt, Object paramObject) {
    Object object = this.objectValueTable2[paramInt];
    this.objectValueTable2[paramInt] = paramObject;
    return object;
  }
  
  public Object put(long paramLong, Object paramObject) {
    return addOrRemove(paramLong, paramObject, null, false);
  }
  
  public boolean containsValue(Object paramObject) {
    return super.containsValue(paramObject);
  }
  
  public Object remove(long paramLong) {
    return addOrRemove(paramLong, null, null, false);
  }
  
  public boolean containsKey(long paramLong) {
    return super.containsKey(paramLong);
  }
  
  public Object put(long paramLong, Object paramObject1, Object paramObject2) {
    return addOrRemove(paramLong, paramObject1, paramObject2, false);
  }
  
  public int getLookup(long paramLong) {
    return super.getLookup(paramLong);
  }
  
  public Object getFirstByLookup(int paramInt) {
    return (paramInt == -1) ? null : this.objectValueTable[paramInt];
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
      OrderedLongKeyHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(OrderedLongKeyHashMap.this, false);
    }
    
    public int size() {
      return OrderedLongKeyHashMap.this.size();
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
      OrderedLongKeyHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      OrderedLongKeyHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(OrderedLongKeyHashMap.this, true);
    }
    
    public int size() {
      return OrderedLongKeyHashMap.this.size();
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
      OrderedLongKeyHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\OrderedLongKeyHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */