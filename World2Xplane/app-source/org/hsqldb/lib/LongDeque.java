package org.hsqldb.lib;

import java.util.NoSuchElementException;

public class LongDeque {
  private long[] list = new long[10];
  
  private int firstindex = 0;
  
  private int endindex = 0;
  
  protected int elementCount;
  
  private static final int DEFAULT_INITIAL_CAPACITY = 10;
  
  public int size() {
    return this.elementCount;
  }
  
  public boolean isEmpty() {
    return (this.elementCount == 0);
  }
  
  public long getFirst() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    return this.list[this.firstindex];
  }
  
  public long getLast() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    return this.list[this.endindex - 1];
  }
  
  public long get(int paramInt) throws IndexOutOfBoundsException {
    int i = getInternalIndex(paramInt);
    return this.list[i];
  }
  
  public long removeFirst() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    long l = this.list[this.firstindex];
    this.list[this.firstindex] = 0L;
    this.firstindex++;
    this.elementCount--;
    if (this.elementCount == 0) {
      this.firstindex = this.endindex = 0;
    } else if (this.firstindex == this.list.length) {
      this.firstindex = 0;
    } 
    return l;
  }
  
  public long removeLast() throws NoSuchElementException {
    if (this.elementCount == 0)
      throw new NoSuchElementException(); 
    this.endindex--;
    long l = this.list[this.endindex];
    this.list[this.endindex] = 0L;
    this.elementCount--;
    if (this.elementCount == 0) {
      this.firstindex = this.endindex = 0;
    } else if (this.endindex == 0) {
      this.endindex = this.list.length;
    } 
    return l;
  }
  
  public boolean add(long paramLong) {
    resetCapacity();
    if (this.endindex == this.list.length)
      this.endindex = 0; 
    this.list[this.endindex] = paramLong;
    this.elementCount++;
    this.endindex++;
    return true;
  }
  
  public boolean addLast(long paramLong) {
    return add(paramLong);
  }
  
  public boolean addFirst(long paramLong) {
    resetCapacity();
    this.firstindex--;
    if (this.firstindex < 0) {
      this.firstindex = this.list.length - 1;
      if (this.endindex == 0)
        this.endindex = this.list.length; 
    } 
    this.list[this.firstindex] = paramLong;
    this.elementCount++;
    return true;
  }
  
  public int addAll(LongDeque paramLongDeque) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramLongDeque.size(); b2++) {
      add(paramLongDeque.get(b2));
      b1++;
    } 
    return b1;
  }
  
  public void clear() {
    if (this.elementCount == 0)
      return; 
    this.firstindex = this.endindex = this.elementCount = 0;
    for (byte b = 0; b < this.list.length; b++)
      this.list[b] = 0L; 
  }
  
  public void zeroSize() {
    this.firstindex = this.endindex = this.elementCount = 0;
  }
  
  public int indexOf(long paramLong) {
    for (byte b = 0; b < this.elementCount; b++) {
      int i = this.firstindex + b;
      if (i >= this.list.length)
        i -= this.list.length; 
      if (this.list[i] == paramLong)
        return b; 
    } 
    return -1;
  }
  
  public long remove(int paramInt) {
    int i = getInternalIndex(paramInt);
    long l = this.list[i];
    if (i == this.firstindex) {
      this.list[this.firstindex] = 0L;
      this.firstindex++;
      if (this.firstindex == this.list.length)
        this.firstindex = 0; 
    } else if (i > this.firstindex) {
      System.arraycopy(this.list, this.firstindex, this.list, this.firstindex + 1, i - this.firstindex);
      this.list[this.firstindex] = 0L;
      this.firstindex++;
      if (this.firstindex == this.list.length)
        this.firstindex = 0; 
    } else {
      System.arraycopy(this.list, i + 1, this.list, i, this.endindex - i - 1);
      this.endindex--;
      this.list[this.endindex] = 0L;
      if (this.endindex == 0)
        this.endindex = this.list.length; 
    } 
    this.elementCount--;
    if (this.elementCount == 0)
      this.firstindex = this.endindex = 0; 
    return l;
  }
  
  public boolean contains(long paramLong) {
    for (byte b = 0; b < this.elementCount; b++) {
      int i = this.firstindex + b;
      if (i >= this.list.length)
        i -= this.list.length; 
      if (this.list[i] == paramLong)
        return true; 
    } 
    return false;
  }
  
  public void toArray(int[] paramArrayOfint) {
    for (byte b = 0; b < this.elementCount; b++)
      paramArrayOfint[b] = (int)get(b); 
  }
  
  public void toArray(long[] paramArrayOflong) {
    for (byte b = 0; b < this.elementCount; b++)
      paramArrayOflong[b] = get(b); 
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
    long[] arrayOfLong = new long[this.list.length * 2];
    System.arraycopy(this.list, this.firstindex, arrayOfLong, this.firstindex, this.list.length - this.firstindex);
    if (this.endindex <= this.firstindex) {
      System.arraycopy(this.list, 0, arrayOfLong, this.list.length, this.endindex);
      this.endindex = this.list.length + this.endindex;
    } 
    this.list = arrayOfLong;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LongDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */