package org.hsqldb.lib;

import java.util.Comparator;

public class HsqlArrayHeap implements HsqlHeap {
  protected Comparator oc;
  
  protected int count;
  
  protected Object[] heap;
  
  public HsqlArrayHeap(int paramInt, Comparator paramComparator) throws IllegalArgumentException {
    if (paramInt <= 0)
      throw new IllegalArgumentException("" + paramInt); 
    if (paramComparator == null)
      throw new IllegalArgumentException("null comparator"); 
    this.heap = new Object[paramInt];
    this.oc = paramComparator;
  }
  
  public synchronized void clear() {
    for (byte b = 0; b < this.count; b++)
      this.heap[b] = null; 
    this.count = 0;
  }
  
  public synchronized void add(Object paramObject) throws IllegalArgumentException, RuntimeException {
    if (paramObject == null)
      throw new IllegalArgumentException("null element"); 
    if (isFull())
      throw new RuntimeException("full"); 
    if (this.count >= this.heap.length)
      increaseCapacity(); 
    int i = this.count;
    this.count++;
    while (i > 0) {
      int j = i - 1 >> 1;
      try {
        if (this.oc.compare(paramObject, this.heap[j]) >= 0)
          break; 
      } catch (Exception exception) {
        throw new IllegalArgumentException(exception.toString());
      } 
      this.heap[i] = this.heap[j];
      i = j;
    } 
    this.heap[i] = paramObject;
  }
  
  public synchronized boolean isEmpty() {
    return (this.count == 0);
  }
  
  public synchronized boolean isFull() {
    return (this.count == Integer.MAX_VALUE);
  }
  
  public synchronized Object peek() {
    return this.heap[0];
  }
  
  public synchronized Object remove() {
    if (this.count == 0)
      return null; 
    int i = 0;
    Object object2 = this.heap[i];
    this.count--;
    if (this.count == 0) {
      this.heap[0] = null;
      return object2;
    } 
    Object object1 = this.heap[this.count];
    this.heap[this.count] = null;
    while (true) {
      int j = (i << 1) + 1;
      if (j >= this.count)
        break; 
      int k = (i << 1) + 2;
      int m = (k >= this.count || this.oc.compare(this.heap[j], this.heap[k]) < 0) ? j : k;
      if (this.oc.compare(object1, this.heap[m]) <= 0)
        break; 
      this.heap[i] = this.heap[m];
      i = m;
    } 
    this.heap[i] = object1;
    return object2;
  }
  
  public synchronized int size() {
    return this.count;
  }
  
  public synchronized String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(super.toString());
    stringBuffer.append(" : size=");
    stringBuffer.append(this.count);
    stringBuffer.append(' ');
    stringBuffer.append('[');
    for (byte b = 0; b < this.count; b++) {
      stringBuffer.append(this.heap[b]);
      if (b + 1 < this.count) {
        stringBuffer.append(',');
        stringBuffer.append(' ');
      } 
    } 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
  
  private void increaseCapacity() {
    Object[] arrayOfObject = this.heap;
    this.heap = new Object[3 * this.heap.length / 2 + 1];
    System.arraycopy(arrayOfObject, 0, this.heap, 0, this.count);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlArrayHeap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */