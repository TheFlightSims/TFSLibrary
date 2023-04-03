package org.hsqldb.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.map.BaseHashMap;

public class LongKeyHashMap extends BaseHashMap {
  Set keySet;
  
  Collection values;
  
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  
  ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
  
  ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
  
  public LongKeyHashMap() {
    this(16);
  }
  
  public LongKeyHashMap(int paramInt) throws IllegalArgumentException {
    super(paramInt, 2, 3, false);
  }
  
  public Lock getWriteLock() {
    return this.writeLock;
  }
  
  public Object get(long paramLong) {
    this.readLock.lock();
    try {
      int i = getLookup(paramLong);
      if (i != -1)
        return this.objectValueTable[i]; 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Object put(long paramLong, Object paramObject) {
    this.writeLock.lock();
    try {
      return addOrRemove(paramLong, 0L, null, paramObject, false);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public boolean containsValue(Object paramObject) {
    this.readLock.lock();
    try {
      return super.containsValue(paramObject);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Object remove(long paramLong) {
    this.writeLock.lock();
    try {
      return addOrRemove(paramLong, 0L, null, null, true);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public boolean containsKey(long paramLong) {
    this.readLock.lock();
    try {
      return super.containsKey(paramLong);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public void clear() {
    this.writeLock.lock();
    try {
      super.clear();
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public Object[] toArray() {
    this.readLock.lock();
    try {
      if (isEmpty())
        return emptyObjectArray; 
      Object[] arrayOfObject = new Object[size()];
      byte b = 0;
      BaseHashMap.BaseHashIterator baseHashIterator = new BaseHashMap.BaseHashIterator(this, false);
      while (baseHashIterator.hasNext())
        arrayOfObject[b++] = baseHashIterator.next(); 
      return arrayOfObject;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public int getOrderedMatchCount(int[] paramArrayOfint) {
    byte b = 0;
    this.readLock.lock();
    try {
      while (b < paramArrayOfint.length && containsKey(paramArrayOfint[b]))
        b++; 
    } finally {
      this.readLock.unlock();
    } 
    return b;
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
      LongKeyHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongKeyHashMap.this, false);
    }
    
    public int size() {
      return LongKeyHashMap.this.size();
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
      LongKeyHashMap.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      LongKeyHashMap.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(LongKeyHashMap.this, true);
    }
    
    public int size() {
      return LongKeyHashMap.this.size();
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
      LongKeyHashMap.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LongKeyHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */