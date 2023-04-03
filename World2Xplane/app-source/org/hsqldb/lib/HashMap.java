package org.hsqldb.lib;

import org.hsqldb.map.BaseHashMap;

public class HashMap extends BaseHashMap {
  Set keySet;
  
  Collection values;
  
  public HashMap() {
    this(8);
  }
  
  public HashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 3, 3, false);
  }
  
  public Object get(Object paramObject) {
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    return (j != -1) ? this.objectValueTable[j] : null;
  }
  
  public Object put(Object paramObject1, Object paramObject2) {
    return addOrRemove(0L, 0L, paramObject1, paramObject2, false);
  }
  
  public Object remove(Object paramObject) {
    return removeObject(paramObject, false);
  }
  
  public boolean containsKey(Object paramObject) {
    return super.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject) {
    return super.containsValue(paramObject);
  }
  
  public void putAll(HashMap paramHashMap) {
    Iterator iterator = paramHashMap.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      put(object, paramHashMap.get(object));
    } 
  }
  
  public void valuesToArray(Object[] paramArrayOfObject) {
    Iterator iterator = values().iterator();
    for (byte b = 0; iterator.hasNext(); b++)
      paramArrayOfObject[b] = iterator.next(); 
  }
  
  public void keysToArray(Object[] paramArrayOfObject) {
    Iterator iterator = keySet().iterator();
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
      HashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(HashMap.this, false);
    }
    
    public int size() {
      return HashMap.this.size();
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
      HashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      HashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(HashMap.this, true);
    }
    
    public int size() {
      return HashMap.this.size();
    }
    
    public boolean contains(Object param1Object) {
      return HashMap.this.containsKey(param1Object);
    }
    
    public Object get(Object param1Object) {
      int i = HashMap.this.getLookup(param1Object, param1Object.hashCode());
      return (i < 0) ? null : HashMap.this.objectKeyTable[i];
    }
    
    public boolean add(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean addAll(Collection param1Collection) {
      throw new RuntimeException();
    }
    
    public boolean remove(Object param1Object) {
      int i = size();
      HashMap.this.remove(param1Object);
      return (size() != i);
    }
    
    public boolean isEmpty() {
      return (size() == 0);
    }
    
    public void clear() {
      HashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */