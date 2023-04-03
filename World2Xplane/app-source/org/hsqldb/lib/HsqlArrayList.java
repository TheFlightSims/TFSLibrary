package org.hsqldb.lib;

import java.lang.reflect.Array;
import java.util.Comparator;

public class HsqlArrayList extends BaseList implements HsqlList {
  private static final int DEFAULT_INITIAL_CAPACITY = 8;
  
  private static final float DEFAULT_RESIZE_FACTOR = 2.0F;
  
  Object[] elementData;
  
  Object[] reserveElementData;
  
  private boolean minimizeOnClear;
  
  public HsqlArrayList(Object[] paramArrayOfObject, int paramInt) {
    this.elementData = paramArrayOfObject;
    this.elementCount = paramInt;
  }
  
  public HsqlArrayList() {
    this.elementData = new Object[8];
  }
  
  public HsqlArrayList(int paramInt, boolean paramBoolean) {
    if (paramInt < 8)
      paramInt = 8; 
    this.elementData = new Object[paramInt];
    this.minimizeOnClear = paramBoolean;
  }
  
  public HsqlArrayList(int paramInt) {
    if (paramInt < 0)
      throw new NegativeArraySizeException("Invalid initial capacity given"); 
    if (paramInt < 8)
      paramInt = 8; 
    this.elementData = new Object[paramInt];
  }
  
  public void add(int paramInt, Object paramObject) {
    if (paramInt > this.elementCount)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + ">" + this.elementCount); 
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " < 0"); 
    if (this.elementCount >= this.elementData.length)
      increaseCapacity(); 
    for (int i = this.elementCount; i > paramInt; i--)
      this.elementData[i] = this.elementData[i - 1]; 
    this.elementData[paramInt] = paramObject;
    this.elementCount++;
  }
  
  public boolean add(Object paramObject) {
    if (this.elementCount >= this.elementData.length)
      increaseCapacity(); 
    this.elementData[this.elementCount] = paramObject;
    this.elementCount++;
    return true;
  }
  
  public Object get(int paramInt) {
    if (paramInt >= this.elementCount)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " >= " + this.elementCount); 
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " < 0"); 
    return this.elementData[paramInt];
  }
  
  public int indexOf(Object paramObject) {
    if (paramObject == null) {
      for (byte b1 = 0; b1 < this.elementCount; b1++) {
        if (this.elementData[b1] == null)
          return b1; 
      } 
      return -1;
    } 
    for (byte b = 0; b < this.elementCount; b++) {
      if (paramObject.equals(this.elementData[b]))
        return b; 
    } 
    return -1;
  }
  
  public Object remove(int paramInt) {
    if (paramInt >= this.elementCount)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " >= " + this.elementCount); 
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " < 0"); 
    Object object = this.elementData[paramInt];
    for (int i = paramInt; i < this.elementCount - 1; i++)
      this.elementData[i] = this.elementData[i + 1]; 
    this.elementCount--;
    this.elementData[this.elementCount] = null;
    if (this.elementCount == 0)
      clear(); 
    return object;
  }
  
  public Object set(int paramInt, Object paramObject) {
    if (paramInt >= this.elementCount)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " >= " + this.elementCount); 
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("Index out of bounds: " + paramInt + " < 0"); 
    Object object = this.elementData[paramInt];
    this.elementData[paramInt] = paramObject;
    return object;
  }
  
  public final int size() {
    return this.elementCount;
  }
  
  private void increaseCapacity() {
    int i = (this.elementData.length == 0) ? 1 : this.elementData.length;
    i = (int)(i * 2.0F);
    resize(i);
  }
  
  private void resize(int paramInt) {
    if (paramInt == this.elementData.length)
      return; 
    Object[] arrayOfObject = (Object[])Array.newInstance(this.elementData.getClass().getComponentType(), paramInt);
    int i = (this.elementData.length > arrayOfObject.length) ? arrayOfObject.length : this.elementData.length;
    System.arraycopy(this.elementData, 0, arrayOfObject, 0, i);
    if (this.minimizeOnClear && this.reserveElementData == null) {
      ArrayUtil.clearArray(76, this.elementData, 0, this.elementData.length);
      this.reserveElementData = this.elementData;
    } 
    this.elementData = arrayOfObject;
  }
  
  public void trim() {
    resize(this.elementCount);
  }
  
  public void clear() {
    if (this.minimizeOnClear && this.reserveElementData != null) {
      this.elementData = this.reserveElementData;
      this.reserveElementData = null;
      this.elementCount = 0;
      return;
    } 
    for (byte b = 0; b < this.elementCount; b++)
      this.elementData[b] = null; 
    this.elementCount = 0;
  }
  
  public void setSize(int paramInt) {
    if (paramInt == 0) {
      clear();
      return;
    } 
    if (paramInt <= this.elementCount) {
      for (int i = paramInt; i < this.elementCount; i++)
        this.elementData[i] = null; 
      this.elementCount = paramInt;
      return;
    } 
    while (paramInt > this.elementData.length)
      increaseCapacity(); 
    this.elementCount = paramInt;
  }
  
  public Object[] toArray() {
    Object[] arrayOfObject = (Object[])Array.newInstance(this.elementData.getClass().getComponentType(), this.elementCount);
    System.arraycopy(this.elementData, 0, arrayOfObject, 0, this.elementCount);
    return arrayOfObject;
  }
  
  public Object[] toArray(int paramInt1, int paramInt2) {
    Object[] arrayOfObject = (Object[])Array.newInstance(this.elementData.getClass().getComponentType(), paramInt2 - paramInt1);
    System.arraycopy(this.elementData, paramInt1, arrayOfObject, 0, paramInt2 - paramInt1);
    return arrayOfObject;
  }
  
  public Object toArray(Object paramObject) {
    if (Array.getLength(paramObject) < this.elementCount)
      paramObject = Array.newInstance(paramObject.getClass().getComponentType(), this.elementCount); 
    System.arraycopy(this.elementData, 0, paramObject, 0, this.elementCount);
    return paramObject;
  }
  
  public void sort(Comparator paramComparator) {
    if (this.elementCount < 2)
      return; 
    ArraySort.sort(this.elementData, 0, this.elementCount, paramComparator);
  }
  
  public Object[] getArray() {
    return this.elementData;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\HsqlArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */