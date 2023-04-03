package org.hsqldb.lib;

import java.util.NoSuchElementException;

abstract class BaseList {
  protected int elementCount;
  
  abstract Object get(int paramInt);
  
  abstract Object remove(int paramInt);
  
  abstract boolean add(Object paramObject);
  
  abstract int size();
  
  public boolean contains(Object paramObject) {
    return !(indexOf(paramObject) == -1);
  }
  
  public boolean remove(Object paramObject) {
    int i = indexOf(paramObject);
    if (i == -1)
      return false; 
    remove(i);
    return true;
  }
  
  public int indexOf(Object paramObject) {
    byte b = 0;
    int i = size();
    while (b < i) {
      Object object = get(b);
      if (object == null) {
        if (paramObject == null)
          return b; 
      } else if (object.equals(paramObject)) {
        return b;
      } 
      b++;
    } 
    return -1;
  }
  
  public boolean addAll(Collection paramCollection) {
    boolean bool = false;
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      bool = true;
      add(iterator.next());
    } 
    return bool;
  }
  
  public boolean addAll(Object[] paramArrayOfObject) {
    boolean bool = false;
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      bool = true;
      add(paramArrayOfObject[b]);
    } 
    return bool;
  }
  
  public boolean isEmpty() {
    return (this.elementCount == 0);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(32 + this.elementCount * 3);
    stringBuffer.append("List : size=");
    stringBuffer.append(this.elementCount);
    stringBuffer.append(' ');
    stringBuffer.append('{');
    Iterator iterator = iterator();
    while (iterator.hasNext()) {
      stringBuffer.append(iterator.next());
      if (iterator.hasNext()) {
        stringBuffer.append(',');
        stringBuffer.append(' ');
      } 
    } 
    stringBuffer.append('}');
    return stringBuffer.toString();
  }
  
  public Iterator iterator() {
    return new BaseListIterator();
  }
  
  private class BaseListIterator implements Iterator {
    int counter = 0;
    
    boolean removed;
    
    private BaseListIterator() {}
    
    public boolean hasNext() {
      return (this.counter < BaseList.this.elementCount);
    }
    
    public Object next() {
      if (this.counter < BaseList.this.elementCount) {
        this.removed = false;
        Object object = BaseList.this.get(this.counter);
        this.counter++;
        return object;
      } 
      throw new NoSuchElementException();
    }
    
    public int nextInt() {
      throw new NoSuchElementException();
    }
    
    public long nextLong() {
      throw new NoSuchElementException();
    }
    
    public void remove() {
      if (this.removed)
        throw new NoSuchElementException("Iterator"); 
      this.removed = true;
      if (this.counter != 0) {
        BaseList.this.remove(this.counter - 1);
        this.counter--;
        return;
      } 
      throw new NoSuchElementException();
    }
    
    public void setValue(Object param1Object) {
      throw new NoSuchElementException();
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\BaseList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */