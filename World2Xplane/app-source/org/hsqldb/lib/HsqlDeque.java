package org.hsqldb.lib;

import java.util.NoSuchElementException;

public class HsqlDeque extends BaseList implements HsqlList {
  private Object[] list = new Object[10];
  
  private int firstindex = 0;
  
  private int endindex = 0;
  
  private static final int DEFAULT_INITIAL_CAPACITY = 10;
  
  public int size() {
    return this.elementCount;
  }
  
  public boolean isEmpty() {
    return (this.elementCount == 0);
  }
  
  public Object getFirst() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    return this.list[this.firstindex];
  }
  
  public Object getLast() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    return this.list[this.endindex - 1];
  }
  
  public Object get(int paramInt) throws IndexOutOfBoundsException {
    int i = getInternalIndex(paramInt);
    return this.list[i];
  }
  
  public void add(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    if (paramInt == this.elementCount) {
      add(paramObject);
      return;
    } 
    resetCapacity();
    int i = getInternalIndex(paramInt);
    if (i < this.endindex && this.endindex < this.list.length) {
      System.arraycopy(this.list, i, this.list, i + 1, this.endindex - i);
      this.endindex++;
    } else {
      System.arraycopy(this.list, this.firstindex, this.list, this.firstindex - 1, i - this.firstindex);
      this.firstindex--;
      i--;
    } 
    this.list[i] = paramObject;
    this.elementCount++;
  }
  
  public Object set(int paramInt, Object paramObject) throws IndexOutOfBoundsException {
    int i = getInternalIndex(paramInt);
    Object object = this.list[i];
    this.list[i] = paramObject;
    return object;
  }
  
  public Object removeFirst() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    Object object = this.list[this.firstindex];
    this.list[this.firstindex] = null;
    this.firstindex++;
    this.elementCount--;
    if (this.elementCount == 0) {
      this.firstindex = this.endindex = 0;
    } else if (this.firstindex == this.list.length) {
      this.firstindex = 0;
    } 
    return object;
  }
  
  public Object removeLast() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    this.endindex--;
    Object object = this.list[this.endindex];
    this.list[this.endindex] = null;
    this.elementCount--;
    if (this.elementCount == 0) {
      this.firstindex = this.endindex = 0;
    } else if (this.endindex == 0) {
      this.endindex = this.list.length;
    } 
    return object;
  }
  
  public boolean add(Object paramObject) {
    resetCapacity();
    if (this.endindex == this.list.length)
      this.endindex = 0; 
    this.list[this.endindex] = paramObject;
    this.elementCount++;
    this.endindex++;
    return true;
  }
  
  public boolean addLast(Object paramObject) {
    return add(paramObject);
  }
  
  public boolean addFirst(Object paramObject) {
    resetCapacity();
    this.firstindex--;
    if (this.firstindex < 0) {
      this.firstindex = this.list.length - 1;
      if (this.endindex == 0)
        this.endindex = this.list.length; 
    } 
    this.list[this.firstindex] = paramObject;
    this.elementCount++;
    return true;
  }
  
  public void clear() {
    if (this.elementCount == 0)
      return; 
    this.firstindex = this.endindex = this.elementCount = 0;
    for (byte b = 0; b < this.list.length; b++)
      this.list[b] = null; 
  }
  
  public int indexOf(Object paramObject) {
    for (byte b = 0; b < this.elementCount; b++) {
      int i = this.firstindex + b;
      if (i >= this.list.length)
        i -= this.list.length; 
      if (this.list[i] == paramObject)
        return b; 
      if (paramObject != null && paramObject.equals(this.list[i]))
        return b; 
    } 
    return -1;
  }
  
  public Object remove(int paramInt) {
    int i = getInternalIndex(paramInt);
    Object object = this.list[i];
    if (i == this.firstindex) {
      this.list[this.firstindex] = null;
      this.firstindex++;
      if (this.firstindex == this.list.length)
        this.firstindex = 0; 
    } else if (i > this.firstindex) {
      System.arraycopy(this.list, this.firstindex, this.list, this.firstindex + 1, i - this.firstindex);
      this.list[this.firstindex] = null;
      this.firstindex++;
      if (this.firstindex == this.list.length)
        this.firstindex = 0; 
    } else {
      System.arraycopy(this.list, i + 1, this.list, i, this.endindex - i - 1);
      this.endindex--;
      this.list[this.endindex] = null;
      if (this.endindex == 0)
        this.endindex = this.list.length; 
    } 
    this.elementCount--;
    if (this.elementCount == 0)
      this.firstindex = this.endindex = 0; 
    return object;
  }
  
  private int getInternalIndex(int paramInt) throws IndexOutOfBoundsException {
    if (paramInt < 0 || paramInt >= this.elementCount)
      throw new IndexOutOfBoundsException(); 
    int i = this.firstindex + paramInt;
    if (i >= this.list.length)
      i -= this.list.length; 
    return i;
  }
  
  private void resetCapacity() {
    if (this.elementCount < this.list.length)
      return; 
    Object[] arrayOfObject = new Object[this.list.length * 2];
    System.arraycopy(this.list, this.firstindex, arrayOfObject, this.firstindex, this.list.length - this.firstindex);
    if (this.endindex <= this.firstindex) {
      System.arraycopy(this.list, 0, arrayOfObject, this.list.length, this.endindex);
      this.endindex = this.list.length + this.endindex;
    } 
    this.list = arrayOfObject;
  }
  
  public void toArray(Object[] paramArrayOfObject) {
    int i = this.list.length - this.firstindex;
    if (i > this.elementCount)
      i = this.elementCount; 
    System.arraycopy(this.list, this.firstindex, paramArrayOfObject, 0, i);
    if (this.endindex <= this.firstindex) {
      System.arraycopy(this.list, 0, paramArrayOfObject, i, this.endindex);
      this.endindex = this.list.length + this.endindex;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */