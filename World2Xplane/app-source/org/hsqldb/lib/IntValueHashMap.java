package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.map.BaseHashMap;

public class IntValueHashMap extends BaseHashMap {
  Set keySet;
  
  private Collection values;
  
  public IntValueHashMap() {
    this(8);
  }
  
  public IntValueHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 3, 1, false);
  }
  
  public int get(Object paramObject) throws NoSuchElementException {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1)
      return this.intValueTable[j]; 
    throw new NoSuchElementException();
  }
  
  public int get(Object paramObject, int paramInt) {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    return (j != -1) ? this.intValueTable[j] : paramInt;
  }
  
  public boolean get(Object paramObject, int[] paramArrayOfint) {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = paramObject.hashCode();
    int j = getLookup(paramObject, i);
    if (j != -1) {
      paramArrayOfint[0] = this.intValueTable[j];
      return true;
    } 
    return false;
  }
  
  public Object getKey(int paramInt) {
    BaseHashMap.BaseHashIterator baseHashIterator = new BaseHashMap.BaseHashIterator(this, false);
    while (baseHashIterator.hasNext()) {
      int i = baseHashIterator.nextInt();
      if (i == paramInt)
        return this.objectKeyTable[baseHashIterator.getLookup()]; 
    } 
    return null;
  }
  
  public boolean put(Object paramObject, int paramInt) {
    if (paramObject == null)
      throw new NoSuchElementException(); 
    int i = size();
    addOrRemove(0L, paramInt, paramObject, null, false);
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
  
  public boolean containsValue(int paramInt) {
    throw new RuntimeException();
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
  
  public void putAll(IntValueHashMap paramIntValueHashMap) {
    Iterator iterator = paramIntValueHashMap.keySet().iterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();
      put(object, paramIntValueHashMap.get(object));
    } 
  }
  
  class Values implements Collection {
    public Iterator iterator() {
      IntValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntValueHashMap.this, false);
    }
    
    public int size() {
      return IntValueHashMap.this.size();
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
      IntValueHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      IntValueHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntValueHashMap.this, true);
    }
    
    public int size() {
      return IntValueHashMap.this.size();
    }
    
    public boolean contains(Object param1Object) {
      return IntValueHashMap.this.containsKey(param1Object);
    }
    
    public Object get(Object param1Object) {
      int i = IntValueHashMap.this.getLookup(param1Object, param1Object.hashCode());
      return (i < 0) ? null : IntValueHashMap.this.objectKeyTable[i];
    }
    
    public boolean add(Object param1Object) {
      throw new RuntimeException();
    }
    
    public boolean addAll(Collection param1Collection) {
      throw new RuntimeException();
    }
    
    public boolean remove(Object param1Object) {
      int i = size();
      IntValueHashMap.this.remove(param1Object);
      return (size() != i);
    }
    
    public boolean isEmpty() {
      return (size() == 0);
    }
    
    public void clear() {
      IntValueHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\IntValueHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */