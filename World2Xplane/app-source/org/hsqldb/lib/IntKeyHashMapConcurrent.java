package org.hsqldb.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.map.BaseHashMap;

public class IntKeyHashMapConcurrent extends BaseHashMap {
  Set keySet;
  
  Collection values;
  
  ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  
  ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
  
  ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
  
  public IntKeyHashMapConcurrent() {
    this(8);
  }
  
  public IntKeyHashMapConcurrent(int paramInt) throws IllegalArgumentException {
    super(paramInt, 1, 3, false);
  }
  
  public Lock getWriteLock() {
    return this.writeLock;
  }
  
  public Object get(int paramInt) {
    try {
      this.readLock.lock();
      int i = getLookup(paramInt);
      if (i != -1)
        return this.objectValueTable[i]; 
      return null;
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Object put(int paramInt, Object paramObject) {
    try {
      this.writeLock.lock();
      return addOrRemove(paramInt, 0L, null, paramObject, false);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public boolean containsValue(Object paramObject) {
    try {
      this.readLock.lock();
      return super.containsValue(paramObject);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Object remove(int paramInt) {
    try {
      this.writeLock.lock();
      return addOrRemove(paramInt, 0L, null, null, true);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public boolean containsKey(int paramInt) {
    try {
      this.readLock.lock();
      return super.containsKey(paramInt);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public int getOrderedMatchCount(int[] paramArrayOfint) {
    byte b = 0;
    try {
      this.readLock.lock();
      while (b < paramArrayOfint.length && super.containsKey(paramArrayOfint[b]))
        b++; 
      return b;
    } finally {
      this.readLock.unlock();
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
      IntKeyHashMapConcurrent.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntKeyHashMapConcurrent.this, false);
    }
    
    public int size() {
      return IntKeyHashMapConcurrent.this.size();
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
      IntKeyHashMapConcurrent.this.clear();
    }
  }
  
  class KeySet implements Set {
    public Iterator iterator() {
      IntKeyHashMapConcurrent.this.getClass();
      return (Iterator)new BaseHashMap.BaseHashIterator(IntKeyHashMapConcurrent.this, true);
    }
    
    public int size() {
      return IntKeyHashMapConcurrent.this.size();
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
      IntKeyHashMapConcurrent.this.clear();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\IntKeyHashMapConcurrent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */